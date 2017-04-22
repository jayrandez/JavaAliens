import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JavaApplication extends JFrame
{
	Color ballColor = Color.BLACK;
	final ToolbarPanel toolbarPanel;
	final GraphicsPanel graphicsPanel;
	
	public JavaApplication(String title, String initialValue) {
		super(title);
		
		this.toolbarPanel = new ToolbarPanel(initialValue);
		this.graphicsPanel = new GraphicsPanel(initialValue);
		
		toolbarPanel.textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				graphicsPanel.text = toolbarPanel.textField.getText();
			}
		});
		
		new Thread() {
			public void run() {
				while(true) {
					graphicsPanel.update();
					try {
						Thread.sleep(33);
					}
					catch(Exception ex) {}
				}
			}
		}.start();
		
		this.setLayout(new BorderLayout());
		this.add(toolbarPanel, BorderLayout.NORTH);
		this.add(graphicsPanel, BorderLayout.CENTER);
		this.setJMenuBar(buildMenuBar());
		this.pack();
		this.setVisible(true);
	}
	
	private JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(1);
			}
		});
		
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		
		return menuBar;
	}
	
	private class GraphicsPanel extends JPanel implements MouseListener, MouseMotionListener {
		public String text;
		private Point ball;
		private Point mouse;
		private boolean dragging;
		private boolean increasing;
		
		public GraphicsPanel(String initialText) {
			this.text = initialText;
			this.ball = new Point(0, 300);
			this.mouse = null;
			this.dragging = false;
			this.increasing = true;
			
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.setBackground(Color.WHITE);
			this.setPreferredSize(new Dimension(800, 600));
		}
		
		public void paint(Graphics gg) {
			super.paint(gg);
			Graphics2D g = (Graphics2D)gg;

			g.setColor(ballColor);
			g.fillOval(ball.x - 5, ball.y - 5, 11, 11);
			
			if(mouse != null) {
				g.setColor(Color.BLACK);
				g.drawLine(0, 0, mouse.x, mouse.y);
				g.drawLine(799, 0, mouse.x, mouse.y);
				g.drawLine(0, 599, mouse.x, mouse.y);
				g.drawLine(799, 599, mouse.x, mouse.y);
			}
			
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", 50, 50));
			g.drawString(text, 20, 80);
		}

		public void update() {
			if(dragging)
				return;
			
			if(increasing) {
				ball.x+=10;
				if(ball.x >= 799)
					increasing = false;
			}
			else {
				ball.x-=10;
				if(ball.x <= 0)
					increasing = true;
			}
			repaint();
		}
		
		public void mouseMoved(MouseEvent ev) {
			this.mouse = ev.getPoint();
			repaint();
		}
		
		public void mouseDragged(MouseEvent ev) {
			this.mouse = ev.getPoint();
			this.ball = ev.getPoint();
			repaint();
		}

		public void mouseExited(MouseEvent ev) {
			this.mouse = null;
			repaint();
		}

		public void mousePressed(MouseEvent ev) {
			this.ball = ev.getPoint();
			this.dragging = true;
			repaint();
		}

		public void mouseReleased(MouseEvent ev) {
			this.dragging = false;
		}
		
		public void mouseClicked(MouseEvent ev) {}
		public void mouseEntered(MouseEvent ev) {}
	}
	
	private class ToolbarPanel extends JPanel {
		public JTextField textField;
		
		public ToolbarPanel(String initialText) {
			this.textField = new JTextField(initialText);
			textField.setPreferredSize(new Dimension(150, 26));
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.add(textField);
			this.add(new JLabel("(Press Enter)"));
		}
	}
	
	public static JavaApplication builder() {
		String title = "Static Factory";
		String initialText = "Default Textbox String";
		return new JavaApplication(title, initialText);
	}
	
	public static void main(String[] args) {
		String title = (args.length > 0) ? (args[0]) : ("Default Title");
		if(title == null)
			title = "null";
		String initialText = (args.length > 1) ? (args[1]) : ("Default Textbox String");
		if(initialText == null)
			initialText = "null";
		new JavaApplication(title, initialText);
	}
}
