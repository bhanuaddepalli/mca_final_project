
package com.video.streaming.controller;

import com.video.streaming.data.StreamRequestData;
import com.video.streaming.data.StreamResponseData;
import com.video.streaming.service.StreamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The Class StreamController.
 */
@CrossOrigin("*")
@RestController
public class StreamController {

    /** The stream service. */
    @Autowired
    StreamService streamService;

    /**
     * Gets the all streams.
     *
     * @return the all streams
     */
    @GetMapping(value = "/streams")
    public ResponseEntity<List<StreamResponseData>> getAllStreams() {
        return new ResponseEntity<>(streamService.getAllStreams(), HttpStatus.OK);
    }


    /**
     * Gets the streams by id.
     *
     * @param id the id
     * @return the streams by id
     */
    @GetMapping(value = "/streams/{id}")
    public ResponseEntity<StreamResponseData> getStreamsById(@PathVariable("id") long id) {
        return new ResponseEntity<>(streamService.getStreamsById(id), HttpStatus.OK);
    }

    /**
     * Creates the stream.
     *
     * @param streamRequestData the stream request data
     * @return the response entity
     */
    @PostMapping("/streams")
    public ResponseEntity<StreamResponseData> createStream(@RequestBody @Valid StreamRequestData streamRequestData) {
        return new ResponseEntity<>(streamService.createStream(streamRequestData), HttpStatus.CREATED);
    }

    /**
     * Update stream.
     *
     * @param streamRequestData the stream request data
     * @param id                the id
     * @return the response entity
     */
    @PutMapping("/streams/{id}")
    public ResponseEntity<StreamResponseData> updateStream(@RequestBody @Valid StreamRequestData streamRequestData,
            @PathVariable("id") long id) {
        return new ResponseEntity<>(streamService.updateStream(streamRequestData, id), HttpStatus.OK);
    }

    /**
     * Delete stream.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/streams/{id}")
    public ResponseEntity<String> deleteStream(@PathVariable("id") long id) {
        streamService.deleteStream(id);
        return new ResponseEntity<>("Stream deleted successfully.", HttpStatus.OK);
    }

}
