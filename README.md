# Mandelbrot Set Explorer

![This makes use of the "Sunset" color palette.](https://raw.githubusercontent.com/robertsonje/MandelbrotSetExplorer/master/rc/about-picture.png)

## Table of Contents

- Description
- What's a Mandelbrot Set?
- How to Build
- How to Use
- More Information

## Description

This is a project that I've done to explore graphics programming. It's a Java program that allows for exploration of Mandelbrot sets. Features include linear interpolation of colors, different color palettes, and saving of interesting points on the picture to separate files. It also allows for saving pictures to PNGs.

## What's a Mandelbrot Set?

The Mandelbrot set is a set of complex numbers of which the function z^2 + C does not reach infinity upon increasing the value of z. Complex numbers have a real and imaginary component that allows for proper representation in a 2D image. 

The picture seen here under the title is what is generated when a computer creates the Mandelbrot Set in image form. It is a *fractal*, meaning that it is a pattern that repeats itself infinitely within that pattern. As a result, you would be able to find the overall pattern somewhere within the pattern itself, and again within that pattern, and so on. Other well known fractals include the Sierpinski Triangle and the Koch Snowflake.

The fractal properties of the Mandelbrot Set is what gives it its distinctive, striking and organic look. Some people describe it as a spider, others as a beetle. There's even descriptions of the Mandelbrot set representing a man's head. If you were to zoom into certain points, you'll find some beautiful and radiant patterns that seem to never end. This concept alone is what inspired me to make this program. 

## How to Build

This program requires the Java Runtime library, and compiling it requires Java SDK 7. To compile the program, simply run the release bat files.

## How to Use

It will take a while for the application to start up because it's generating the image first, one iteration at a time. Once the window pops up, use the mouse wheel to zoom in our out. Move the mouse over the part you want to zoom in, then slowly rotate the mouse wheel inwards to zoom in, or outwards to zoom out. Do not quickly rotate the mouse wheel or it will slow the program down (it has to calculate the iterations for each zoom level).

You can also load in and save zoom points using the file dialog in the file menu.

## More Information

More information can be found in the following videos:

[Fractals: The Colors of Infinity](https://www.youtube.com/watch?v=pJA8mayMKvY) - A documentary by well-known science fiction writer Arthur C. Clarke done on the Mandelbrot set. It contains interviews from the set's namesake, Benoit Mandelbrot, along with containing details about the nature of the set itself, along with other fractals.

[The Mandelbrot Set](https://www.youtube.com/watch?v=56gzV0od6DU) - A 22-minute video done on the history of the Mandelbrot set.

[The Mandelbrot Set - Numberphile](https://www.youtube.com/watch?v=NGMRB4O922I) - A shorter and more modern video done on the Mandelbrot set.

