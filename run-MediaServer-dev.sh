docker stop $ (docker ps -a -q --filter ancestor=jrottenberg/ffmpeg --format="{{.ID}}")
echo "Deleted RUNNING FFMPEG CONTAINERS"
docker pull jrottenberg/ffmpeg
echo "PULL FFMPEG LATEST IMAGE Done"
docker-compose -f ./OvenMediaEngine/docker-compose.yml up -d dev
echo "Oven Media Server is UP and Running"
kill -9 `jps | grep "offshore-video-streaming-0.0.1-SNAPSHOT.jar" | cut -d " " -f 1`
echo "Terminated Running Offshore Media Tool JAVA Service"
call mvn -f offshore-video-streaming/pom.xml clean package
echo "Offshore Media Tool Packaging done"
javaw -jar -Dspring.profiles.active=dev offshore-video-streaming/target/offshore-video-streaming-0.0.1-SNAPSHOT.jar