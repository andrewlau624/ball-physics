import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable{
	private final int width = 600;
	private final int height = 600;
	private final double FPS = 60;
	
	double drawInterval = 1000000000/FPS;
	double delta = 0;
	long lastTime = System.nanoTime();
	long currentTime;
	
	private ArrayList<Object> obj = new ArrayList<Object>();
	private Vec2 gravity = new Vec2(0, 0.16345);
	
	private int borderRad = 250;
	private int bRad = 10;
	
	int count = 0;
	
	public Game() {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void update() {
		if(count % 10 == 0)
			obj.add(new Object(new Vec2(width/2 - 100, height/2)));
		int subSteps = 2;
		for(int i = 0; i < subSteps; i++) {
			applyGravity();
			applyConstraint();
			solveCollisions();
			updatePos(delta / 2);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.darkGray);
		g.fillOval(width/2 - borderRad, height/2 - borderRad, borderRad * 2, borderRad * 2);
		drawBall(g);
	}
	
	public void drawBall(Graphics g) {
		g.setColor(Color.WHITE);
		for(Object obj: obj) {
			g.fillOval((int)obj.position.x - bRad, (int)obj.position.y - bRad, bRad * 2, bRad * 2);
		}
	}
	
	public void updatePos(double subDt) {
		for(Object obj: obj) {
			obj.updatePosition(subDt);
		}
	}
	
	public void applyGravity() {
		for(Object obj: obj) {
			obj.accelerate(gravity);
		}
	}
	
	public void applyConstraint() {
		Vec2 position = new Vec2(width/2, height/2);
		for(Object obj: obj) {
			Vec2 toObj = obj.position.sub(position);
			double dist = Math.sqrt(toObj.x * toObj.x + toObj.y * toObj.y);
			if(dist > borderRad - bRad) {
				Vec2 n = toObj.div(dist);
				obj.position = position.add(n.mult(borderRad - bRad));
			}
		}
	}
	
	public void solveCollisions() {
		for(Object obj: obj) {
			for(Object obj2: this.obj) {
				if(obj == obj2) break;
				Vec2 collisionAxis = obj.position.sub(obj2.position);
				double dist = Math.sqrt(collisionAxis.x * collisionAxis.x + collisionAxis.y * collisionAxis.y);
				if(dist < bRad * 2) {
					Vec2 n = collisionAxis.div(dist);
					double dta = bRad * 2 - dist;
					obj.position = obj.position.add(n.mult(dta / 2));
				}
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				count++;
			}
		}
	}
}
