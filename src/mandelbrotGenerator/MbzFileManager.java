/**
* Source file for the MBZ file manager
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-27-2017
*/

package mandelbrotGenerator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MbzFileManager {
	/**
	 * This is a class that handles the MBZ file format, a file format
	 * that keeps track of Mandelbrot zoom points.
	 */

	private FileOutputStream fileOutput = null;
	private DataOutputStream dataOutput = null;
	private FileInputStream fileInput = null;
	private DataInputStream dataInput = null;
	private File selectedFile;
	private JFileChooser zoomPointFileChooser;
	private MainWindow owner;
	private final long HEADER = 0x408;
	private double xCoordinate;
	private double yCoordinate;
	private int zoomLevel;
	public final static String FILE_EXTENSION = ".mbz";

	public MbzFileManager( MainWindow owner ) {
		/**
		 * Constructor for the MbzFileManager object.
		 *
		 * @param  owner	The main program owner.
		 */
		// TODO Auto-generated constructor stub
		this.owner = owner;
		zoomPointFileChooser = new JFileChooser( "rc/Zoom Points" );
		zoomPointFileChooser.setAcceptAllFileFilterUsed( false );
		zoomPointFileChooser.addChoosableFileFilter( new FileNameExtensionFilter( "Mandelbrot set zoom point", "mbz" ) );
	}

	public double getXCoordinate() {
		/**
		 * Gets the X coordinate of the zoom point.
		 *
		 * @return  The x coordinate.
		 */
		return xCoordinate;
	}

	public double getYCoordinate() {
		/**
		 * Gets the Y coordinate of the zoom point.
		 *
		 * @return  The y coordinate.
		 */
		return yCoordinate;
	}

	public int getZoomLevel() {
		/**
		 * Gets the zoom level of the zoom point.
		 *
		 * @return  The zoom level.
		 */
		return zoomLevel;
	}

	public int writeZoomPoint( double xCoordinate, double yCoordinate, int zoomLevel ) {
		/**
		 * Saves the zoom point to a file.
		 *
		 * @param  xCoordinate		The X coordinate of the zoom point
		 * @param  yCoordinate		The Y coordinate of the zoom point
		 * @param  zoomLevel		The zoom level
		 * @return		The status of the function. '0' indicates the file was written without
		 * 				any problems, any other value means there was an error.
		 */
    	int dialogVal = zoomPointFileChooser.showSaveDialog( owner );
    	if ( dialogVal == JFileChooser.APPROVE_OPTION ){
    		zoomPointFileChooser.setDialogTitle("Save zoom point to where?");
			selectedFile = zoomPointFileChooser.getSelectedFile();
			try {
				this.xCoordinate = xCoordinate;
				this.yCoordinate = yCoordinate;
				this.zoomLevel = zoomLevel;

				// If the file doesn't end with an ".mbz" file extension, make it so
				String filePath = selectedFile.getPath();
				if ( !filePath.endsWith( FILE_EXTENSION ) ) {
					selectedFile = new File( filePath + FILE_EXTENSION );
				}

				fileOutput = new FileOutputStream( selectedFile );
				dataOutput = new DataOutputStream( fileOutput );

				// if file doesn't exist, then create it
				if ( !selectedFile.exists() ) {
					selectedFile.createNewFile();
				}

				dataOutput.writeLong( HEADER );
				dataOutput.writeDouble( xCoordinate );
				dataOutput.writeDouble( yCoordinate );
				dataOutput.writeInt( zoomLevel );
				fileOutput.flush();
				fileOutput.close();

			} catch ( IOException e ) {
                JOptionPane.showMessageDialog( owner, "Parse error: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE );
                return -1;
			} finally {
				try {
					if (fileOutput != null) {
						fileOutput.close();
					}
				} catch ( IOException e ) {
	                JOptionPane.showMessageDialog( owner, "Parse error: " + e.getMessage(), "Error",
	                        JOptionPane.ERROR_MESSAGE );
	                return -1;
				}
			}
    	}
    	if ( dialogVal != JFileChooser.APPROVE_OPTION ) {
        	return -1;
    	} else {
        	return 0;
    	}
	}

	public int readZoomPoint() {
		/**
		 * Reads the zoom point from a file, then sets the view to said zoom point
		 * if the read is successful.
		 *
		 * @return		The status of the function. '0' indicates the file was read without
		 * 				any problems, any other value means there was an error.
		 */
    	int dialogVal = zoomPointFileChooser.showOpenDialog( owner );
    	long header;
    	if ( dialogVal == JFileChooser.APPROVE_OPTION ){
    		zoomPointFileChooser.setDialogTitle( "Open what zoom point?" );
			selectedFile = zoomPointFileChooser.getSelectedFile();
			try {
				fileInput = new FileInputStream( selectedFile );
				dataInput = new DataInputStream( fileInput );

				// This shouldn't happen, but if the file doesn't end with an ".mbz" file extension, don't open it
				if ( !selectedFile.getPath().endsWith( FILE_EXTENSION ) ) {
					JOptionPane.showMessageDialog( null, "Read error: Selected file is not an MBZ file.", "Error",
						JOptionPane.ERROR_MESSAGE );
					return -1;
				}

				// if file exists, open it
				if ( selectedFile.exists() ) {
					header = dataInput.readLong();
					// This is to make sure it doesn't open corrupted MBZ files
					if ( header != HEADER ) {
						JOptionPane.showMessageDialog( null, "Read error: Selected file is not an MBZ file. The header is incorrect.", "Error",
							JOptionPane.ERROR_MESSAGE );
						return -1;
					} else {
						xCoordinate = dataInput.readDouble();
						yCoordinate = dataInput.readDouble();
						zoomLevel = dataInput.readInt();
					}
				}
				fileInput.close();
				System.out.println("xCoordinate = " + xCoordinate);
				System.out.println("yCoordinate = " + yCoordinate);
				System.out.println("zoomLevel = " + zoomLevel);

			} catch (IOException e) {
                JOptionPane.showMessageDialog( owner, "Parse error: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE );
                return -1;
			} finally {
				try {
					if (fileOutput != null) {
						fileOutput.close();
					}
				} catch (IOException e) {
	                JOptionPane.showMessageDialog( owner, "Parse error: " + e.getMessage(), "Error",
	                        JOptionPane.ERROR_MESSAGE );
	                return -1;
				}
			}
    	}
    	if ( dialogVal != JFileChooser.APPROVE_OPTION ) {
        	return -1;
    	} else {
        	return 0;
    	}
	}

}
