#/bin/sh

rm /usr/local/axis2-1.5/repository/services/VirtualLabs.aar 
rm /usr/local/axis2-1.5/lib/VirtualLabsSrc.jar

ant clean
ant

cp build/lib/VirtualLabsSrc.jar /usr/local/axis2-1.5/lib/VirtualLabsSrc.jar

cd ws
ant clean
ant
cd ../


