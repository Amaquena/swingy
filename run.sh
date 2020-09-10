#!/bin/bash
if [ $1 ]
then
	pushd swingy/ > /dev/null
	mvn clean package
	java -jar target/swingy-1.0-SNAPSHOT.jar $1
	popd > /dev/null
else
	echo "Needs an argument"
fi