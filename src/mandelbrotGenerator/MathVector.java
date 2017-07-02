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
		 * @param  x	The horizontal (x) value
		 * @param  y	The vertical (y) value
		 * @param  z	The distance (z) value
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
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[0] = 0.0;
		vec3[1] = 0.0;
		vec3[2] = 0.0;
		vec3[3] = 1.0;
	}
	
	public void setX( double x ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[0] = x;
	}

	public void setY( double y ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[1] = y;
	}

	public void setZ( double z ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[2] = z;
	}
	
	public double getX() {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		return vec3[0];
	}

	public double getY() {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		return vec3[1];
	}

	public double getZ() {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		return vec3[2];
	}
	
	public double getLength() {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		return Math.sqrt(
				( vec3[0] * vec3[0] ) + 
				( vec3[1] * vec3[1] ) + 
				( vec3[2] * vec3[2] ) );
	}
	
	public double getSumOfSquares() {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		return ( ( vec3[0] * vec3[0] ) + 
				  ( vec3[1] * vec3[1] ) + 
				  ( vec3[2] * vec3[2] ) );
	}
	
	public MathVector add( MathVector a ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[0] += a.getX();
		vec3[1] += a.getY();
		vec3[2] += a.getZ();
		return this;
	}
	
	public MathVector subtract( MathVector a ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[0] -= a.getX();
		vec3[1] -= a.getY();
		vec3[2] -= a.getZ();
		return this;
	}
	
	public MathVector multiply( double a ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[0] *= a;
		vec3[1] *= a;
		vec3[2] *= a;
		return this;
	}
	
	public MathVector divide( double a ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		vec3[0] /= a;
		vec3[1] /= a;
		vec3[2] /= a;
		return this;
	}
	
	public MathVector normalize() {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		double norm = getLength();
		vec3[0] /= norm;
		vec3[1] /= norm;
		vec3[2] /= norm;
		return this;
	}
	
	public double dotProduct( MathVector a ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
		 */
		return	( ( vec3[0] * a.getX() ) + 
				  ( vec3[1] * a.getY() ) + 
				  ( vec3[2] * a.getZ() ) );
	}
	
	public MathVector crossProduct( MathVector b ) {
		/**
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
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
		 * Main function. 
		 *
		 * @param  args			Program arguments, although there are none.
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