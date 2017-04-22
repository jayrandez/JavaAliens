import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class JavaGraphics extends JPanel implements MouseListener, MouseMotionListener
{
	public static class AppState {
		JavaGraphics panel;
		BufferedImage backbuffer;
		boolean step;
		boolean mouseMoved;
		java.awt.Point mousePoint;
		ArrayList<Point> mouseClicks;
		boolean finished;
		long millis;
		
		public AppState() {
			backbuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
			step = false;
			mouseMoved = false;
			mousePoint = null;
			mouseClicks = new ArrayList<Point>();
			finished = false;
		}
		
		long startTime;
		
		public void start() {
			startTime = System.currentTimeMillis();
		}
		
		public void stop() {
			millis = System.currentTimeMillis() - startTime;
		}
	}
	
	public AppState state;

	public JavaGraphics(AppState appState) {
		this.state = appState;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(800, 600));
	}
	
	public void paint(Graphics gg) {
		super.paint(gg);
		Graphics2D g = (Graphics2D)gg;

		synchronized(state.backbuffer) {
			g.drawImage(state.backbuffer, 0, 0, null);
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Millis: " + state.millis, 700, 100);
	}

	public void step() {
		state.step = true;
	}
	
	public void mouseMoved(MouseEvent ev) {
		state.mousePoint = ev.getPoint();
		state.mouseMoved = true;
	}
	
	public void mouseDragged(MouseEvent ev) {
		state.mousePoint = ev.getPoint();
		state.mouseMoved = true;
	}

	public void mouseExited(MouseEvent ev) {
		state.mousePoint = null;
		state.mouseMoved = true;
	}

	public void mousePressed(MouseEvent ev)  {
		state.mouseClicks.add(ev.getPoint());
	}
	
	public void mouseEntered(MouseEvent ev) {}
	public void mouseClicked(MouseEvent ev) {}
	public void mouseReleased(MouseEvent ev) {}
	
	public static AppState makeWindow() {
		AppState state = new AppState();
		
		new Thread() {
		public void run() {
			JavaGraphics graphicsPanel = new JavaGraphics(state);
			state.panel = graphicsPanel;
			
			new Thread() {
				public void run() {
					while(true) {
						graphicsPanel.step();
						try { Thread.sleep(33); }
						catch(Exception ex) {}
					}
				}
			}.start();
			
			JFrame frame = new JFrame();
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
	    		public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	    			graphicsPanel.state.finished = true;
	    		}
	    	});
	
			frame.setLayout(new BorderLayout());
			frame.add(graphicsPanel, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
		
		}}.start();
		
		return state;
	}
}
