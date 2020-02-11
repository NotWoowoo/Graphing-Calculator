package main;

import java.awt.event.KeyEvent;

import lawt.engine.Window;

public class Controller {
	
	private Window w;
	private Graph g;
	private double prevX, prevY, dx, dy, zx;
	
	public Controller(Window w, Graph g) {
		this.w = w;
		this.g = g;
		
		prevX = getMouseGraphX();
		prevY = getMouseGraphY();
	}
	
	public double getMouseGraphX() {
		return g.windowXtoGraphX(w.getMouseX());
	}
	
	public double getMouseGraphY() {
		return g.windowYtoGraphY(w.getMouseY());
	}
	
	public void update() {
		dx = prevX-getMouseGraphX();
		dy = prevY-getMouseGraphY();
		
		if(w.mouseButtonsDown.contains(1)) g.addPosition(dx, dy);
		
		prevX = getMouseGraphX();
		prevY = getMouseGraphY();
		
		if(w.mouseScrollTotal != 0) {
			if(w.keysDown.contains(KeyEvent.VK_SHIFT)) {
				zx = w.mouseScrollTotal/-1.0;
			}else {
				zx = w.mouseScrollTotal/-4.0;
			}
			w.mouseScrollTotal = 0;
		}
		g.zoom(zx, getMouseGraphX(), getMouseGraphY());
		zx = 0;
	}
	
}
