@echo off
docker run --detach -it -e RTSP_PROTOCOLS=tcp -p 8900:8554 aler9/rtsp-simple-server
docker-compose -f ./OvenMediaEngine/docker-compose.yml up --detach
echo "Oven Media Server is UP and Running"
FOR /f "tokens=1" %%i in ('jps -m ^| find "streaming-0.0.1-SNAPSHOT.jar"') do taskkill /F /PID %%i 
echo "Terminated Running Offshore Media Tool JAVA Service"
start /B ffmpeg -re -stream_loop -1 -nostdin -nostats -hide_banner -log_level panic -i 5min.mp4 -codec:v h264 -tune zerolatency -codec:a aac -preset ultrafast -b:v 2M -f rtsp -rtsp_transport tcp rtsp://localhost:8900/birds
start /B ffmpeg -re -stream_loop -1 -nostdin -nostats -hide_banner -log_level panic -i 5min.mp4 -codec:v h264 -tune zerolatency -codec:a aac -preset ultrafast -b:v 2M -f rtsp -rtsp_transport tcp rtsp://localhost:8900/animals
start /B ffmpeg -re -stream_loop -1 -nostdin -nostats -hide_banner -log_level panic -i 5min.mp4 -codec:v h264 -tune zerolatency -an -preset ultrafast -b:v 2M -f rtsp -rtsp_transport tcp rtsp://localhost:8900/5min
call mvn -f streaming/pom.xml clean package
echo "Offshore Media Tool Packaging done and server will start in a while"
javaw -jar streaming/target/streaming-0.0.1-SNAPSHOT.jar