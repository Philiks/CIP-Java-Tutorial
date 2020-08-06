package programs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Operators extends JPanel {
	private static final long serialVersionUID = 1L;
	
	static String oHCS = "<html><center><span>";	// open  HTML Center Span
	static String cSCH = "</span></center></html>"; // close Span Center HTML
	
	private enum State {
		Blank(), Intro(), Prob1(), Prob2(), Prob3(),
		
		// Obejct[][] { Level, Operator, Description, Associativity, Num of lines "br"
		
		// +, -, ++(pre), --(pre), ++(post), --(post)
		Unary(new Object[][]{
				{"15", oHCS+"++<br />--"+cSCH,					oHCS+"unary post-increment<br />unary post-decrement"+cSCH,									"not associative",	"2"},
				{"14", oHCS+"++<br />--<br />+<br />-"+cSCH,	oHCS+"unary pre-increment<br />unary pre-decrement<br />unary plus<br />unary minus"+cSCH,	"right to left", 	"3"}
				}),
		// +, -, /, *, %  
		Arithmetic(new Object[][]{
				{"12", oHCS+"* / %"+cSCH, 		"multiplicative",								"left to right", "3"},
			   	{"11", oHCS+"+ -<br />+"+cSCH,	oHCS+"additive<br />string contatenation"+cSCH,	"left to right", "2"}
				}),
		// <, >, <=, >=, ==, !=, instanceof
		Relational(new Object[][]{
				{"9", oHCS+"&lt; &lt;=<br />&gt; &gt;=<br />instanceof"+cSCH, 	"relational",	"not associative",	"3"},
				{"8", oHCS+"==<br />!="+cSCH, 									"equality",		"left to right",	"2"}
				}),
		// &, |, ~, ^, <<, >>, >>>
		Bitwise(new Object[][]{
				{"14", 	"~", "unary bitwise NOT", "right to left", "1"},
				{"10", 	oHCS+"&lt;&lt; &gt;&gt;<br />&gt;&gt;&gt;"+cSCH, "shift", "left to right", "2"},
				{"7",	"&", "bitwise AND",	"left to right", "1"},
				{"6", 	"^", "bitwise XOR",	"left to right", "1"},
				{"5", 	"|", "bitwise OR",	"left to right", "1"}
				}),
		// &&, ||, !
		Logical(new Object[][]{
				{"14", 	"!",  "unary logical NOT",	"right to left", "1"},
			 	{"4", 	"&&", "logical AND",		"left to right", "1"},
			 	{"3", 	"||", "logical OR",			"left to right", "1"}
			 	}),
		// =, +=, -=, *=, /=, %=, &=, |=, ^=, <<=, >>=, >>>=
		Assignment(new Object[][]{
				{"1", oHCS+"= += -=<br />*= /= %=<br />&amp;= ^= |=<br />&lt;&lt;= &gt;&gt;= &gt;&gt;&gt;="+cSCH, "assignment",	"right to left", "4"}
				}),
		// ?:, [], (), ., (), new
		Other(new Object[][]{
				{"16",	oHCS+"[]<br />.<br />()"+cSCH,	oHCS+"access array element<br />access object member<br />grouping expression"+cSCH,	"left to right", "3"},
				{"13",	oHCS+"()<br />new"+cSCH, 		oHCS+"cast<br />object instantiation"+cSCH,												"right to left", "2"},
				{"2",	"?:", "ternary",	"right to left", "1"}
				});
		
		Object[][] rowData;
		
		State() {
			// non table related States
		}
		
		State(Object[][] rowData) {
			this.rowData = rowData;
		}
		
		Object[][] getRowData() {
			return rowData;
		}
		
		int getNumberOfBreakLines(int row) {
			// column that holds the number of breaklines
			int rightmost_index = rowData[0].length - 1;
			String str_val = rowData[row][rightmost_index].toString();
			return Integer.valueOf(str_val);
		}
	}
	
	private final int WIDTH = 800, HEIGHT = WIDTH * 3 / 4;
	private final int OFFSET = 20, MARGIN = 30;
	private final int BUTTON_WIDTH = 175, BUTTON_HEIGHT = 50;
	private final int DESC_WIDTH = WIDTH - OFFSET * 2, DESC_HEIGHT = 100;
	private final String COLOR = "#0FF"; // CYAN
	private boolean showLeaderboard = false;
	
	private Font font = new Font("Arial", Font.BOLD, 18);
	private JFrame frame;
	private JScrollPane operatorScroll, leaderboardScroll;
	private JTable operatorTable, leaderboardTable;
	private JButton prev, next, show;
	private JLabel prompt, desc, demo;
	
	private State currentState = State.Blank;
	
	private String tab(int num) {
		StringBuilder tabs = new StringBuilder();
		while(num > 0) {
			tabs.append("&emsp;");
			num--;
		}
		return tabs.toString(); 
	}
	
	private void update() {
		final String ssc = "<span style=\"color:"+COLOR+";\">"; // Span Style Color
		final String oHC = "<html><center>"; // open  HTML Center
		final String cCH = "</center></html>"; // close Center HTML
		String text = "";
		String demoText = "";
		boolean isDesc = currentState.ordinal() >= State.Unary.ordinal();
		
		switch(currentState) {
			case Blank:
				prompt.setVisible(false);
				break;
			case Intro:
				prompt.setVisible(true);
				text = oHC+ssc+"Are you ready?</span>"+cCH;
				break;
			case Prob1:
				text = oHC+ssc+"Integer one = Integer.valueOf(0 + 1);</span><br /><br />"
						+ "What is the  value of one?"+cCH;
				break;
			case Prob2:
				text = oHC+ssc+"Integer two = Integer.valueOf(7 - 4 / 2 * 3 + 1);</span><br /><br />"
						+ "What is the  value of two?"+cCH;
				break;
			case Prob3:
				frame.setTitle("");
				operatorScroll.setVisible(false);
				desc.setVisible(false);
				demo.setVisible(false);
				prompt.setVisible(true);
				text = "<html>Integer one = Integer.valueOf(0 + 1);<br /><br />"
						+ "Integer two = Integer.valueOf(7 - 4 / 2 * 3 + 1);<br /><br />"
						+ ssc+"boolean trulalu_o_babalu =<br />"
						+ tab(2)+"--two / one++ == two || one-- >= ++two * one ?<br />"
						+ tab(4)+"one instanceof Number :<br />"
						+ tab(4)+"(one | two) - (two & one) % +one &gt; two &gt;&gt; one &lt;&lt; two &gt;&gt;&gt; one<br />"
						+ tab(4)+"&& one + two != ~one * -(two ^ one);</span><br /><br />"
						+ "Trulalu o Babalu? Use the #420-69 to vote!</html>";
				break;
			case Unary:
				frame.setTitle("boolean trulalu_o_babalu = "
						+ "--two / one++ == two || one-- >= ++two * one ? "
						+ "one instanceof Number :"
						+ "(one | two) - (two & one) % +one > two >> one << two >>> one && one + two != ~one * -(two ^ one)");
				operatorScroll.setVisible(true);
				desc.setVisible(true);
				demo.setVisible(true);
				prompt.setVisible(false);
				text = oHC+ssc+tab(1)+"Unary Operators</span><br />"
						+ tab(1)+"Haha single :P"+cCH;
				demoText = oHC+tab(1)+"int a = "+ssc+"-</span>1, b = "+ssc+"+</span>2;<br />"
						+ "a"+ssc+"++</span>;"+tab(1)+ssc+"--</span>b;"+cCH;
				break;
			case Arithmetic:
				text = oHC+ssc+tab(1)+"Arithmetic Operators</span><br />"
						+tab(1)+"Basic maths."+cCH;
				demoText = oHC+"int a = 1, b = 2, c;<br />"
						+ "c = a "+ssc+"*</span> b;"+tab(1)+"c = a "+ssc+"%</span> b;"+tab(1)+"c = a "+ssc+"-</span> b;<br />"
						+"String str = \"subscribe\" "+ssc+"+</span> \"now\";"+cCH;
				break;
			case Relational:
				text = oHC+ssc+tab(1)+"Relational Operators</span><br />"
						+tab(1)+"Will it work? Or will it end up in break-up? Iinom nalang 'yan."+cCH;
				demoText = oHC+"int a = 1;"+tab(1)+"Integer b = Integer.valueOf(2);"+tab(1)+"boolean isTrulalu;<br />"
						+ "isTrulalu = a "+ssc+"&lt;</span> b;"+tab(1)+"isTrulalu = a "+ssc+"&gt;=</span> b;<br />"
						+ "isTrulalu = b "+ssc+"instanceof</span> Number;"+tab(1)+"isTrulalu = a "+ssc+"!=</span> b;"+cCH;
				break;
			case Bitwise:
				text = oHC+ssc+tab(1)+"Bitwise Operators</span><br />"
						+tab(1)+"You should definitely be wise since puro 1's and 0's to."+cCH;
				demoText = oHC+tab(1)+"int a = 0b00011110, b = 0b01010101, c; // a = 30 b = 85<br />"
						+ "c = a "+ssc+"&lt;&lt;</span> 2; /* 00 011110"+ssc+"00</span> = 120 */"+tab(1)+"c = a "+ssc+"&</span> b;//  00011110 = a = 30<br />"
						+ "c = "+ssc+"~</span>b; /* 01010101 = "+ssc+"10101010</span> = 170 */"+tab(2)+                         " //& 01010101 = b = 85<br />"
												+															             tab(27)+"//  00010100 = c = 20"+cCH;
				break;
			case Logical:
				text = oHC+ssc+tab(1)+"Logical Operators</span><br />"
						+ tab(1)+"Facts only. Trulalu kung totoo at Babalu kung echos lang."+cCH;
				demoText = oHC+"boolean trulalu = true, babalu = false, anongTotoo;<br />"
						+ "anongTotoo = trulalu "+ssc+"||</span> babalu;"+tab(1)+"anongTotoo = "+ssc+"!</span>trulalu;"+cCH;
				break;
			case Assignment:
				text = oHC+ssc+tab(1)+"Assignment Operators</span><br />"
						+tab(1)+"Ito ang bagay na hindi dapat pinapapaalala kay teacher.<br />"
						+tab(1)+"Shhhhh 'wag bibo."+cCH;
				demoText = oHC+"int a "+ssc+"=</span> 69;<br />"
						+ "a "+ssc+"+=</span> 420;<br />"
						+ "// a = a + 420 or a = 69 + 420"+cCH;
				break;
			case Other:
				text = oHC+ssc+tab(1)+"O-T-H-E-R Operators</span><br />"+tab(1)+"What?"+cCH;
				demoText = oHC+"float f;"+tab(1)+"int[] a = {69, 420};"+tab(1)+"Label label = "+ssc+"new</span> Label(\"wala kayo niyan\");<br />"
						+ "f = a"+ssc+"[</span>0"+ssc+"]</span>;"+tab(1)+"label"+ssc+".</span>setText(\"ligawan mo muna\");<br />"
						+ "a[1] = "+ssc+"(</span>int"+ssc+")</span>;"+tab(1)+"f = a[0] * "+ssc+"(</span>a[1] + a[0]"+ssc+")</span>;<br />"
						+ "f = label != null "+ssc+"?</span> 69.0f "+ssc+":</span> 420.0;"+cCH;
				break;
		}
		
		if(showLeaderboard) {
			updateComponents();
		}
		
		if(currentState != State.Blank) {
			if(isDesc) {
				desc.setText(text);
				demo.setText(demoText);
				updateOperatorTable();
				updateLeaderboardTable();
			}
			else prompt.setText(text);
		}
	}

	private void updateComponents() {
		// the component currently displayed will get the flag of showLeaderboard
		// before inverting its value
		// If the showLeaderboard is true, the components will be true
		// then the leaderboard will be inverted to false
		if(currentState.ordinal() >= State.Unary.ordinal()) {
			operatorScroll.setVisible(showLeaderboard);
			desc.setVisible(showLeaderboard);
			demo.setVisible(showLeaderboard);
		} else if(currentState != State.Blank) {
			prompt.setVisible(showLeaderboard);
		}
		
		showLeaderboard = !showLeaderboard;
		leaderboardScroll.setVisible(showLeaderboard);
	}
	
	private void updateOperatorTable() {
		DefaultTableModel model = (DefaultTableModel)operatorTable.getModel();

		// clears content
		model.setRowCount(0);
		
		// adds content one by one
		for(int i = 0; i < currentState.getRowData().length; i++) {
			model.addRow(currentState.getRowData()[i]);
			int height_interval = 30;
			int last_row = model.getRowCount() - 1;
			operatorTable.setRowHeight(last_row, height_interval * currentState.getNumberOfBreakLines(i));
		}
	}
	
	private void updateLeaderboardTable() {
		DefaultTableModel model = (DefaultTableModel)leaderboardTable.getModel();
		
		// clears content
		model.setRowCount(0);
		
		for(int i = State.Unary.ordinal(); i <= currentState.ordinal(); i++) {
			State state = State.values()[i];
			for(int j = 0; j < state.getRowData().length; j++) {
				int key = Integer.valueOf(state.getRowData()[j][0].toString());
				int index = model.getRowCount();
				for(int k = 0; k < model.getRowCount(); k++) {
					int value = Integer.valueOf(model.getValueAt(k, 0).toString());
					if(key > value) {
						index = k;
						break;
					}
				}
				
				model.insertRow(index, state.getRowData()[j]);
				int height_interval = 30;
				leaderboardTable.setRowHeight(index, height_interval * state.getNumberOfBreakLines(j));
			}
		}
	}
	
	private void initGUI() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		
		setBackground(Color.BLACK);
		setLayout(null);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initTable();
		initButtons();
		initLabels();
		
		frame.setContentPane(this);
		frame.revalidate();
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
	}
	
	private void initTable() {
		operatorTable 	 = createTable(new DefaultTableModel(
											new Object[]{"Level", "Operator", "Description", "Associativity"},
											0/*no data yet*/));
		
		leaderboardTable = createTable(new DefaultTableModel(
											new Object[]{"Level", "Operator", "Description"}, 
											0/*no data yet*/));	
		
		int scroll_width  = WIDTH - 2 * OFFSET;
		int scroll_height = HEIGHT - (BUTTON_HEIGHT + DESC_HEIGHT*2 + MARGIN*2 + OFFSET*3);
		operatorScroll = createScrollPane(operatorTable,
											new Rectangle(OFFSET, OFFSET, scroll_width, scroll_height));
		
		scroll_width  = WIDTH * 4 / 5;
		scroll_height = HEIGHT - (BUTTON_HEIGHT + OFFSET) - MARGIN;
		leaderboardScroll = createScrollPane(leaderboardTable,
											new Rectangle((WIDTH - scroll_width) / 2, OFFSET, scroll_width, scroll_height));
		
		add(operatorScroll);
		add(leaderboardScroll);
	}
	
	private void initLabels() {
		int prompt_width  = DESC_WIDTH;
		int prompt_height = operatorScroll.getBounds().height + DESC_HEIGHT;
		int x = (WIDTH  - prompt_width) / 2;
		int y = (HEIGHT - prompt_height) / 2 - (BUTTON_HEIGHT);
		
		prompt = createLabel(new Rectangle(x, y, prompt_width, prompt_height));
		
		int opScrollHeight = operatorScroll.getBounds().height;
		int opScrollY = operatorScroll.getBounds().y;
		y = opScrollY + opScrollHeight + MARGIN;
		desc = createLabel(new Rectangle(x, y, DESC_WIDTH, DESC_HEIGHT));
		
		y = desc.getBounds().y + DESC_HEIGHT + MARGIN;
		demo = createLabel(new Rectangle(x, y, DESC_WIDTH, DESC_HEIGHT));
		
		add(prompt);
		add(desc);
		add(demo);
	}
	
	private void initButtons() {
		int x = WIDTH / 2 + OFFSET + BUTTON_WIDTH / 2;
		int y = HEIGHT - (OFFSET + BUTTON_HEIGHT);
		
		AbstractAction nextAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				prev.setEnabled(true);
				
				currentState = State.values()[currentState.ordinal() + 1];
				
				if(currentState == State.Other)
					next.setEnabled(false);
				
				update();
			}
		};
		next = createButton("Next", nextAction, new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT));

		AbstractAction showAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				updateComponents();
			}
		};
		
		x = (WIDTH - BUTTON_WIDTH) / 2;
		show = createButton("Leaderboard", showAction, new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT));

		AbstractAction prevAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				next.setEnabled(true);
				
				currentState = State.values()[currentState.ordinal() - 1];
				
				if(currentState == State.Blank)
					prev.setEnabled(false);
				
				update();
			}
		};
		
		x = WIDTH / 2 - (OFFSET + BUTTON_WIDTH + BUTTON_WIDTH / 2);
		prev = createButton("Previous", prevAction, new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT));
		prev.setEnabled(false);
		
		add(prev);
		add(next);
		add(show);
	}
	
	private JScrollPane createScrollPane(JTable table, Rectangle bound) {
		JScrollPane scroll = new JScrollPane(table);
		
		scroll.setBounds(bound);
		scroll.setVisible(false);
		scroll.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.BLUE));
		
		return scroll;
	}
	
	private JTable createTable(DefaultTableModel model) {
		JTable table = new JTable(model);
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		table.setFont(font);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setBackground(Color.BLACK);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(font);
		table.getTableHeader().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.BLUE));
		((DefaultTableCellRenderer)table.getDefaultRenderer(String.class))
						.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		return table;
	}
	
	private JLabel createLabel(Rectangle bound) {
		JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setBounds(bound);
		label.setVisible(false);
		label.setFont(font);
		label.setBorder(BorderFactory.createDashedBorder(Color.CYAN, 5.0f, 3.0f, 2.0f, true));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
		return label;
	}
	
	private JButton createButton(String text, AbstractAction action, Rectangle bound) {
		JButton button = new JButton(action);
		button.setText(text);
		button.setFont(font);
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setBounds(bound);
		button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.BLUE));
		
		return button;
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(() -> new Operators().initGUI());
	}
}