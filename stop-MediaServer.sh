docker stop $(docker ps -a -q --filter ancestor=jrottenberg/ffmpeg --format="{{.ID}}")
echo "Deleted RUNNING FFMPEG CONTAINERS"
kill -9 `jps | grep "offshore-video-streaming-0.0.1-SNAPSHOT.jar" | cut -d " " -f 1`
echo "Terminated Running Offshore Media Tool JAVA Service"
docer rm $(docker stop $(docker ps -a -q --filter ancestor=ancestor=airensoft/ovenmediaengine --format="{{.ID}}"))
echo "Stoped and Deleted RUNNING OvenMedia CONTAINERS"