package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import lawt.engine.Entity;

public class Graph extends Entity{
	
	private double xmin=-10, xmax=10, ymin=-10, ymax=10, dx, resolution;
	
	public Graph(){
		super(0,0,Color.WHITE);
		resolution = 3000.0;
		dx = (xmax-xmin)/resolution;
	}
	
	public double graphXtoWindowX(double x){
		return getWindow().getWidth()*(x-xmin)/(xmax-xmin);
	}
	
	public double graphYtoWindowY(double y){
		int h = getWindow().getHeight();
		return -h*(y-ymin)/(ymax-ymin)+h;
	}
	
	public double windowXtoGraphX(double x) {
		return x/getWindow().getWidth()*(xmax-xmin)+xmin;
	}
	
	public double windowYtoGraphY(double y) {
		int h = getWindow().getHeight();
		return ((y-h)/-h)*(ymax-ymin)+ymin;
	}
	
	public double f(double x){
		return Math.pow(x, x);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWindow().getWidth(), getWindow().getHeight());
		
		g.setColor(Color.BLACK);
		if(xmin < 0 && xmax > 0) drawGraphLine(g, 0, ymin, 0, ymax); //y axis
		if(ymin < 0 && ymax > 0) drawGraphLine(g, xmin, 0, xmax, 0); //x axis
		
		g.setColor(Color.RED);
		double x=xmin, y = f(x), prevX = x, prevY = y;
		for(; x < xmax; x += dx){
			y = f(x);
			
			drawGraphLine(g, prevX, prevY, x, y);
			
			prevX = x;
			prevY = y;
		}
	}
	
	public void drawGraphLine(Graphics2D g, double x1, double y1, double x2, double y2){
		g.drawLine((int)graphXtoWindowX(x1), (int)graphYtoWindowY(y1), (int)graphXtoWindowX(x2), (int)graphYtoWindowY(y2));
	}

	public double getXmin() {
		return xmin;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
		dx = (xmax-this.xmin)/resolution;
	}
	
	public void addXmin(double x){
		setXmin(getXmin()+x);
	}
	
	public double getXmax() {
		return xmax;
	}

	public void setXmax(double xmax) {
		this.xmax = xmax;
		dx = (this.xmax-xmin)/resolution;
	}
	
	public void addXmax(double x){
		setXmax(getXmax()+x);
	}

	public double getYmin() {
		return ymin;
	}

	public void setYmin(double ymin) {
		this.ymin = ymin;
	}
	
	public void addYmin(double y){
		setYmin(getYmin()+y);
	}

	public double getYmax() {
		return ymax;
	}

	public void setYmax(double ymax) {
		this.ymax = ymax;
	}
	
	public void addYmax(double y){
		setYmax(getYmax()+y);
	}
	
	public void setPosition(double x, double y){
		setXmin(x);
		setXmax(x);
		setYmin(y);
		setYmax(y);
	}
	
	public void addPosition(double x, double y){
		addXmin(x);
		addXmax(x);
		addYmin(y);
		addYmax(y);
	}
	
	public void zoom(double amt, double x, double y) {
		double xRatio = (x-xmin)/(xmax-xmin);
		double xSize = xmax-xmin;
		setXmin(xmin + amt*xRatio*xSize);
		setXmax(xmax - amt*(1-xRatio)*xSize);
		double yRatio = (y-ymin)/(ymax-ymin);
		double ySize = ymax-ymin;
		setYmin(ymin + amt*yRatio*ySize);
		setYmax(ymax - amt*(1-yRatio)*ySize);
	}
}
