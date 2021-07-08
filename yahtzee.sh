#!/bin/bash
# Program name
program=Yahtzee

rm *.class
javac *.java
cd ..
jar cmvf $program/Manifest.txt $program.jar $program/*.class $program/*.java $program/images $program/README.md
java -jar $program.jar