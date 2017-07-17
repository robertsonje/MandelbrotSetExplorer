/**
* This is the source file for the Painter object in the Mandelbrot explorer
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Painter extends JPanel {

	/**
	 * This is a class meant to assist with drawing images.
	 * Contains functions for drawing pixels, lines, shapes,
	 * and setting colors. Also clears the screen for refreshing.
	 */
	private static final long serialVersionUID = 7572637374269286731L;
	private PainterImage painterImage;
	private MandelbrotAlgorithm mandelbrotSet;
	private int height;
	private int width;

	public Painter ( MainWindow owner ) {
		/**
		 * Constructor for the painter object. A new color palette is generated.
		 *
		 * @see         JPanel
		 */
		// initialize the painter image
		painterImage = new PainterImage( owner );
		width = (int)MainWindow.DEFAULT_SIZE.getX();
		height = (int)MainWindow.DEFAULT_SIZE.getY();
		mandelbrotSet = new MandelbrotAlgorithm( owner );
		mandelbrotSet.generateIterations();
		setMandelbrotImage();
		//painterImage.applyGaussianBlur();
	}

	public void setMandelbrotImage() {
		/**
		 * Constructor for the painter object. A new color palette is generated.
		 *
		 * @see         JPanel
		 */
		Color currentColor;
		Color color1;
		Color color2;
		double iterationReal;
		for( int y = 0; y < height; y++ ) {
			for( int x = 0; x < width; x++ ) {
				if ( mandelbrotSet.isSmooth() ) {
					iterationReal = mandelbrotSet.getIterationReal( x, y );
					color1 = painterImage.getIterationColor( (int)Math.floor( iterationReal ) );
					color2 = painterImage.getIterationColor( (int)Math.floor( iterationReal ) + 1 );
					painterImage.setColor( interpolateColor( color1, color2, iterationReal % 1.0d ) );
					painterImage.drawPixel(x, y);
				} else {
					currentColor = painterImage.getIterationColor( mandelbrotSet.getIteration( x, y ) );
					painterImage.setColor( currentColor );
					painterImage.drawPixel(x, y);
				}
			}
		}
	}

	public static Color interpolateColor( Color color1, Color color2, double bias ) {
		/**
		 * Uses linear interpolation to generate the mid color between two colors given a bias.
		 *
		 * @param	color1		The first color
		 * @param	color2		The second color
		 * @param	bias		The bias of the color, i.e. how much of the second color should there
		 * 						be compared to the first color.
		 * @return			The interpolated color
		 */
		int r = (int)( ( 1.0 - bias )*(double)color1.getRed() + bias*(double)color2.getRed() );
		int g = (int)( (1.0 - bias)*(double)color1.getGreen() + bias*(double)color2.getGreen() );
		int b = (int)( (1.0 - bias)*(double)color1.getBlue() + bias*(double)color2.getBlue() );

		// Bounds checks
		if ( r < 0 ) {
			r = 0;
		}
		if ( g < 0 ) {
			g = 0;
		}
		if ( b < 0 ) {
			b = 0;
		}

		if ( r > 255 ) {
			r = 255;
		}
		if ( g > 255 ) {
			g = 255;
		}
		if ( b > 255 ) {
			b = 255;
		}
		return new Color( r, g, b );
	}

	public void generateMandelbrotImage() {
		/**
		 * Creates the Mandelbrot images by generating the iterations and displaying it.
		 *
		 */
		mandelbrotSet.generateIterations();
		setMandelbrotImage();
		//painterImage.applyGaussianBlur();
	}

	public void paint( Graphics g ) {
		/**
		 * Main function for refreshing the screen.
		 *
		 * @param  g	Graphics object for drawing to the window.
		 */
	    g.drawImage(painterImage, 0, 0, this);
	}

	public BufferedImage getImage() {
		/**
		 * Returns the internal image in BufferedImage format.
		 *
		 * @return		The image drawn by the Painter object.
		 */
		return painterImage;
	}

	public double getIterations( int x, int y ) {
		/**
		 * Returns the number of iterations at a certain point on the image.
		 *
		 * @param	x	The image X coordinate
		 * @param	y	The image Y coordinate
		 * @return		The number of iterations at that point.
		 */
		if ( mandelbrotSet.isSmooth() ) {
			return mandelbrotSet.getIterationReal( x, y );
		} else {
			return (double)mandelbrotSet.getIteration( x, y );
		}
	}
}
