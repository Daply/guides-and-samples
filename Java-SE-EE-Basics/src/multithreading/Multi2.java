package multithreading;

class Multi2 implements Runnable { 
	
    private String message;  
    
    public Multi2(String s) {  
        this.message = s;  
    }  
     public void run() {  
        System.out.println(Thread.currentThread().getName() + " (Start) message = " + message);  
        processmessage(); //call processmessage method that sleeps the thread for 2 seconds  
        System.out.println(Thread.currentThread().getName() + " (End)");//prints thread name  
    }  
    private void processmessage() {  
        try {  
        	Thread.sleep(2000);
        } 
        catch (InterruptedException e) { 
        	e.printStackTrace();
        }  
    }  
}  
