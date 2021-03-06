/**
* Source file for the Mandelbrot Set menu
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-27-2017
*/

package mandelbrotGenerator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.sun.glass.events.KeyEvent;

public class ProgramMenu extends JMenuBar {
	/**
	 *
	 */
	private static final long serialVersionUID = 7911406328149685822L;

	private JMenu file;
	private JMenuItem setZoomPointItem;
	private JMenuItem loadZoomPointItem;
	private JMenuItem saveZoomPointItem;
	private JMenuItem savePictureItem;
	private JMenuItem printItem;
	private JMenuItem settingsItem;
	private JMenuItem exitItem;

	private JMenu tools;
	private JMenuItem pointerItem;
	private JMenuItem panItem;
	private JMenuItem zoomInItem;
	private JMenuItem zoomOutItem;
	private JMenuItem resetItem;

	private JMenu help;
	private JMenuItem aboutItem;

	private ImageIcon currentIcon;

	private JFileChooser pictureFileChooser;
	private MbzFileManager mbzFileManager;
	private File selectedFile;

	public ProgramMenu( final MainWindow owner ) {
		// TODO Auto-generated constructor stub
		file = new JMenu( "File" );

		//Create a file chooser
		mbzFileManager = new MbzFileManager( owner );
		pictureFileChooser = new JFileChooser( FileSystemView.getFileSystemView().getDefaultDirectory() );
		pictureFileChooser.setDialogTitle("Save image to where?");
		pictureFileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG image", "png" );
		pictureFileChooser.addChoosableFileFilter(filter);


		currentIcon = new ImageIcon( "rc/icons/setZoomPoint.png" );
		setZoomPointItem = new JMenuItem ( "Set Zoom Point", currentIcon );
		setZoomPointItem.setToolTipText( "Opens up a dialog letting you input the zoom point" );
		setZoomPointItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent arg0 ) {
            	owner.showCoordinateInputDialog();
            }
        });

		currentIcon = new ImageIcon( "rc/icons/loadZoomPoint.png" );
		loadZoomPointItem = new JMenuItem ( "Load Zoom Point", currentIcon );
		loadZoomPointItem.setToolTipText( "Loads a zoom point from an .MBZ file" );
		loadZoomPointItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent arg0 ) {
            	int returnVal = mbzFileManager.readZoomPoint();
            	long zoomAmount;
            	if ( returnVal == 0 ){
            		zoomAmount = (long)Math.pow( 2, mbzFileManager.getZoomLevel() );
        			owner.absoluteZoom( mbzFileManager.getXCoordinate(), mbzFileManager.getYCoordinate(), zoomAmount );
            	}
            }
        } );

		currentIcon = new ImageIcon( "rc/icons/saveZoomPoint.png" );
		saveZoomPointItem = new JMenuItem ( "Save Zoom Point", currentIcon );
		saveZoomPointItem.setToolTipText( "Saves the current zoom point to an .MBZ file" );
		saveZoomPointItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent arg0 ) {
            	mbzFileManager.writeZoomPoint( owner.getCenter().getX(), owner.getCenter().getY(), owner.getZoomLevel() );
            }
        } );

		currentIcon = new ImageIcon( "rc/icons/savePicture.png" );
		savePictureItem = new JMenuItem ( "Save Picture", currentIcon );
		savePictureItem.setToolTipText( "Saves the current picture to a PNG file" );
		savePictureItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent arg0 ) {
            	int returnVal = pictureFileChooser.showSaveDialog( ProgramMenu.this );
            	if ( returnVal == JFileChooser.APPROVE_OPTION ){
        			selectedFile = pictureFileChooser.getSelectedFile();
        			try {
						ImageIO.write( owner.getMandelbrotImage(), "png", selectedFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            }
        } );

		currentIcon = new ImageIcon( "rc/icons/print.png" );
		printItem = new JMenuItem ( "Print", currentIcon );
		printItem.setToolTipText( "Prints the Mandelbrot set picture" );

		currentIcon = new ImageIcon( "rc/icons/settings.png" );
		settingsItem = new JMenuItem ( "Settings", currentIcon );
		settingsItem.setToolTipText( "Lets you configure the program" );

		currentIcon = new ImageIcon( "rc/icons/saveZoomPoint.png" );
		exitItem = new JMenuItem ( "Exit" );
		exitItem.setToolTipText( "Closes the program" );
		exitItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent arg0 ) {
                System.exit( 0 );
            }
        } );

		file.setMnemonic( KeyEvent.VK_F );
		file.add( setZoomPointItem );
		file.add( loadZoomPointItem );
		file.add( saveZoomPointItem );
		file.addSeparator();
		file.add( savePictureItem );
		file.add( printItem );
		file.addSeparator();
		file.add( settingsItem );
		file.addSeparator();
		file.add( exitItem );

		tools = new JMenu( "Tools" );
		pointerItem = new JMenuItem ( "Pointer" );
		panItem = new JMenuItem ( "Pan" );
		zoomInItem = new JMenuItem ( "Zoom In" );
		zoomOutItem = new JMenuItem ( "Zoom Out" );
		resetItem = new JMenuItem ( "Reset" );
		tools.setMnemonic( KeyEvent.VK_T );
		tools.add( pointerItem );
		tools.add( panItem );
		tools.addSeparator();
		tools.add( zoomInItem );
		tools.add( zoomOutItem );
		tools.addSeparator();
		tools.add( resetItem );

		help = new JMenu( "Help" );
		aboutItem = new JMenuItem ( "About" );
		help.setMnemonic( KeyEvent.VK_H );
		help.add( aboutItem );

		add( file );
		add( tools );
		add( help );

	}

}
