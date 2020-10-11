package multithreading;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

public class ThreadsDrawingExample {
	
	// frame
	private JFrame frame = null;
	private CanvasDrawing canvas = null; 
	
	private Color lineColor = null;
	
	public ThreadsDrawingExample() {
		this.lineColor = Color.RED;
		this.canvas = new CanvasDrawing(); 
		this.frame = new JFrame();
		this.frame.setSize(400, 400);
		this.frame.add(this.canvas);
		this.frame.setVisible(true);
	}
	
	public Color getColor() {
		return this.lineColor;
	}
	
	public void setColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	public void draw() {
		this.canvas.repaint();
	}
    
	public void launchThreeThreads() {
		ThreadsDrawing m1 = new ThreadsDrawing(Color.GREEN);  
		ThreadsDrawing m2 = new ThreadsDrawing(Color.BLUE);  
		ThreadsDrawing m3 = new ThreadsDrawing(Color.RED); 
        m1.start();
        m2.start();
        m3.start();
	}
	
	public void launchThreadsPool() {
		ExecutorService executor = Executors.newFixedThreadPool(5); //creating a pool of 5 threads 
		Random r = new Random();
        for (int i = 0; i < 10; i++) {  
        	float h = (float) r.nextGaussian();
            float s = (float) r.nextGaussian();
            float b = (float) r.nextGaussian();
            Runnable worker = new ThreadsDrawing(Color.getHSBColor(h, s, b));  
            executor.execute(worker); //calling execute method of ExecutorService  
        }  
        executor.shutdown();  
        while (!executor.isTerminated()) {   }  
  
	}
	
	public class ThreadsDrawing extends Thread{

		private Color color = null;
		
		public ThreadsDrawing (Color color) {
			this.color = color;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				setColor(this.color);
				draw();
				try {  
		        	//Thread.sleep(100);
					Thread.sleep(50);
		        } 
		        catch (InterruptedException e) { 
		        	e.printStackTrace();
		        }  
				System.out.println(this.getName() + " drawed " + i + " line");
			}
		}
	}
	
	public class CanvasDrawing extends Canvas {
		
		public CanvasDrawing() {
			setBackground(Color.WHITE); 
		}
		
	    @Override
		public void update(Graphics g) {
			// TODO Auto-generated method stub
			super.update(g);
			paint(g);
		}

		public void paint(Graphics g) {  
			drawRandomLine(g);
	    }  
		
		public void drawRandomLine(Graphics g) {  
	        Random r = new Random();
	        int x0 = r.nextInt(this.getWidth());
	        int x1 = r.nextInt(this.getWidth());
	        int y0 = r.nextInt(this.getWidth());
	        int y1 = r.nextInt(this.getWidth());
	        setForeground(getColor());  
	        g.drawLine(x0, y0, x1, y1); 
	        g.drawLine(x0+1, y0+1, x1+1, y1+1); 
		}
		
	}
    
    public static void main(String[] args) {  
    	ThreadsDrawingExample ex = new ThreadsDrawingExample();
    	ex.launchThreeThreads();
    	ex.launchThreadsPool();
    }

}
