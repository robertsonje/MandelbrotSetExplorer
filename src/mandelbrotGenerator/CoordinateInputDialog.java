/**
* Source file for the coordinate input dialog accessible from the menu
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.LayoutManager;
import java.text.NumberFormat;
import java.text.ParseException;

public class CoordinateInputDialog extends Dialog implements WindowListener {

	/**
	 * Dialog class that allows for input of the Mandelbrot coordinates
	 * and zoom level.
	 */
	private static final long serialVersionUID = -662387424534326152L;
	private final int LAYOUT_PADDING = 4;
	private MainWindow owner;
	private JPanel generalPanel;
	private JPanel xCoordinatePanel;
	private JPanel yCoordinatePanel;
	private JPanel zoomPanel;
	private JPanel buttonPanel;
	private JPanel realPanel;
	private JPanel imaginaryPanel;
	private JLabel xCoordinateLabel;
	private JLabel yCoordinateLabel;
	private JLabel zoomLevelLabel;
	private JLabel realLabel;
	private JLabel imaginaryLabel;
	private JCheckBox inverseBox;
	private JTextField xCoordinateBox;
	private JTextField yCoordinateBox;
	private JTextField zoomLevelBox;
	private LayoutManager xCoordinateLayout;
	private LayoutManager yCoordinateLayout;
	private LayoutManager zoomLevelLayout;
	private LayoutManager buttonLayout;
	private SpringLayout realLayout;
	private JButton confirmButton;
	private JButton cancelButton;
	private NumberFormat coordinateFormat;
	private boolean isZoomInverted;

	public CoordinateInputDialog( final MainWindow owner ) {
		/**
		 * Constructor for the CoordinateInputDialog object.
		 *
		 * @param  owner	Parent program. Cannot be null.
		 */
		super( owner );
		this.owner = owner;
		realLayout = new SpringLayout();
		inverseBox = new JCheckBox( "Inverse?" );
		inverseBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		inverseBox.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent e) {
				isZoomInverted = ( e.getStateChange() == 1 ) ;
			 }
		  });

		coordinateFormat = NumberFormat.getNumberInstance();

		// Add the panel
		generalPanel = new JPanel();
		generalPanel.setBorder( new EmptyBorder( LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING ) );

		addWindowListener(this);
		setLayout( new BorderLayout() );
		add( generalPanel, BorderLayout.CENTER );
		generalPanel.setLayout( new BoxLayout(generalPanel, BoxLayout.Y_AXIS ) );

		// Initialize the labels
		xCoordinateLabel = new JLabel( "X-Coordinate" );
		xCoordinateLabel.setAlignmentX(0.5f);
		xCoordinateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		realLabel = new JLabel( "Re: " );
		realLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Initialize the text fields
		xCoordinateBox = new JTextField(  );
		xCoordinateBox.setText( "" + owner.getCenter().getX() );

		// Initialize the Panels and add the components
		realPanel = new JPanel();
		xCoordinateBox.setAlignmentX( Component.LEFT_ALIGNMENT );
		realPanel.setLayout( new BoxLayout( realPanel, BoxLayout.X_AXIS ) );
		realPanel.add( realLabel );
		realPanel.add( xCoordinateBox );
		realLayout.putConstraint( SpringLayout.WEST, realLabel, 5, SpringLayout.WEST, realPanel );
		realLayout.putConstraint( SpringLayout.NORTH, realLabel, 5, SpringLayout.NORTH, realPanel );
		realLayout.putConstraint( SpringLayout.WEST, xCoordinateBox, 5, SpringLayout.EAST, realLabel );
		xCoordinatePanel = new JPanel();
		xCoordinateLayout = new BoxLayout( xCoordinatePanel, BoxLayout.Y_AXIS );
		xCoordinatePanel.setBorder(new EmptyBorder( LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING ));
		xCoordinatePanel.setLayout( xCoordinateLayout );
		xCoordinatePanel.add( xCoordinateLabel );
		xCoordinatePanel.add( realPanel );
		generalPanel.add( xCoordinatePanel );
		yCoordinatePanel = new JPanel();
		yCoordinateLayout = new BoxLayout( yCoordinatePanel, BoxLayout.Y_AXIS );
		yCoordinatePanel.setBorder(new EmptyBorder( LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING ));
		yCoordinatePanel.setLayout( yCoordinateLayout );
		yCoordinateLabel = new JLabel( "Y-Coordinate" );
		yCoordinateLabel.setAlignmentX( 0.5f );
		yCoordinateLabel.setAlignmentY( 1.0f );
		yCoordinatePanel.add( yCoordinateLabel );
		imaginaryLabel = new JLabel( "Im: " );
		yCoordinateBox = new JTextField( "" + owner.getCenter().getY() );
		yCoordinateBox.setText( "" + owner.getCenter().getY() );
		imaginaryPanel = new JPanel();
		yCoordinatePanel.add( imaginaryPanel );
		imaginaryPanel.setLayout( new BoxLayout(imaginaryPanel, BoxLayout.X_AXIS ) );
		imaginaryPanel.add( imaginaryLabel );
		imaginaryPanel.add( yCoordinateBox );
		generalPanel.add( yCoordinatePanel );
		zoomLevelLabel = new JLabel( "Zoom Level" );
		zoomLevelLabel.setAlignmentX( Component.RIGHT_ALIGNMENT );
		zoomLevelBox = new JFormattedTextField( "" + Math.pow(2, owner.getZoomLevel() ) );
		zoomPanel = new JPanel();
		zoomLevelLayout = new BoxLayout( zoomPanel, BoxLayout.Y_AXIS );
		zoomPanel.setBorder( new EmptyBorder( LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING ) );
		zoomPanel.setLayout( zoomLevelLayout );
		zoomPanel.add( zoomLevelLabel );
		zoomPanel.add( zoomLevelBox );
		zoomPanel.add( inverseBox );
		generalPanel.add( zoomPanel );
		confirmButton = new JButton( "OK" );
		cancelButton = new JButton( "Cancel" );
		buttonPanel = new JPanel();
		buttonLayout = new BoxLayout( buttonPanel, BoxLayout.X_AXIS );
		buttonPanel.setBorder( new EmptyBorder( LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING ) );
		buttonPanel.setLayout( buttonLayout );
		buttonPanel.add( confirmButton );
		buttonPanel.add( cancelButton );
		generalPanel.add( buttonPanel );

		confirmButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 ) {
				if ( CoordinateInputDialog.this.parseCoordinates() == -1) {
					ErrorHandler.printVerboseErrorDialog( "Parse error: You must input numbers in the boxes." );
				} else {
					CoordinateInputDialog.this.setVisible( false );
				}
			}
		});

		cancelButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent arg0 ) {
				CoordinateInputDialog.this.updateCoordinates();
				CoordinateInputDialog.this.setVisible( false );
			}
		});
		pack();
	}

	protected MaskFormatter createFormatter(String s) {
		/**
		 * Constructor for the CoordinateInputDialog object.
		 *
		 * @param  owner	Parent program. Cannot be null.
		 */
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			ErrorHandler.printErrorMessage( "formatter is bad: " + exc.getMessage()  );
			System.exit(-1);
		}
		return formatter;
	}

	public void updateCoordinates() {
		/**
		 * Updates the coordinates for the parent program.
		 *
		 */
		xCoordinateBox.setText( "" + owner.getCenter().getX() );
		yCoordinateBox.setText( "" + owner.getCenter().getY() );
		zoomLevelBox.setText( "" + Math.pow(2, owner.getZoomLevel() ) );
	}

	public int parseCoordinates() {
		/**
		 * Parses the coordinates in the boxes. If the coordinates
		 * are invalid, then it prints out an error dialog and doesn't
		 * automatically zoom in on the target.
		 *
		 * @return	the status of the function. 0 if OK, -1 if there is an error
		 */
		double xCoordinate;
		double yCoordinate;
		double zoomLevel;

		//Parse the values.
		try {
			xCoordinate = coordinateFormat.parse( xCoordinateBox.getText() ).doubleValue();
		} catch (ParseException pe) {
			return -1;
			}
		try {
			yCoordinate = coordinateFormat.parse( yCoordinateBox.getText() ).doubleValue();
		} catch (ParseException pe) {
			return -1;
			}
		try {
			zoomLevel = coordinateFormat.parse(zoomLevelBox.getText() ).doubleValue();
		} catch (ParseException pe) {
			return -1;
			}
		if ( isZoomInverted ) {
			zoomLevel = Math.pow( zoomLevel, -1 );
		}
		owner.absoluteZoom( xCoordinate, yCoordinate, (long)zoomLevel );
		return 0;
	}

	@Override
	public void windowClosing(java.awt.event.WindowEvent arg0) {
		/**
		 * Window closing event handler.
		 *
		 * @param	arg0	Window event. Unused in this function.
		 */
		// TODO Auto-generated method stub

		//This will only be seen on standard output.
		setVisible( false );
	}

	@Override
	public void windowClosed(java.awt.event.WindowEvent arg0) {
		/**
		 * Window closing event handler.
		 *
		 * @param	arg0	Window event. Unused in this function.
		 */
		//This will only be seen on standard output.
		setVisible( false );
	}

}
