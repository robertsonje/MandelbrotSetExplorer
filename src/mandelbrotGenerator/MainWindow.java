/**
* This is the MainWindow class, which shows the Mandelbrot window and
* contains the main functionality of the program.
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class MainWindow extends JFrame
	implements MouseMotionListener, MouseWheelListener {
	/**
	 * The main window that contains the functions for producing the
	 * Mandelbrot set and creating the pictures.
	 */
	private static final long serialVersionUID = 1L;
	// Constants
	public static final String WINDOW_TITLE = "Mandelbrot Set Explorer";
	public static final int STATUS_BAR_HEIGHT = 64;
	public static final int ITERATIONS = 5000;
	public static final MathVector DEFAULT_SIZE = new MathVector( 720, 480, 0 );
	public static final MathVector DEFAULT_ORIGIN = new MathVector( DEFAULT_SIZE );
	public static MathVector VIEW_SIZE;
	private int mouseX = 0;
	private int mouseY = 0;
	private MathMatrix viewMatrix;		// World to camera coordinates
	private MathMatrix modelMatrix;		// World to Mandelbrot coordinates
	private MathMatrix reverseModelMatrix;	// Mandelbrot to world coordinates
	private MathMatrix combinedMatrix;
	private int zoomLevel = 1;
	private MathVector center = new MathVector( 0, 0, 0 );
	private MathVector mouseLocation = new MathVector( 0, 0, 0 );
	private MathVector cartesian = new MathVector( 0, 0, 0 );
	private Painter painter;
	private JPanel statusBar;
	private JLabel statusMessageX;
	private JLabel statusMessageY;
	private JLabel statusMessageZoom;
	private JLabel statusMessageIterations;
	private ProgramMenu programMenu;
	private CoordinateInputDialog inputDialog;
	private int colorLoops = 80;
	//private DecimalFormat coordinateFormatter;

	// Methods
	public MainWindow() {
		/**
		 * Constructor for the MainWindow object. The window is initialized here,
		 * along with the menu, status bar, etc.
		 *
		 * @see         JFrame
		 */
		// Constructor
		super( WINDOW_TITLE );

		// Vector and matrix initialization
		double[][] modelMatrix_d =	{{3.0d/DEFAULT_SIZE.getX(), 0, 0, -2},
			 	 							{0, -2.0d/DEFAULT_SIZE.getY(), 0, 1},
			 	 							{0, 0, 0, 0},
			 	 							{0, 0, 0, 1}};
		double[][] reverseModelMatrix_d =	{{DEFAULT_SIZE.getX() / 3.0d, 0, 0, 2.0d * DEFAULT_SIZE.getX() / 3.0d},
											{0, DEFAULT_SIZE.getY() / -2.0d, 0, DEFAULT_SIZE.getY() / 2.0d},
											{0, 0, 0, 0},
											{0, 0, 0, 1}};
		double[][] viewMatrix_d =	{{1, 0, 0, 0},
											{0, 1, 0, 0},
											{0, 0, 1, 0},
											{0, 0, 0, 1}};
		modelMatrix = new MathMatrix( modelMatrix_d );
		viewMatrix = new MathMatrix( viewMatrix_d );
		reverseModelMatrix = new MathMatrix( reverseModelMatrix_d );
		combinedMatrix = new MathMatrix( modelMatrix_d );
		VIEW_SIZE = new MathVector( DEFAULT_SIZE );
		VIEW_SIZE.divide( 2d );
		center = new MathVector( DEFAULT_SIZE );
		center.divide( 2d );
		center.multiply( combinedMatrix );

		// create the main window and make sure it closes as it should
		setLayout( new BorderLayout() );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		pack();

		// create the menu
		programMenu = new ProgramMenu( this );
		setJMenuBar( programMenu );

		// Add the painter object to the frame so it displays the Mandelbrot set
		painter = new Painter( this );
		add( painter );
        painter.addMouseMotionListener( this );
        painter.addMouseWheelListener( this );

		// Add the status bars
		statusBar = new JPanel();
		statusBar.setBorder( new BevelBorder( BevelBorder.LOWERED ) );
		statusBar.setPreferredSize( new Dimension( getWidth(), STATUS_BAR_HEIGHT ) );
		statusBar.setLayout( new BoxLayout(statusBar, BoxLayout.Y_AXIS ) );
		statusMessageX = new JLabel( "Mandelbrot Set Explorer" );
		statusMessageY = new JLabel( "by Jehrick Robertson" );
		statusMessageZoom = new JLabel( "June 8, 2017" );
		statusMessageIterations = new JLabel( "" );
		statusMessageX.setHorizontalAlignment( SwingConstants.LEFT );
		statusMessageY.setHorizontalAlignment( SwingConstants.LEFT );
		statusMessageZoom.setHorizontalAlignment( SwingConstants.LEFT );
		statusMessageIterations.setHorizontalAlignment( SwingConstants.LEFT );
		statusBar.add( statusMessageX, BorderLayout.SOUTH);
		statusBar.add( statusMessageY, BorderLayout.SOUTH);
		statusBar.add( statusMessageZoom, BorderLayout.SOUTH);
		statusBar.add( statusMessageIterations, BorderLayout.SOUTH);
		add( statusBar, BorderLayout.SOUTH );
		inputDialog = new CoordinateInputDialog( this );

		//show the window
		setVisible( true );
		setSize( (int)DEFAULT_SIZE.getX(), (int)DEFAULT_SIZE.getY() + STATUS_BAR_HEIGHT + STATUS_BAR_HEIGHT );
	}

	private void updateStatusBar() {
		/**
		 * Makes sure the status bar has been updated with the latest information.
		 */
        statusMessageX.setText( "X coordinate:\t " + cartesian.getX() );
        statusMessageY.setText( "Y coordinate:\t " + cartesian.getY() );
        if (zoomLevel == 1) {
            statusMessageZoom.setText( "Full size" );
        } else {
            statusMessageZoom.setText( "Zoomed in by " + (long)Math.pow( 2, zoomLevel - 1) );
        }
        statusMessageIterations.setText( "Iterations for current location:\t " + painter.getIterations( mouseX, mouseY ) );
	}

	public void printAuthorMessage() {
		System.out.println( "********************************************************************************" );
		System.out.println( "*                                                                              *" );
		System.out.println( "*        Interactive Mandelbrot Generator                                      *" );
		System.out.println( "*        Version 1.0 (June 8, 2017)                                            *" );
		System.out.println( "*        By Jehrick Robertson                                                  *" );
		System.out.println( "*                                                                              *" );
		System.out.println( "********************************************************************************" );
	}

	private void calculateMousePosition( int px, int py ) {
		/**
		 * converts the mouse coordinates to Mandelbrot coordinates and
		 * updates the status bar.
		 *
		 * @param	px			Horizontal location of the mouse
		 * @param	py			Vertical location of the mouse
		 */
		if( px < (int)DEFAULT_SIZE.getX() && py < (int)DEFAULT_SIZE.getY() ) {
			// Calculate the Mandelbrot coords
			mouseX = px;
			mouseY = py;
			mouseLocation.setX( mouseX );
			mouseLocation.setY( mouseY );
			cartesian.setX(px);
			cartesian.setY(py);
			cartesian.multiply( combinedMatrix );

			// Format the numbers so that the locations aren't
			updateStatusBar();
		}
	}

	public MathVector getCenter() {
		/**
		 * Returns the center coordinate of the screen in Mandelbrot
		 * coordinate form.
		 *
		 * @return	The center coordinate vector
		 */
		return center;
	}

	public int getZoomLevel() {
		/**
		 * Returns the zoom level
		 *
		 * @return	The current zoom level
		 */
		return zoomLevel;
	}

	public MathMatrix getTransformationMatrix() {
		/**
		 * Returns the transformation matrix for translating absolute
		 * coordinates to Mandelbrot coordinates
		 *
		 * @return	The combined transformation matrix
		 */
		return combinedMatrix;
	}

	public void absoluteZoom( double px, double py, long zoomAmount ) {
		/**
		 * Calculates the zooming coordinates given the absolute zoom
		 * level and changes the viewing matrix so that the screen
		 * zooms in properly.
		 *
		 * @param	px		Horizontal location of the zoom point
		 * @param	py		Vertical location of the zoom point
		 * @param	zoomAmount	The absolute zoom level
		 */
		MathVector zoomPoint = new MathVector( (double)px, (double)py, 0 );
		zoomPoint.multiply( reverseModelMatrix );
		MathVector zoomDimensions = new MathVector( 0, 0, 0 );

		//set the zoom factor
		zoomLevel = (int)Math.floor( Math.log( zoomAmount ) / Math.log( 2 ) );

		//calculate the zoom point coordinates
		zoomDimensions.setX( DEFAULT_SIZE.getX() / (2d * Math.pow( 2, zoomLevel - 1 ) ) );
		zoomDimensions.setY( DEFAULT_SIZE.getY() / (2d * Math.pow( 2, zoomLevel - 1 ) ) );

		//configure the view matrix
		viewMatrix.set( 0, 0, 1.0d/Math.pow( 2, zoomLevel - 1) );
		viewMatrix.set( 1, 1, 1.0d/Math.pow( 2, zoomLevel - 1)  );
		viewMatrix.set( 3, 0, zoomPoint.getX() - zoomDimensions.getX() );
		viewMatrix.set( 3, 1, zoomPoint.getY() - zoomDimensions.getY() );
		combinedMatrix = new MathMatrix( modelMatrix );
		combinedMatrix.multiply( viewMatrix );
		center = new MathVector( DEFAULT_SIZE );
		center.divide( 2d );
		center.multiply( combinedMatrix );
		updateStatusBar();
		painter.generateMandelbrotImage();
		painter.repaint();
	}

	private void calculateZoom( int px, int py, boolean direction ) {
		/**
		 * Calculates the zooming coordinates and changes the viewing matrix
		 * so that the screen zooms in properly.
		 *
		 * @param  px			Horizontal location of the zoom point
		 * @param	py			Vertical location of the zoom point
		 * @param	direction	Direction of the zoom
		 */
		if( px < (int)DEFAULT_SIZE.getX() && py < (int)DEFAULT_SIZE.getY() ) {
			MathVector zoomPoint = new MathVector( (double)px, (double)py, 0 );
			zoomPoint.multiply( viewMatrix );
			MathVector zoomDimensions = new MathVector( 0, 0, 0 );
			//increase the zoom factor
			if ( direction == true ) {
				zoomLevel++;
			} else {
				if (zoomLevel != 1)
				zoomLevel--;
			}
			//calculate the zoom point coordinates
			zoomDimensions.setX( DEFAULT_SIZE.getX() / (2d * Math.pow( 2, zoomLevel - 1) ) );
			zoomDimensions.setY( DEFAULT_SIZE.getY() / (2d * Math.pow( 2, zoomLevel - 1) ) );
			//configure the view matrix
			viewMatrix.set( 0, 0, 1.0d/Math.pow( 2, zoomLevel - 1) );
			viewMatrix.set( 1, 1, 1.0d/Math.pow( 2, zoomLevel - 1)  );
			viewMatrix.set( 3, 0, zoomPoint.getX() - zoomDimensions.getX() );
			viewMatrix.set( 3, 1, zoomPoint.getY() - zoomDimensions.getY() );
			combinedMatrix = new MathMatrix( modelMatrix );
			combinedMatrix.multiply( viewMatrix );
			center = new MathVector( DEFAULT_SIZE );
			center.divide( 2d );
			center.multiply( combinedMatrix );
			updateStatusBar();
			painter.generateMandelbrotImage();
			painter.repaint();
			//repaint
		}
	}

	public int getColorLoops() {
		/**
		 * Gets the amount of window gradient color loops.
		 *
		 * @return	the number of times the gradient loops itself
		 */
		return colorLoops;
	}

	public void showCoordinateInputDialog() {
		/**
		 * Shows the dialog for entering Mandelbrot coordinates.
		 */
    	inputDialog.updateCoordinates();
    	inputDialog.setVisible( true );
	}

	public void hideCoordinateInputDialog() {
		/**
		 * Hides the dialog for entering Mandelbrot coordinates.
		 */
    	inputDialog.updateCoordinates();
    	inputDialog.setVisible( false );
	}


	public BufferedImage getMandelbrotImage() {
		/**
		 * Gets the current Mandelbrot image
		 *
		 * @return	the Mandelbrot image information
		 */
		return painter.getImage();
	}

    public void mouseMoved( MouseEvent e ) {
		/**
		 * Mouse moved event handler. Sends the event to the calculateMousePosition function.
		 *
		 * @param  g	Mouse moving event that contains the mouse coordinates.
		 */
    	calculateMousePosition( e.getX(), e.getY() );
    }

    public void mouseDragged( MouseEvent e ) {
		/**
		 * Mouse dragged event handler. Sends the event to the CalculateMousePosition function.
		 *
		 * @param  g	Mouse dragging event that contains the mouse coordinates.
		 */
    	calculateMousePosition( e.getX(), e.getY() );
    }

    public void mouseWheelMoved( MouseWheelEvent e ) {
		/**
		 * Mouse moved event handler. Sends the event to the calculateZoom function.
		 *
		 * @param  g	Mouse wheel event that contains the mouse coordinates and direction.
		 */
    	calculateZoom( e.getX(), e.getY(), ( e.getUnitsToScroll() >= 0 ) );
    }

}
