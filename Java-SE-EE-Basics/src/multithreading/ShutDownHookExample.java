package multithreading;

  
public class ShutDownHookExample{  
	
	public class MyThread extends Thread{  
	    public void run(){  
	        System.out.println("shut down hook task completed..");  
	    }  
	}  
	
	public static void main(String[] args)throws Exception {  
		ShutDownHookExample she = new ShutDownHookExample();
		Runtime r = Runtime.getRuntime();  
		r.addShutdownHook(she.new MyThread());  
		      
		System.out.println("Now main sleeping... press ctrl+c to exit");  
		try{
			Thread.sleep(3000);
		}
		catch (Exception e) {
			
		}  
	}  
}  