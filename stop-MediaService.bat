
FOR /f "tokens=1" %%i in ('jps -m ^| find "streaming-0.0.1-SNAPSHOT.jar"') do taskkill /F /PID %%i 
echo "Terminated Running Offshore Media Tool JAVA Service"
FOR /f "tokens=*" %%i IN ('docker ps -a -q --filter "ancestor=aler9/rtsp-simple-server"') DO docker stop %%i && docker rm %%i
FOR /f "tokens=*" %%i IN ('docker ps -a -q --filter "ancestor=airensoft/ovenmediaengine"') DO docker stop %%i && docker rm %%i
echo "Stoped and Deleted RUNNING OvenMedia CONTAINERS"
