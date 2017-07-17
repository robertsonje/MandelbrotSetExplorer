/**
* This file determines the vector object and all of its math operations.
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

public class MathVector {
	/**
	 * This is a class that represents a vector in 3D space.
	 * It has 4 elements.
	 */
	private double[] vec3;
	private boolean usesUnitVectors = false;

	public MathVector( double x, double y, double z ) {
		/**
		 * Constructor for the MathVector object. This uses 3 numbers
		 * for initialization. It does not use unit vectors.
		 *
		 * @param  x	The horizontal (x) value
		 * @param  y	The vertical (y) value
		 * @param  z	The distance (z) value
		 */
		vec3 = new double[4];
		vec3[0] = x;
		vec3[1] = y;
		vec3[2] = z;
		vec3[3] = 1.0d;
	}

	public MathVector( double x, double y, double z, boolean usesUnitVectors ) {
		/**
		 * Constructor for the MathVector object. This uses 3 numbers
		 * for initialization, and a boolean value for determining
		 *
		 * @param  x				The horizontal (x) value
		 * @param  y				The vertical (y) value
		 * @param  z				The distance (z) value
		 * @param	usesUnitVectors	Decides whether or not to use unit vectors when parsing to string
		 */
		vec3 = new double[4];
		vec3[0] = x;
		vec3[1] = y;
		vec3[2] = z;
		vec3[3] = 1.0d;
		this.usesUnitVectors = usesUnitVectors;
	}

	public MathVector( MathVector a ) {
		/**
		 * Copy contructor for the MathVector object.
		 *
		 * @param  a	The MathVector to copy from.
		 */
		this( a.getX(), a.getY(), a.getZ(), a.getUnitVectorMode() );
	}

	@Override public final Object clone() throws CloneNotSupportedException {
		/**
		 * The MathVector class does not support Clone().
		 */
		throw new CloneNotSupportedException();
	}

	public String toString() {
		/**
		 * Parses the vector to a string and returns it. If it is in
		 * unit vector form, it will be displayed as such.
		 *
		 * @return		The string form of the MathVector
		 */
		if ( usesUnitVectors == true) {
			String xString = "";
			String yString = "";
			String zString = "";
			double absY = Math.abs( vec3[1] );
			double absZ = Math.abs( vec3[2] );
			int numVectors = 0;
			if ( vec3[0] != 0.0d ) {
				xString = vec3[0] + "i";
				numVectors++;
			}
			if ( vec3[1] != 0.0d ) {
				if ( numVectors != 0 ) {
					if ( vec3[1] >= 0.0d ) {
						yString = " + " + absY + "j";
					} else {
						yString = " - " + absY + "j";
					}
				} else {
					yString = vec3[1] + "j";
				}
				numVectors++;
			}
			if ( vec3[2] != 0.0d ) {
				if ( numVectors != 0 ) {
					if ( vec3[2] >= 0.0d ) {
						zString = " + " + absZ + "k";
					} else {
						zString = " - " + absZ + "k";
					}
				} else {
					zString = vec3[2] + "k";
				}
				numVectors++;
			}
			if ( numVectors != 0 ) {
				return xString + yString + zString;
			} else {
				return "null vector";
			}
		}
		else {
			return "< " + vec3[0] + "\t\t\t\t " + vec3[1] + "\t\t\t\t " + vec3[2] + " >";
		}
	}

	public void setUnitVectorMode( boolean usesUnitVectors ) {
		/**
		 * Sets the unit vector mode.
		 *
		 * @param  usesUnitVectors	Boolean to determine usage of unit vectors
		 */
		this.usesUnitVectors = usesUnitVectors;
	}

	public boolean getUnitVectorMode() {
		/**
		 * Gets the unit vector mode.
		 *
		 * @return  usesUnitVectors	Boolean to determine usage of unit vectors
		 */
		return this.usesUnitVectors;
	}

	public void clear() {
		/**
		 * Sets the x, y, and z values to zero.
		 *
		 */
		vec3[0] = 0.0;
		vec3[1] = 0.0;
		vec3[2] = 0.0;
		vec3[3] = 1.0;
	}

	public void setX( double x ) {
		/**
		 * Sets the X value to the given value.
		 *
		 * @param  x			The x value to set to.
		 */
		vec3[0] = x;
	}

	public void setY( double y ) {
		/**
		 * Sets the Y value to the given value.
		 *
		 * @param  y			The y value to set to.
		 */
		vec3[1] = y;
	}

	public void setZ( double z ) {
		/**
		 * Sets the Z value to the given value.
		 *
		 * @param  z			The z value to set to.
		 */
		vec3[2] = z;
	}

	public double getX() {
		/**
		 * Gets the X value from the vector.
		 *
		 * @return  The x value of the vector.
		 */
		return vec3[0];
	}

	public double getY() {
		/**
		 * Gets the Y value from the vector.
		 *
		 * @return  The y value of the vector.
		 */
		return vec3[1];
	}

	public double getZ() {
		/**
		 * Gets the Z value from the vector.
		 *
		 * @return  The z value of the vector.
		 */
		return vec3[2];
	}

	public double getLength() {
		/**
		 * Gets the length of the value through use of the Pythagorean theorem.
		 *
		 * @return  The length of the vector.
		 */
		return Math.sqrt(
				( vec3[0] * vec3[0] ) +
				( vec3[1] * vec3[1] ) +
				( vec3[2] * vec3[2] ) );
	}

	public double getSumOfSquares() {
		/**
		 * Gets the sum of squares, which is useful for Mandelbrot calculations.
		 *
		 * @return  The sum of squares of the vector.
		 */
		return ( ( vec3[0] * vec3[0] ) +
				  ( vec3[1] * vec3[1] ) +
				  ( vec3[2] * vec3[2] ) );
	}

	public MathVector add( MathVector a ) {
		/**
		 * Adds the components of one vector to this vector.
		 * The original values are overwritten.
		 *
		 * @param  a			The vector to add with.
		 * @return				The resulting vector.
		 */
		vec3[0] += a.getX();
		vec3[1] += a.getY();
		vec3[2] += a.getZ();
		return this;
	}

	public MathVector subtract( MathVector a ) {
		/**
		 * Subtracts the components of the vector with one of another.
		 * The original values are overwritten.
		 *
		 * @param  a			The vector to subtract with.
		 * @return				The resulting vector.
		 */
		vec3[0] -= a.getX();
		vec3[1] -= a.getY();
		vec3[2] -= a.getZ();
		return this;
	}

	public MathVector multiply( double a ) {
		/**
		 * Performs scalar multiplication on a vector.
		 * The original values are overwritten.
		 *
		 * @param  a			Scalar value to multiply with.
		 * @return				The resulting vector.
		 */
		vec3[0] *= a;
		vec3[1] *= a;
		vec3[2] *= a;
		return this;
	}

	public MathVector divide( double a ) {
		/**
		 * Performs scalar division on a vector.
		 *
		 * @param  args			Scalar value to divide with.
		 * @return				The resulting vector.
		 */
		vec3[0] /= a;
		vec3[1] /= a;
		vec3[2] /= a;
		return this;
	}

	public MathVector normalize() {
		/**
		 * Normalizes the vector so that its length is 1.
		 * The original values are overwritten.
		 *
		 * @return				The normalized vector.
		 */
		double norm = getLength();
		vec3[0] /= norm;
		vec3[1] /= norm;
		vec3[2] /= norm;
		return this;
	}

	public double dotProduct( MathVector a ) {
		/**
		 * Performs the dot product of this vector and an input vector.
		 *
		 * @param  a			The vector used for performing the dot product.
		 * @return				The dot product.
		 */
		return	( ( vec3[0] * a.getX() ) +
				  ( vec3[1] * a.getY() ) +
				  ( vec3[2] * a.getZ() ) );
	}

	public MathVector crossProduct( MathVector b ) {
		/**
		 * Performs the cross product of this vector and an input vector.
		 * The original values are overwritten.
		 *
		 * @param  b			The vector used for performing the dot product.
		 * @return				The cross product vector.
		 */
		double tempX = vec3[0];
		double tempY = vec3[1];
		double tempZ = vec3[2];
		vec3[0] = ( ( tempY * b.getZ() ) - ( tempZ * b.getY() ) );
		vec3[1] = ( ( tempZ * b.getX() ) - ( tempX * b.getZ() ) );
		vec3[2] = ( ( tempX * b.getY() ) - ( tempY * b.getX() ) );
		return this;
	}

	public MathVector multiply( MathMatrix a ) {
		/**
		 * Performs a multiplication of this vector using a transformation matrix.
		 * The original values are overwritten.
		 *
		 * @param  a			The input transformation matrix.
		 * @return				The resulting vector.
		 */
		// Create a temporary array for storing values
		double[] temp = new double[4];
		for ( int i = 0; i < 4; i++) {
			temp[i] = vec3[i];
		}

		double productTemp = 0.0d;
		// create the vector
		for ( int x = 0; x < vec3.length; x++) {
			productTemp = 0.0d;
			for ( int i = 0; i < 4; i++ ) {
				productTemp += ( temp[i] * a.get( i, x ) );
			}
			vec3[x] = productTemp;
		}
		// regardless of input, last element should always be 1.0d
		//mat3[3][3] = 1.0d;
		return this;
	}
}
