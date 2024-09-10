#!/bin/bash

javac -d ./build/classes/main/java ./src/main/java/heapsort/*.java
javadoc -d ./docs -sourcepath ./src/main/java -subpackages heapsort
java -classpath ./build/classes/main/java/ heapsort.Main