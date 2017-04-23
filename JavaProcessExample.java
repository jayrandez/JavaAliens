import javax.swing.JOptionPane;

import org.newspeaklanguage.NSObject;

public class JavaProcessExample
{
	public static void main(NSObject arg) {
		JOptionPane.showMessageDialog(null, "In java app.");
		arg.send("terminate");
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Not in java app");
	}
}
