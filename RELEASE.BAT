mkdir bin
javac -d bin -cp src src/mandelbrotGenerator/MainProgram.java
cd bin
jar cvfe ../MandelbrotSetExplorer.jar mandelbrotGenerator.MainProgram ../rc .
cd .. 
java -jar MandelbrotSetExplorer.jar
pause
