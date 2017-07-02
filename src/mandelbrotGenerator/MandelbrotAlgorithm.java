/**
* This is the class that generates the Mandelbrot picture.
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

import java.util.ArrayList;

public class MandelbrotAlgorithm {
	/**
	 * This is a class meant to assist with generating the Mandelbrot set.
	 */

	public static final double LOG_OF_TWO = 0.6931471805599453;
	private MainWindow owner;
	private MathVector cartesian = new MathVector( 0, 0, 0 );	// Mandelbrot coordinate vector
	private MathVector position;								// Current position for mandelbrot calculation
	private int iteration;										// Mandelbrot iteration counter
	private double iterationReal;								// Mandelbrot iteration counter, double
	private int maxIterations = MainWindow.ITERATIONS;			// Maximum amount of iterations allowed
	private double maxIterationsReal = (double)MainWindow.ITERATIONS;			// Maximum amount of iterations allowed, double
	private ArrayList<Integer> iterationList;					// Number of iterations for each pixel
	private ArrayList<Double> iterationListReal;				// Number of iterations for each pixel
	private int height;
	private int width;
	private boolean smooth = true;

	public MandelbrotAlgorithm( MainWindow owner ) {
		this.owner = owner;
		iterationList = new ArrayList<Integer>();
		iterationListReal = new ArrayList<Double>();
		position = new MathVector( 0, 0, 0 );
		width = (int)MainWindow.DEFAULT_SIZE.getX();
		height = (int)MainWindow.DEFAULT_SIZE.getY();
	}

	public boolean isSmooth() {
		/**
		 * Gets the Mandelbrot smoothness property
		 *
		 * @return	The smoothness property
		 */
		return smooth;
	}

	public void setSmooth( boolean smooth ) {
		/**
		 * Sets the Mandelbrot smoothness property
		 *
		 * @param	smooth		The smoothness property it should be set to
		 */
		this.smooth = smooth;
	}

	public void generateIterations() {
		/**
		 * Creates the iterations for the Mandelbrot Set.
		 */
		double xtemp;

		if ( smooth ) {
			iterationReal = 0;
			double logzn;
			double nu;

			// Clear the list of iterations
			iterationListReal.clear();
			for( int py = 0; py < height; py++ ) {
				for( int px = 0; px < width; px++ ) {
					cartesian.setX(px);
					cartesian.setY(py);
					cartesian.multiply( owner.getTransformationMatrix() );
					position.clear();
					while ( position.getSumOfSquares() < 256 && iterationReal < maxIterationsReal ) {
						xtemp = position.getX()*position.getX() - position.getY()*position.getY() + cartesian.getX();
						position.setY((2.0d * position.getX() * position.getY()) + cartesian.getY());
						position.setX(xtemp);
						iterationReal += 1.0d;
					}
					// this is to prevent floating point errors
					if (iterationReal < maxIterationsReal ) {
						logzn = Math.log( position.getSumOfSquares() );
						nu = Math.log( logzn / LOG_OF_TWO ) / LOG_OF_TWO;
						iterationReal = iterationReal + 1.0d - nu;
					}
					iterationListReal.add( iterationReal );
					iterationReal = 0;
				}
			}
		} else {
			iteration = 0;

			// Clear the list of iterations
			iterationList.clear();
			for( int py = 0; py < height; py++ ) {
				for( int px = 0; px < width; px++ ) {
					cartesian.setX(px);
					cartesian.setY(py);
					cartesian.multiply( owner.getTransformationMatrix() );
					position.clear();
					while ( position.getSumOfSquares() < 4 && iteration < maxIterations ) {
						xtemp = position.getX()*position.getX() - position.getY()*position.getY() + cartesian.getX();
						position.setY((2.0d * position.getX() * position.getY()) + cartesian.getY());
						position.setX(xtemp);
						iteration++;
					}
					iterationList.add( iteration );
					iteration = 0;
				}
			}
		}
	}

	public int getIteration( int x, int y ) {
		/**
		 * Main function for refreshing the screen.
		 *
		 * @param  g	Graphics object for drawing to the window.
		 */
		int index = ( y * width ) + x;
		if ( !smooth ) {
			return iterationList.get( index );
		} else {
			return 0;
		}
	}

	public double getIterationReal( int x, int y ) {
		/**
		 * Main function for refreshing the screen.
		 *
		 * @param  g	Graphics object for drawing to the window.
		 */
		int index = ( y * width ) + x;
		if ( smooth ) {
			return iterationListReal.get( index );
		} else {
			return 0.0d;
		}
	}
}
