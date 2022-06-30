/**
 * Copyright (c) OCEANEERING INTERNATIONAL, Inc. 2021 - 2025 All Rights Reserved
 * <p>
 * This file contains confidential and proprietary information. Any use of this code, including
 * reproduction, modification, distribution or republication, without the prior written consent
 * of OCEANEERING INTERNATIONAL, Inc., is strictly prohibited.
 */

package com.video.streaming.service;

import com.video.streaming.constants.SysConstants;
import com.video.streaming.entity.Stream;
import com.video.streaming.enums.StreamStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpThread implements Runnable {

    /** The Constant Log. */
    private static final Logger Log = LoggerFactory.getLogger(OpThread.class);

    /** The ocean perception. */
    private OceanPerception oceanPerception;

    /** The stream details. */
    private Stream streamDetails;

    /** The thread. */
    private Thread thread;

    /**
     * Instantiates a new op thread.
     *
     * @param streamDetails   the stream details
     * @param oceanPerception the ocean perception
     */
    public OpThread(Stream streamDetails, OceanPerception oceanPerception) {
        this.oceanPerception = oceanPerception;
        this.streamDetails = streamDetails;
        Log.debug("Creating Thread for : {}", streamDetails.getStreamName());
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        Log.debug("Running Thread for Stream: {}", streamDetails.getStreamName());
        String operSys = System.getProperty("os.name").toLowerCase();
        if (operSys.contains("win")) {
            runWindows();
            Log.debug("Running Thread for Stream in Windows : {}", streamDetails.getStreamName());
        } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
            runLinux();
        } else {
            Log.debug("Running Thread for Stream: {}", streamDetails.getStreamName());
        }
    }

    /**
     * Run linux.
     */
    private void runLinux() {
        Process process;
        String appName;
        String streamUrl = SysConstants.DEFAULT_STREAM_URL;
        appName = SysConstants.DEFAULT_APP_NAME_STREAM;

        String output = SysConstants.DEFAULT_RTMP_URL_START + streamUrl + ":" + this.oceanPerception.getRtmpPort() + "/"
                + appName + "/" + streamDetails.getStreamName();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-rtsp_transport", "tcp", "-loglevel", "info",
                    "-i", streamDetails.getStreamRtspUrl(), "-codec:v", SysConstants.CODEC_FFMPEG_QUERY, "-an", "-tune",
                    "zerolatency", "-b:v", SysConstants.BITRATE_FFMPEG, "-maxrate", SysConstants.MAX_BITRATE_FFMPEG,
                    "-bufsize", SysConstants.BUFF_SIXE_FFMPEG, "-f", "flv", output);

            long dateString = new Date().getTime();
            Path errorFile = Paths.get("logs", SysConstants.ERROR + "_" + streamDetails.getStreamName() + "_"
                    + dateString + SysConstants.TXT_FORMAT);
            Files.deleteIfExists(errorFile);
            Files.createDirectories(Paths.get(this.oceanPerception.getLogsFolder()));
            processBuilder.redirectErrorStream(true);
            processBuilder.redirectError(errorFile.toFile());
            process = processBuilder.start();
            streamDetails.setStreamStatus(StreamStatus.ACTIVE);
            streamDetails.setStreamUrlRelativePath(
                    streamDetails.getStreamApplicationName() + "/" + streamDetails.getStreamName());
            streamDetails.setComments("Stream is UP and Running");
            streamDetails.setStreamApplicationName("ws://" + streamUrl + ":3333/"
                    + appName + "/" + streamDetails.getStreamName());
            oceanPerception.updateStreams(streamDetails);
            Integer returnCode = process.waitFor();
            Log.debug("Stream id {} got : {} return code ", streamDetails.getStreamId(), returnCode);
            if (returnCode > 0) {
                streamDetails.setStreamStatus(StreamStatus.EXCEPTION);
                streamDetails.setComments("Error while running query with return code " + returnCode);
            } else {
                streamDetails.setStreamStatus(StreamStatus.INACTIVE);
            }
            oceanPerception.updateStreams(streamDetails);
        } catch (InterruptedException e) {
            Log.error("Interrupted while running Query for Stream: {}", streamDetails.getStreamId());
            streamDetails.setStreamStatus(StreamStatus.INACTIVE);
            streamDetails.setComments(e.getMessage());
            oceanPerception.updateStreams(streamDetails);
        } catch (Exception e) {
            streamDetails.setStreamStatus(StreamStatus.EXCEPTION);
            streamDetails.setComments(e.getMessage());
            oceanPerception.updateStreams(streamDetails);
            Log.error("Error while running Query Stream: {}", streamDetails.getStreamId());
        }
    }

    /**
     * Run windows.
     */
    private void runWindows() {
        Process process;
        String stream = streamDetails.getStreamApplicationName();
        String appName;
        String streamUrl = SysConstants.DEFAULT_STREAM_URL;
        appName = SysConstants.DEFAULT_APP_NAME_STREAM;

        String output = SysConstants.DEFAULT_RTMP_URL_START + streamUrl + ":" + this.oceanPerception.getRtmpPort() + "/"
                + appName + "/" + streamDetails.getStreamName();
        streamDetails.setStreamApplicationName("ws://" + streamUrl + ":3333/"
                + appName + "/" + streamDetails.getStreamName());
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg.exe", "-rtsp_transport", "tcp", "-loglevel", "info",
                "-i", streamDetails.getStreamRtspUrl(), "-codec:v", SysConstants.CODEC_FFMPEG_QUERY, "-an", "-tune",
                "zerolatency", "-b:v", SysConstants.BITRATE_FFMPEG, "-maxrate", SysConstants.MAX_BITRATE_FFMPEG,
                "-bufsize", SysConstants.BUFF_SIXE_FFMPEG, "-f", "flv", output);
        try {
            long dateString = new Date().getTime();
            Path errorFile = Paths.get("logs", SysConstants.ERROR + "_" + streamDetails.getStreamName() + "_"
                    + dateString + SysConstants.TXT_FORMAT);
            Files.deleteIfExists(errorFile);
            Files.createDirectories(Paths.get(this.oceanPerception.getLogsFolder()));
            processBuilder.redirectError(ProcessBuilder.Redirect.to(errorFile.toFile()));
            process = processBuilder.start();
            streamDetails.setStreamStatus(StreamStatus.ACTIVE);
            streamDetails.setStreamUrlRelativePath(
                    streamDetails.getStreamApplicationName() + "/" + streamDetails.getStreamName());
            streamDetails.setComments("Stream is UP and Running");
            oceanPerception.updateStreams(streamDetails);
            Integer returnCode = process.waitFor();

            if (returnCode > 0) {
                streamDetails.setStreamStatus(StreamStatus.EXCEPTION);
                streamDetails.setComments("Error while running query with return code " + returnCode);
                Log.warn("Stream id {} got : {} return code ", streamDetails.getStreamId(), returnCode);
                oceanPerception.updateThreadList(streamDetails);
            } else {
                Log.warn("Stream id {} got : {} Got Closed ", streamDetails.getStreamId(), returnCode);
                streamDetails.setStreamStatus(StreamStatus.INACTIVE);
            }
            oceanPerception.updateStreams(streamDetails);
        } catch (InterruptedException e) {
            Log.error("Interrupted while running Query for Stream: {}", streamDetails.getStreamId());
            streamDetails.setStreamStatus(StreamStatus.INACTIVE);
            streamDetails.setComments(e.getMessage());
            oceanPerception.updateStreams(streamDetails);
        } catch (Exception e) {
            streamDetails.setStreamStatus(StreamStatus.EXCEPTION);
            streamDetails.setComments(e.getMessage());
            oceanPerception.updateStreams(streamDetails);
            Log.error("Error while running Query Stream: {}", streamDetails.getStreamId());
        }
    }

    /**
     * Start.
     */
    public void start() {
        Log.debug("Starting Thread of Stream: {}", streamDetails.getStreamName());
        if (Objects.isNull(thread)) {
            thread = new Thread(this, streamDetails.getStreamName());
            thread.start();
        }
    }

    /**
     * Stop.
     */
    public void stop() {
        Log.debug("Stopping Thread of Stream: {}", streamDetails.getStreamName());
        Thread.currentThread().interrupt();
    }

    /**
     * Gets the thread id.
     *
     * @return the thread id
     */
    public Long getThreadId() {

        return Thread.currentThread().getId();
    }

}
