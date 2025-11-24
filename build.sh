#!/bin/zsh
# Compile all Java files in src/ to build/
javac -d build src/*.java

# Run the program from build/
cd build && java Main
