javac -version -g -verbose -d bin -cp src src/mandelbrotGenerator/MainProgram.java
cd bin
jdb mandelbrotGenerator.MainProgram
cd ..
@echo off
pause
