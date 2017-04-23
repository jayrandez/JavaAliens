import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.newspeaklanguage.NSObject;

public class JavaProcessExample extends JFrame
{
	public JavaProcessExample(NSObject object) {
		setPreferredSize(new Dimension(800, 600));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem menuItem = new JMenuItem("Menu Item");
		menu.add(menuItem);
		menuBar.add(menu);
		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				object.send("menuItem");
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent event) {
    			object.send("terminate");
    		}
    	});
		
		this.setJMenuBar(menuBar);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(NSObject object) {
		new JavaProcessExample(object);
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Not in Newspeak app.");
	}
}
