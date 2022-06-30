@echo off
docker run --detach -it -e RTSP_PROTOCOLS=tcp -p 8900:8554 aler9/rtsp-simple-server
docker-compose -f ./OvenMediaEngine/docker-compose.yml up --detach
echo "Oven Media Server is UP and Running"
FOR /f "tokens=1" %%i in ('jps -m ^| find "streaming-0.0.1-SNAPSHOT.jar"') do taskkill /F /PID %%i 
echo "Terminated Running Offshore Media Tool JAVA Service"
call mvn -f streaming/pom.xml clean package
echo "Offshore Media Tool Packaging done"
javaw -jar streaming/target/streaming-0.0.1-SNAPSHOT.jar