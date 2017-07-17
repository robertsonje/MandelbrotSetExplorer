/**
* This is the color palette used for determining the color of the Mandelbrot
* set.
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

import java.util.ArrayList;
import java.awt.Color;

public class ColorPalette {
	/**
	 * Class that holds the colors for painting the Mandelbrot set
	 */
	// variables
	private MainWindow owner;
	private int numColors;
	private ArrayList<Color> colorLookUpTable;
	private ColorGradient gradient;
	private final int ERROR_COEFFICIENT = 50;

	// methods

	public ColorPalette( MainWindow owner, int numColors, ColorGradient gradient ) {
		/**
		 * Constructor for the ColorPalette object.
		 *
		 * @param  numcolors	number of colors to initialize.
		 */
		this.owner = owner;
		this.numColors = numColors;
		this.gradient = gradient;
		colorLookUpTable = new ArrayList<Color>();
		GeneratePalette();
	}

	public int getNumColors() {
		/**
		 * Returns the number of colors in the palette.
		 *
		 * @return	The number of colors
		 */
		return numColors;
	}

	public Color getColor( int index ) {
		/**
		 * Gets the color in the palette at the index.
		 *
		 * @return	the color at the index, or black if out of bounds
		 */
		if ( index >= 0 && index < numColors + ERROR_COEFFICIENT ) {
			return colorLookUpTable.get( index );
		}
		else {
			System.err.println( "Error in ColorPalette.getColor(): Index " + index + " out of range" );
			return Color.black;
		}
	}

	public void GeneratePalette() {
		/**
		 * Creates a palette from the number of colors.
		 */
		// Make a rainbow if the gradient is one
		Color lastColor = Color.black;
		if ( gradient.checkIfRainbow() )
		{
			final float hueIncrement = (float)owner.getColorLoops()/(float)numColors;
			float hue = 0.0f;
			for ( int i = 0; i < numColors; i++ ) {
				hue = hueIncrement*(float)i;
				// Use the hue to set a rainbow of colors
				colorLookUpTable.add( Color.getHSBColor( hue, 1.0f, 1.0f ) );
				hue %= 1.0f;
			}
		} else {
		// Else, generate the palette from the colors
			float stepSize = (float)( gradient.getNumColors() * owner.getColorLoops() )  / (float)numColors;
			float gradientIndexReal = 0.0f;
			int firstColorIndex = 0;
			int secondColorIndex = 0;
			int maxGradientSize = gradient.getNumColors();
			for ( int i = 0; i < numColors; i++ ) {
				firstColorIndex = (int)Math.floor( gradientIndexReal ) % maxGradientSize;
				secondColorIndex = ( firstColorIndex + 1 ) % maxGradientSize;
				lastColor = Painter.interpolateColor(
						gradient.getColor( firstColorIndex ),
						gradient.getColor( secondColorIndex ),
						(double)( gradientIndexReal % 1.0f ) );
				colorLookUpTable.add( lastColor );
				gradientIndexReal += stepSize;
			}
		}
		// clean up any remaining colors
		for( int j = colorLookUpTable.size(); j < numColors; j++ ) {
			colorLookUpTable.add( lastColor );
		}
		// Make sure that there aren't any leftover colors to cause any problems
		for( int k = 0; k < ERROR_COEFFICIENT; k++ ) {
			colorLookUpTable.add( Color.BLACK );
		}
		// First color is black
		colorLookUpTable.set( 0, Color.BLACK );
	}

}
