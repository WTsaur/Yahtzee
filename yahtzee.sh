#!/bin/bash
# Program name
folder=Yahtzee
program=hwx

rm *.class
javac *.java
cd ..
jar cmvf $folder/Manifest.txt $program.jar $folder/*.class $folder/*.java $folder/images $folder/README.md
java -jar $program.jar