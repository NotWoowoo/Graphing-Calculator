package main;

import lawt.engine.Window;

public class Main {

	public static void main(String... args) {
		double s = 1.4;
		
		Window w = new Window((int)(1280*s), (int)(720*s), "better than desmos");
		Graph graph = new Graph();
		Controller controls = new Controller(w, graph);
		
		while(true){
			w.executeFrame();
			controls.update();
		}
	}

}
