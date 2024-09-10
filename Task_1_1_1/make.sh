#!/bin/bash

javac -d ./build/classes/java/main ./src/main/java/heapsort/*.java
javadoc -d ./docs -sourcepath ./src/main/java -subpackages heapsort
java -classpath ./build/classes/java/main/ heapsort.Main