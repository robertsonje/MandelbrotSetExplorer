/**
* This is the Color gradient class; it stores a number of colors for
* the Mandelbrot Set program.
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-27-2017
*/

package mandelbrotGenerator;

import java.util.ArrayList;
import java.awt.Color;

public class ColorGradient {
	/**
	 * Class that contains a number of colors for the Mandelbrot Set 
	 * color palette
	 */
	// variables
	private int numColors;
	private ArrayList<Color> colors;
	private boolean isRainbow;

	// methods

	public ColorGradient() {
		/**
		 * Constructor for the ColorGradient object.
		 */
		isRainbow = false;
		colors = new ArrayList<Color>();
	}

	public ColorGradient( boolean isRainbow ) {
		/**
		 * Constructor for the ColorGradient object.
		 *
		 * @param	isRainbow	Decides if the gradient is a rainbow
		 */
		this.isRainbow = isRainbow;
		colors = new ArrayList<Color>();
	}

	public ColorGradient( Color[] colors ) {
		/**
		 * Constructor for the ColorGradient object.
		 *
		 * @param	colors		array of colors to initialize.
		 */
		this.colors = new ArrayList<Color>();
		isRainbow = false;
		numColors = colors.length;
		if ( numColors != 0 ) {
			for ( int i = 0; i < numColors; i++ ) {
				this.colors.add( colors[i] );
			}
		}
	}

	public int getNumColors() {
		/**
		 * Returns the number of colors in the gradient
		 *
		 * @return			the number of colors
		 */
		return numColors;
	}

	public void addColor( Color colorToAdd ) {
		/**
		 * Adds a color to the gradient
		 *
		 * @param	colorToAdd	The color to add to the list
		 */
		if ( colorToAdd != null ) {
			colors.add( colorToAdd );
		}
		numColors++;
	}

	public Color getColor( int index ) {
		/**
		 * Gets a color from the color list, or black if the list is out of bounds
		 *
		 * @param	index		The color location in the list
		 * @return			The color at the index, or black if out of bounds
		 */
		// Bounds check
		if ( index >= 0 && index < numColors ) {
			return colors.get( index );
		} else {
			System.err.println( "Error in ColorGradient.getColor(): Index " + index + " is out of bounds" );
			return Color.BLACK;
		}
	}

	public boolean checkIfRainbow() {
		/**
		 * Checks if the gradient is a rainbow
		 *
		 * @return			The status of the isRainbow variable
		 */
		return isRainbow;
	}

}
