javac -d bin -cp src src/mandelbrotGenerator/MainProgram.java
jar cvf MandelBrotGenerator.jar bin rc
rem java -cp bin mandelbrotGenerator.MainProgram
@echo off
pause
