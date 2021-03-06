/**
* This determines the PainterImage class used to draw the Mandelbrot set.
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PainterImage extends BufferedImage {
	/**
	 * This is a class meant to assist with drawing images.
	 * Contains functions for drawing pixels, lines, shapes,
	 * and setting colors. Also clears the screen for refreshing.
	 */
	private ColorPalette colorPalette;
	private Graphics2D g2d;
	private int height;
	private int width;

	public PainterImage ( MainWindow owner ) {
		/**
		 * Constructor for the PainterImage object. A new color palette is generated.
		 *
		 * @see         BufferedImage
		 */
		// initialize the color palette
		super( (int)MainWindow.DEFAULT_SIZE.getX(), (int)MainWindow.DEFAULT_SIZE.getY(), BufferedImage.TYPE_INT_RGB );
		width = (int)MainWindow.DEFAULT_SIZE.getX();
		height = (int)MainWindow.DEFAULT_SIZE.getY();
		/*
		// sunset color palette
		Color[] colors = {	new Color( 0, 6, 92 ),
							new Color( 8, 41, 156 ),
							new Color( 243, 255, 247 ),
							new Color( 255, 174, 4 ),
							new Color( 82, 31, 48 ) };
							*/
		/*
		// radioactive color palette
		Color[] colors = {	new Color( 0, 18, 26 ),
				new Color( 0, 52, 52 ),
				new Color( 0, 77, 52 ),
				new Color( 0, 153, 0 ),
				new Color( 68, 204, 0 ),
				new Color( 157, 235, 0 ),
				new Color( 255, 255, 0 ),
				new Color( 255, 255, 205 ),
				new Color( 255, 255, 0 ),
				new Color( 157, 235, 0 ),
				new Color( 68, 204, 0 ),
				new Color( 0, 153, 0 ),
				new Color( 0, 77, 52 ),
				new Color( 0, 52, 52 ) };
		*/
		// milky way color palette
		Color[] colors = {	new Color( 0, 43, 52 ),
				new Color( 0, 51, 103 ),
				new Color( 0, 26, 153 ),
				new Color( 35, 0, 204 ),
				new Color( 128, 0, 255 ),
				new Color( 234, 127, 255 ),
				new Color( 255, 191, 245 ),
				new Color( 234, 127, 255 ),
				new Color( 128, 0, 255 ),
				new Color( 35, 0, 204 ),
				new Color( 0, 26, 153 ),
				new Color( 0, 51, 103 ) };
		colorPalette = new ColorPalette( owner, MainWindow.ITERATIONS, new ColorGradient( colors ) );
		g2d = (Graphics2D) getGraphics();
	}

	public void drawStripes() {
		/**
		 * Main function for refreshing the screen.
		 *
		 * @param  g	Graphics object for drawing to the window.
		 */
		for ( int i = 0; i < MainWindow.ITERATIONS; i++) {
			g2d.setColor( colorPalette.getColor( i ) );
			g2d.drawLine( i, 0, i, width);
		}
	}

	public void setColor( Color color ) {
		/**
		 * Sets the color of the painter object.
		 *
		 */
		g2d.setColor( color );
	}

	public Color getColor() {
		/**
		 * Gets the color of the painter object.
		 *
		 * @return	the color of the painter object
		 */
		return g2d.getColor();
	}

	public Color getIterationColor( int iteration ) {
		/**
		 * Gets the color in the color palette corresponding to its Mandelbrot iteration index.
		 *
		 * @param	iteration	The iteration the color is mapped to.
		 * @return	the color mapped to the current iteration, or black if it's out of bounds
		 */
		if ( iteration < 0 || iteration > colorPalette.getNumColors() ) {
		}
		return colorPalette.getColor( iteration );
	}

	public void drawPixel( int x, int y ) {
		/**
		 * Draws a single pixel on the screen.
		 *
		 * @param  g	Graphics object for drawing to the window.
		 */
		// bounds check
		if ( x < 0 || x >= width ) {
			ErrorHandler.printErrorMessage(" x value (" + x + ") is out of bounds" );
			return;
		}
		if ( y < 0 || y >= height ) {
			ErrorHandler.printErrorMessage(" x value (" + x + ") is out of bounds" );
			return;
		}
		setRGB( x, y, g2d.getColor().getRGB() );
	}

	public void applyGaussianBlur(  ) {
		/**
		 * Blurs the current picture to smooth everything out. Currently
		 * unused.
		 */
		float[][] blurMatrix =	{{1f/16f, 2f/16f, 1f/16f},
								{2f/16f, 4f/16f, 2f/16f},
								{1f/16f, 2f/16f, 1f/16f}};
		float r = 0f;
		float g = 0f;
		float b = 0f;
		Color helperColor;
		for( int y = 1; y < height - 1; y++ ) {
			for( int x = 1; x < width - 1; x++ ) {
				// use the blur matrix
				for( int matrixY = 0; matrixY < 3; matrixY++ ) {
					for( int matrixX = 0; matrixX < 3; matrixX++ ) {
						helperColor = new Color( getRGB( x - 1 + matrixX, y - 1 + matrixY ) );
						r += (float)helperColor.getRed() * blurMatrix[matrixY][matrixX];
						g += (float)helperColor.getGreen() * blurMatrix[matrixY][matrixX];
						b += (float)helperColor.getBlue() * blurMatrix[matrixY][matrixX];
					}
				}
				r /= 256f;
				g /= 256f;
				b /= 256f;
				helperColor = new Color(r, g, b);
				setRGB( x, y, helperColor.getRGB() );
			}
		}
	}

}
