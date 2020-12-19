package sorter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class VisualizerFrame extends JFrame {

	private final String[] Sorts = {"Bubble", "Selection", "Insertion", "Merge"};
	
	private int sizeModifier;

	private JPanel wrapper;
	private JPanel arrayWrapper;
	private JPanel buttonWrapper;
	private JPanel[] squarePanels;
	private JButton start;
	private JComboBox<String> selection;
	private GridBagConstraints c;
	private JCheckBox stepped;
	
	public VisualizerFrame(){
		super("Sorting Visualizer");
		
		start = new JButton("Start");
		buttonWrapper = new JPanel();
		arrayWrapper = new JPanel();
		wrapper = new JPanel();
		selection = new JComboBox<String>();
		stepped = new JCheckBox("Stepped Values");
		c = new GridBagConstraints();
		
		for(String s : Sorts) selection.addItem(s);
		
		arrayWrapper.setLayout(new GridBagLayout());
		wrapper.setLayout(new BorderLayout());

		c.insets = new Insets(0,1,0,1);
		c.anchor = GridBagConstraints.SOUTH;
                
                stepped.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18));
                
		
                start.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18));
                start.setBackground(new java.awt.Color(38, 34, 97));
                start.setForeground(Color.WHITE);
                
                selection.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18));
                
                
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortingVisualizer.startSort((String) selection.getSelectedItem());
			}
		});
		
		stepped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortingVisualizer.stepped = stepped.isSelected();
			}
		});

		buttonWrapper.add(stepped);
		buttonWrapper.add(selection);
                buttonWrapper.add(start);
                
		
		wrapper.add(buttonWrapper, BorderLayout.NORTH);
		wrapper.add(arrayWrapper);
		
		add(wrapper);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				// Reset the sizeModifier
				sizeModifier = (int) ((getHeight()*0.9)/(squarePanels.length));
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// Do nothing
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// Do nothing
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// Do nothing
			}
			
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	// preDrawArray reinitializes the array of panels that represent the values.
	public void preDrawArray(Integer[] squares){
		squarePanels = new JPanel[SortingVisualizer.sortDataCount];
		sizeModifier =  (int) ((getHeight()*0.9)/(squarePanels.length));
		for(int i = 0; i<SortingVisualizer.sortDataCount; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
			squarePanels[i].setBackground(new Color(38, 34, 97));
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
	public void reDrawArray(Integer[] x){
		reDrawArray(x, -1);
	}
	
	public void reDrawArray(Integer[] x, int y){
		reDrawArray(x, y, -1);
	}
	
	public void reDrawArray(Integer[] x, int y, int z){
		reDrawArray(x, y, z, -1);
	}
	
	public void reDrawArray(Integer[] squares, int working, int comparing, int reading){
		arrayWrapper.removeAll();
		for(int i = 0; i<squarePanels.length; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
			if (i == working){
				squarePanels[i].setBackground(new Color(250, 175, 64));	
			}else if(i == comparing){
				squarePanels[i].setBackground(new Color(238, 64, 54));	
			}else if(i == reading){
				squarePanels[i].setBackground(Color.yellow);	
			}else{
				squarePanels[i].setBackground(new Color(38, 34, 97));        
			}
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
}
