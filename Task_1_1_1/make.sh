#!/bin/bash

javac -d ./build/classes/main/java ./src/main/java/Heapsort/*.java
javadoc -d ./docs -sourcepath ./src/main/java -subpackages Heapsort
java -classpath ./build/classes/main/java/ Heapsort.Main