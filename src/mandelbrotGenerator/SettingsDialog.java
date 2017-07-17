/**
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-27-2017
*/

package mandelbrotGenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

public class SettingsDialog extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the dialog.
	 */
	public SettingsDialog() {
		setBounds(100, 100, 450, 536);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel mandelbrotSettingsPanel = new JPanel();
			mandelbrotSettingsPanel.setBounds(10, 11, 414, 135);
			mandelbrotSettingsPanel.setBorder( BorderFactory.createTitledBorder( "Mandelbrot Settings" ) );
			contentPanel.add(mandelbrotSettingsPanel);
			mandelbrotSettingsPanel.setLayout(new BorderLayout(0, 0));
			{
				JPanel mandelbrotBezelPanel = new JPanel();
				mandelbrotBezelPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				mandelbrotSettingsPanel.add(mandelbrotBezelPanel);
				mandelbrotBezelPanel.setLayout(new BorderLayout(0, 0));
				{
					JPanel mandelbrotInnerPanel = new JPanel();
					mandelbrotInnerPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
					mandelbrotBezelPanel.add(mandelbrotInnerPanel, BorderLayout.CENTER);
					GridBagLayout gbl_mandelbrotInnerPanel = new GridBagLayout();
					gbl_mandelbrotInnerPanel.columnWidths = new int[]{0, 0, 0, 0};
					gbl_mandelbrotInnerPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
					gbl_mandelbrotInnerPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
					gbl_mandelbrotInnerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					mandelbrotInnerPanel.setLayout(gbl_mandelbrotInnerPanel);
					{
						JLabel lblIterations = new JLabel("Iterations");
						GridBagConstraints gbc_lblIterations = new GridBagConstraints();
						gbc_lblIterations.anchor = GridBagConstraints.WEST;
						gbc_lblIterations.insets = new Insets(0, 0, 5, 5);
						gbc_lblIterations.gridx = 0;
						gbc_lblIterations.gridy = 0;
						mandelbrotInnerPanel.add(lblIterations, gbc_lblIterations);
					}
					{
						textField = new JTextField();
						GridBagConstraints gbc_textField = new GridBagConstraints();
						gbc_textField.insets = new Insets(0, 0, 5, 0);
						gbc_textField.fill = GridBagConstraints.HORIZONTAL;
						gbc_textField.gridx = 2;
						gbc_textField.gridy = 0;
						mandelbrotInnerPanel.add(textField, gbc_textField);
						textField.setColumns(10);
					}
					{
						JLabel lblStartingXCoordinate = new JLabel("Starting X Coordinate");
						GridBagConstraints gbc_lblStartingXCoordinate = new GridBagConstraints();
						gbc_lblStartingXCoordinate.anchor = GridBagConstraints.WEST;
						gbc_lblStartingXCoordinate.insets = new Insets(0, 0, 5, 5);
						gbc_lblStartingXCoordinate.gridx = 0;
						gbc_lblStartingXCoordinate.gridy = 1;
						mandelbrotInnerPanel.add(lblStartingXCoordinate, gbc_lblStartingXCoordinate);
					}
					{
						textField_1 = new JTextField();
						GridBagConstraints gbc_textField_1 = new GridBagConstraints();
						gbc_textField_1.insets = new Insets(0, 0, 5, 0);
						gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
						gbc_textField_1.gridx = 2;
						gbc_textField_1.gridy = 1;
						mandelbrotInnerPanel.add(textField_1, gbc_textField_1);
						textField_1.setColumns(10);
					}
					{
						JLabel lblStartingYCoordinate = new JLabel("Starting Y Coordinate");
						GridBagConstraints gbc_lblStartingYCoordinate = new GridBagConstraints();
						gbc_lblStartingYCoordinate.anchor = GridBagConstraints.WEST;
						gbc_lblStartingYCoordinate.insets = new Insets(0, 0, 5, 5);
						gbc_lblStartingYCoordinate.gridx = 0;
						gbc_lblStartingYCoordinate.gridy = 2;
						mandelbrotInnerPanel.add(lblStartingYCoordinate, gbc_lblStartingYCoordinate);
					}
					{
						textField_2 = new JTextField();
						GridBagConstraints gbc_textField_2 = new GridBagConstraints();
						gbc_textField_2.insets = new Insets(0, 0, 5, 0);
						gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
						gbc_textField_2.gridx = 2;
						gbc_textField_2.gridy = 2;
						mandelbrotInnerPanel.add(textField_2, gbc_textField_2);
						textField_2.setColumns(10);
					}
					{
						JLabel lblZoomLevel = new JLabel("Zoom Level");
						GridBagConstraints gbc_lblZoomLevel = new GridBagConstraints();
						gbc_lblZoomLevel.insets = new Insets(0, 0, 0, 5);
						gbc_lblZoomLevel.anchor = GridBagConstraints.WEST;
						gbc_lblZoomLevel.gridx = 0;
						gbc_lblZoomLevel.gridy = 3;
						mandelbrotInnerPanel.add(lblZoomLevel, gbc_lblZoomLevel);
					}
					{
						textField_3 = new JTextField();
						GridBagConstraints gbc_textField_3 = new GridBagConstraints();
						gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
						gbc_textField_3.gridx = 2;
						gbc_textField_3.gridy = 3;
						mandelbrotInnerPanel.add(textField_3, gbc_textField_3);
						textField_3.setColumns(10);
					}
				}
			}
		}

		JPanel displaySettingsPanel = new JPanel();
		displaySettingsPanel.setBounds(10, 157, 414, 262);
		displaySettingsPanel.setBorder( BorderFactory.createTitledBorder( "Display Settings" ) );
		contentPanel.add(displaySettingsPanel);
		displaySettingsPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel displayBezelPanel = new JPanel();
			displayBezelPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			displaySettingsPanel.add(displayBezelPanel, BorderLayout.CENTER);
			displayBezelPanel.setLayout(new BorderLayout(0, 0));
			{
				JPanel displayInnerPanel = new JPanel();
				displayInnerPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
				displayBezelPanel.add(displayInnerPanel);
				GridBagLayout gbl_displayInnerPanel = new GridBagLayout();
				gbl_displayInnerPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
				gbl_displayInnerPanel.rowHeights = new int[]{0, 0, 0, 19, 0, 0};
				gbl_displayInnerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_displayInnerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
				displayInnerPanel.setLayout(gbl_displayInnerPanel);
				{
					JLabel lblGradient = new JLabel("Gradient");
					GridBagConstraints gbc_lblGradient = new GridBagConstraints();
					gbc_lblGradient.anchor = GridBagConstraints.WEST;
					gbc_lblGradient.insets = new Insets(0, 0, 5, 5);
					gbc_lblGradient.gridx = 0;
					gbc_lblGradient.gridy = 0;
					displayInnerPanel.add(lblGradient, gbc_lblGradient);
				}
				{
					// this is only temporary until the type is defined
					JComboBox<Integer> comboBox = new JComboBox<Integer>();
					GridBagConstraints gbc_comboBox = new GridBagConstraints();
					gbc_comboBox.insets = new Insets(0, 0, 5, 0);
					gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBox.gridx = 3;
					gbc_comboBox.gridy = 0;
					displayInnerPanel.add(comboBox, gbc_comboBox);
				}
				{
					JLabel lblGradientSmoothing = new JLabel("Gradient Smoothing");
					GridBagConstraints gbc_lblGradientSmoothing = new GridBagConstraints();
					gbc_lblGradientSmoothing.anchor = GridBagConstraints.WEST;
					gbc_lblGradientSmoothing.insets = new Insets(0, 0, 5, 5);
					gbc_lblGradientSmoothing.gridx = 0;
					gbc_lblGradientSmoothing.gridy = 1;
					displayInnerPanel.add(lblGradientSmoothing, gbc_lblGradientSmoothing);
				}
				{
					JCheckBox checkBox = new JCheckBox("");
					GridBagConstraints gbc_checkBox = new GridBagConstraints();
					gbc_checkBox.anchor = GridBagConstraints.EAST;
					gbc_checkBox.insets = new Insets(0, 0, 5, 0);
					gbc_checkBox.gridx = 3;
					gbc_checkBox.gridy = 1;
					displayInnerPanel.add(checkBox, gbc_checkBox);
				}
				{
					JLabel lblColorLoops = new JLabel("Color Loops");
					GridBagConstraints gbc_lblColorLoops = new GridBagConstraints();
					gbc_lblColorLoops.anchor = GridBagConstraints.WEST;
					gbc_lblColorLoops.insets = new Insets(0, 0, 5, 5);
					gbc_lblColorLoops.gridx = 0;
					gbc_lblColorLoops.gridy = 2;
					displayInnerPanel.add(lblColorLoops, gbc_lblColorLoops);
				}
				{
					textField_4 = new JTextField();
					GridBagConstraints gbc_textField_4 = new GridBagConstraints();
					gbc_textField_4.insets = new Insets(0, 0, 5, 0);
					gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
					gbc_textField_4.gridx = 3;
					gbc_textField_4.gridy = 2;
					displayInnerPanel.add(textField_4, gbc_textField_4);
					textField_4.setColumns(10);
				}
				{
					JLabel lblAntialiasing = new JLabel("Antialiasing");
					GridBagConstraints gbc_lblAntialiasing = new GridBagConstraints();
					gbc_lblAntialiasing.anchor = GridBagConstraints.WEST;
					gbc_lblAntialiasing.insets = new Insets(0, 0, 5, 5);
					gbc_lblAntialiasing.gridx = 0;
					gbc_lblAntialiasing.gridy = 3;
					displayInnerPanel.add(lblAntialiasing, gbc_lblAntialiasing);
				}
				{
					JCheckBox checkBox = new JCheckBox("");
					GridBagConstraints gbc_checkBox = new GridBagConstraints();
					gbc_checkBox.anchor = GridBagConstraints.EAST;
					gbc_checkBox.insets = new Insets(0, 0, 5, 0);
					gbc_checkBox.gridx = 3;
					gbc_checkBox.gridy = 3;
					displayInnerPanel.add(checkBox, gbc_checkBox);
				}
				{
					JLabel lblResolutions = new JLabel("Resolutions");
					GridBagConstraints gbc_lblResolutions = new GridBagConstraints();
					gbc_lblResolutions.anchor = GridBagConstraints.WEST;
					gbc_lblResolutions.insets = new Insets(0, 0, 0, 5);
					gbc_lblResolutions.gridx = 0;
					gbc_lblResolutions.gridy = 4;
					displayInnerPanel.add(lblResolutions, gbc_lblResolutions);
				}
				{
					// This is only temporary until the type is defined
					JList<Integer> list = new JList<Integer>();
					GridBagConstraints gbc_list = new GridBagConstraints();
					gbc_list.fill = GridBagConstraints.BOTH;
					gbc_list.gridx = 3;
					gbc_list.gridy = 4;
					displayInnerPanel.add(list, gbc_list);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton applyButton = new JButton("Apply");
				buttonPane.add(applyButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
		}
	}
}
