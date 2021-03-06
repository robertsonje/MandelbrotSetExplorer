/**
* This file determines the Matrix object and all of its math operations.
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017 
*/

package mandelbrotGenerator;

public class MathMatrix {
	/**
	 * This is a class that represents a vector in 3D space.
	 * It has 4 arrays of 4 elements each.
	 * 
	 * Remember, notation is [y][x]
	 */
	private double[][] mat3;

	public MathMatrix( double[][] numbers ) {
		/**
		 * Constructor for the MathMatrix. This allows for input of a
		 * two-dimensional array of numbers.
		 * 
		 * @param  number		Two-dimensional array of numbers. Must be 4x4.
		 */
		// bounds check
		
		// create the matrix
		mat3 = new double[4][4];
		for ( int y = 0; y < mat3.length; y++ ) {
			for ( int x = 0; x < mat3[y].length; x++) {
				mat3[y][x] = numbers[y][x];
			}
		}
		// regardless of input, last element should always be 1.0d
		mat3[3][3] = 1.0d;
	}
	
	public MathMatrix( MathVector[] vectors ) {
		/**
		 * Constructor for the MathMatrix. This allows for input of an
		 * array of vectors.
		 * 
		 * @param  number		The array of vectors. Must be 4x4.
		 */
		mat3 = new double[4][4];
		for ( int x = 0; x < mat3.length; x++ ) {
			mat3[0][x] = vectors[x].getX();
			mat3[1][x] = vectors[x].getY();
			mat3[2][x] = vectors[x].getZ();
			mat3[3][x] = 0.0;
		}
		// regardless of input, last element should always be 1.0d
		mat3[3][3] = 1.0d;
	}

	public MathMatrix( MathMatrix a ) {
		/**
		 * Copy constructor for the MathMatrix
		 * 
		 * @param  a			MathMatrix to copy from
		 */
		this( a.getArray() );
	}

	public String toString() {
		/**
		 * Parses the matrix to a string and returns it.
		 *
		 * @return		The string form of the MathMatrix
		 */
		String returnString = "";
		for ( int y = 0; y < mat3.length; y++ ) {
			returnString += "[ " + mat3[y][0]
			+ " " + mat3[y][1]
			+ " " + mat3[y][2]
			+ " " + mat3[y][3] + " ]\n";
		}
		return returnString;
	}
	
	public double[][] getArray() {
		/**
		 * Gets the two-dimensional matrix array.
		 *
		 * @return		The two-dimensional array
		 */
		return mat3;
	}
	
	public double get( int x, int y ) {
		/**
		 * Gets a number from the matrix
		 *
		 * @param	x	The horizontal index of the matrix. Must be above 0 and below 4.
		 * @param	y	The vertical index of the matrix. Must be above 0 and below 4.
		 * @return		The value at the index
		 */
		if ( ( x < 0 || x >= 4) || ( y < 0 || y >= 4 ) ) {
			ErrorHandler.printErrorMessage( "Index is out of bounds" );
			return 0.0d;
		} else {
			return mat3[y][x];
		}
	}
	public void set( int x, int y, double value ) {
		/**
		 * Sets a number from the matrix to a value 
		 *
		 * @param	x	The horizontal index of the matrix. Must be above 0 and below 4.
		 * @param	y	The vertical index of the matrix. Must be above 0 and below 4.
		 * @param	value	The number to set the value to at the index
		 */
		if ( ( x < 0 || x >= 4) || ( y < 0 || y >= 4 ) ) {
			ErrorHandler.printErrorMessage( "Index is out of bounds" );
		} else {
			mat3[y][x] = value;
		}
	}

	public MathMatrix add( MathMatrix a ) {
		/**
		 * Adds two matrices together.
		 *
		 * @param  	a	The matrix to add with
		 * @return		The resulting matrix
		 */
		// create the matrix
		for ( int y = 0; y < mat3.length; y++ ) {
			for ( int x = 0; x < mat3[y].length; x++) {
				mat3[y][x] += a.get( x, y );
			}
		}
		// regardless of input, last element should always be 1.0d
		mat3[3][3] = 1.0d;
		return this;
	}
	
	public MathMatrix subtract( MathMatrix a ) {
		/**
		 * Subtracts two matrices from each other.
		 *
		 * @param  	a	The matrix to subtract with
		 * @return		The resulting matrix
		 */
		// create the matrix
		for ( int y = 0; y < mat3.length; y++ ) {
			for ( int x = 0; x < mat3[y].length; x++) {
				mat3[y][x] -= a.get( x, y );
			}
		}
		// regardless of input, last element should always be 1.0d
		mat3[3][3] = 1.0d;
		return this;
	}
	
	public MathMatrix multiply( double a ) {
		/**
		 * Multiplies the matrix with a single value
		 *
		 * @param  a		The number to multiply with
		 * @return		The resulting matrix
		 */
		// create the matrix
		for ( int y = 0; y < mat3.length; y++ ) {
			for ( int x = 0; x < mat3[y].length; x++) {
				mat3[y][x] *= a;
			}
		}
		// regardless of input, last element should always be 1.0d
		mat3[3][3] = 1.0d;
		return this;
	}
	
	public MathMatrix multiply( MathMatrix a ) {
		/**
		 * Multiplies the matrix. Since matrix multiplication is non-commutative,
		 * assuming the current matrix is b, the order of operations is b * a.
		 *
		 * @param  a		The matrix to multiply with
		 * @return		The resulting matrix
		 */
		MathMatrix temp = new MathMatrix( this );
		double productTemp = 0.0d;
		// create the matrix
		for ( int y = 0; y < mat3.length; y++ ) {
			for ( int x = 0; x < mat3[y].length; x++) {
				productTemp = 0.0d;
				for ( int i = 0; i < 4; i++ ) {
					productTemp += ( temp.get( i, y ) * a.get( x, i ) );
				}
				mat3[y][x] = productTemp;
			}
		}
		// regardless of input, last element should always be 1.0d
		//mat3[3][3] = 1.0d;
		return this;
	}
	
	public MathMatrix divide( double a ) {
		/**
		 * Divides the matrix with a single value. Every number is
		 * divided with the parameter.
		 *
		 * @param  a		The number to divide with
		 * @return		The resulting matrix
		 */
		// create the matrix
		for ( int y = 0; y < mat3.length; y++ ) {
			for ( int x = 0; x < mat3[y].length; x++) {
				mat3[y][x] /= a;
			}
		}
		// regardless of input, last element should always be 1.0d
		mat3[3][3] = 1.0d;
		return this;
	}
	
}
