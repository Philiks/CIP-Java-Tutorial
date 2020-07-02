package programs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class PrimitiveDataTypes {
	private static JFrame frame;
	private JTable table;
	private int rowCtr = 0;
	private String[][] data = {
			{"byte", 	"8 bits", 	"<html><span>-2<sup>7</span></html>",	"<html><span>2<sup>7</sup> - 1</span></html>", 	"123, 127, -128, -21"				},
			{"short", 	"16 bits", 	"<html><span>-2<sup>15</span></html>", 	"<html><span>2<sup>7</sup> - 1</span></html>", 	"123, 456, -789, -254"				},
			{"int",		"32 bits", 	"<html><span>-2<sup>31</span></html>", 	"<html><span>2<sup>7</sup> - 1</span></html>", 	"0b101010, 0167, 123456, 0xA2F4"	},
			{"long",	"64 bits", 	"<html><span>-2<sup>63</span></html>", 	"<html><span>2<sup>7</sup> - 1</span></html>", 	"123, 456789, -987654, -654872"		},
			{"float",	"32 bits", 	"single-precision",						"32-bit IEEE 754", 								"123, 456e1, 123.2f, 456.2e12f"		},
			{"double",	"64 bits", 	"double-precision",						"64-bit IEEE 754",								"123, 123.2, 456d, 456.2d"			},
			{"boolean",	"1 bit(?)",	"true", 								"false", 										"true, false"						},
			{"char",	"16 bits", 	"Unicode 0000",							"Unicode ffff", 								"\'a\', \'\\u1EF2\', \'\\t\', \'C\'"},
			{"String",	"-------",	"-------", 								"-------",										"\"Sample string\", \"\\n \\t \\\\\""}};
					
	private JTable showTable() {
		table = new JTable(new DefaultTableModel(
								new Object[]{"PDT", "Size", "Min. Val.", "Max. Val.", "Literals"}, 
								rowCtr));
		initDataGUI();
		initHeaderGUI();
		table.setFillsViewportHeight(true);
		
		return table;
	}
	
	private void initDataGUI() {
		table.setFont(new Font("Arial", Font.BOLD, 15));
		table.setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		table.setRowHeight(40);
		
		TableColumn column = null;
		
		for(int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i); 
			if(i < 2) 		// PDT & size
				column.setPreferredWidth(120);
			else if(i < 4) 	// min & max val
				column.setPreferredWidth(200);
			else			// literals
				column.setPreferredWidth(350);
		}
	}
	
	private void initHeaderGUI() {
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
	}
	
	private boolean displayNextRow() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		if(rowCtr >= data.length - 1) {
			JOptionPane.showMessageDialog(frame, "That's all folks.", "Displayed All", JOptionPane.INFORMATION_MESSAGE);
			int ans = JOptionPane.showConfirmDialog(frame, "But wait, there's more.\nWanna see?", "Bonus Data Type", JOptionPane.OK_CANCEL_OPTION);
			if(ans == JOptionPane.OK_OPTION)
				model.addRow(data[rowCtr++]);
			return false;
		}
		
		model.addRow(data[rowCtr++]);
		
		return true;
	}
	
	public static void main(String args[]) {
		frame = new JFrame("Primitive Data Types");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		int FRAME_WIDTH = 800, FRAME_HEIGHT = 450, PADDING = 10,
			TABLE_WIDTH = FRAME_WIDTH - 2 * PADDING, TABLE_HEIGHT = 400, 
			BUTTON_WIDTH = 120, BUTTON_HEIGHT = FRAME_HEIGHT - (3 * PADDING + TABLE_HEIGHT);
		
		PrimitiveDataTypes pdt = new PrimitiveDataTypes();
		
		JScrollPane scroll = new JScrollPane(pdt.showTable());
		scroll.setBounds(PADDING, PADDING, TABLE_WIDTH, TABLE_HEIGHT);
		
		AbstractAction nextAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!pdt.displayNextRow())
					((JButton)e.getSource()).setEnabled(false);	
			}
		};
		
		JButton next = new JButton();
		next.setAction(nextAction);
		next.setText("Show Next");
		next.setForeground(Color.WHITE);
		next.setFont(new Font("Arial", Font.BOLD, 15));
		next.setContentAreaFilled(false);
		next.setOpaque(false);
		next.setBounds((FRAME_WIDTH - BUTTON_WIDTH) / 2,	//center x
						TABLE_HEIGHT + 2 * PADDING, 			//y
						BUTTON_WIDTH,
						BUTTON_HEIGHT);
		
		JPanel contentPane = new JPanel(null) {
			private static final long serialVersionUID = 1L;
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
			}
		};
		contentPane.add(scroll);
		contentPane.add(next);
		contentPane.setBackground(Color.BLACK);
		
		frame.setContentPane(contentPane);
		frame.revalidate();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}