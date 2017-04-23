import java.awt.Color;
import java.awt.Graphics2D;

public class GameEquivalent {
	private static Color color = Color.BLACK;
	private static boolean increasing = true;
	private static int x = 0;
	
	private static boolean run(JavaGraphics.AppState state) {
		boolean redraw = false;
		
		if(state.step) {
			state.step = false;
			redraw = true;
			
			if(increasing) {
				x += 10;
				if(x >= 799)
					increasing = false;
			}
			else {
				x -= 10;
				if(x <= 0)
					increasing = true;
			}
		}
		
		if(state.mouseMoved) {
			state.mouseMoved = false;
			redraw = true;
		}
		
		if(state.mouseClicks.size() > 0) {
			state.mouseClicks.clear();
			color = new Color((float)Math.random() / 4.0f, (float)Math.random() / 4.0f, (float)Math.random() / 4.0f);
		}
		
		if(redraw)
			draw(state);
		
		return redraw;
	}
	
	private static void draw(JavaGraphics.AppState state) {
		state.start();
		synchronized(state.backbuffer) {
			Graphics2D g = (Graphics2D)state.backbuffer.getGraphics();
			
			g.setColor(color);
			g.fillRect(0, 0, 800, 600);
			
			g.setColor(Color.WHITE);
			
			g.fillOval(x - 5, 300 - 5, 10, 10);
			
			if(state.mousePoint != null) {
				g.drawLine(0, 0, state.mousePoint.x, state.mousePoint.y);
				g.drawLine(799, 0, state.mousePoint.x, state.mousePoint.y);
				g.drawLine(799, 599, state.mousePoint.x, state.mousePoint.y);
				g.drawLine(0, 599, state.mousePoint.x, state.mousePoint.y);
			}
		}
		state.stop();
	}
	
	public static void main(String[] args) {
		JavaGraphics.AppState state = JavaGraphics.makeWindow();
		
		while(true) {
			if(state.finished)
				System.exit(0);
			
			boolean dirty = run(state);
			if(dirty)
				state.panel.repaint();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
