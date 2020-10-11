package input.and.output;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

// The class java.io.File can represent either a file or a directory.


// https://www3.ntu.edu.sg/home/ehchua/programming/java/J5b_IO.html
public class InputOutputExample {
	
	public static long startTime;
	
	public static void startTime() {
		startTime = System.currentTimeMillis();
	}
	
	public static void endTime() {
		long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        double seconds = (double)elapsedTime/1000;
        double minutes = 0;
        double hours = 0;
        if (seconds < 1) {
        	System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
        }
        else {
	        if (seconds > 60) {
	            minutes = (double)seconds/60;
	            seconds = seconds%60;
	        }
	        if (minutes > 60) {
	            hours = (double)minutes/60;
	        }
	        System.out.println("Elapsed Time: " + (int)hours + " hours " + 
	                            (int)minutes + " minutes " + 
	                            (int)seconds + " seconds");
        }
	}
	
	public static void showAllInnerFiles(File dir) {
		if (dir.isDirectory()) {
		    File[] items = dir.listFiles();
		    for (File item : items) {
		         System.out.println(item.getAbsoluteFile());
		         if (item.isDirectory()) 
		        	 showAllInnerFiles(item);
		    }
		}
	}
	
    public static void main(String[] args) throws IOException {
    	
    	

    	startTime();
    	// fast file reader custom 
    	FastFileReader s = new FastFileReader("src//input//and//output//file1.in");
        BufferedOutputStream out=new BufferedOutputStream(System.out);
		StringBuilder sb=new StringBuilder();
		
		while (s.hasNext()) {
			sb.append(s.readLine());
		}

        out.write(sb.toString().getBytes());
		out.flush();
        endTime();
    }
    
}
