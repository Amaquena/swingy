#!/bin/bash
if [ $1 ]
then
	pushd swingy/ > /dev/null
	mvn clean package
	java -jar target/swingy.jar $1
	popd > /dev/null
else
	echo "Needs an argument"
fi