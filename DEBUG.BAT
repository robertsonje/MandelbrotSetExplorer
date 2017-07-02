javac -version -g -verbose -d bin -cp src src/mandelbrotGenerator/MainProgram.java
jdb -cp bin mandelbrotGenerator.MainProgram
@echo off
pause
