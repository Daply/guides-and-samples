package multithreading;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

// public void run(): is used to perform action for a thread.
//
// start() method of Thread class is used to start a newly 
// created thread. It performs following tasks:
//
//    A new thread starts(with new callstack).
//    The thread moves from New state to the Runnable state.
//    When the thread gets a chance to execute, 
//    its target run() method will run.

// !!! What if we call run() method directly instead start() method?
// Each thread starts in a separate call stack.
// Invoking the run() method from main thread, 
// the run() method goes onto the current call 
// stack rather than at the beginning of a new call stack.

// The join() method waits for a thread to die. 
// In other words, it causes the currently running threads 
// to stop executing until the thread it joins with completes its task.

// 3 constants defined in Thread class:
//
//    public static int MIN_PRIORITY
//    public static int NORM_PRIORITY
//    public static int MAX_PRIORITY


public class Multi1 extends Thread {
	
	public void run(){  
		for (int i = 0; i < 5; i++) {  
			try {
			  Thread.sleep(500);
			}
			catch(InterruptedException e){
				System.out.println(e);
			}  
			System.out.println(i);  
		}  
		System.out.println(this.getName() + " running with priority " + this.getPriority());
	}  
	
	public static void main(String args[]) {  
		Multi1 t1 = new Multi1();  
		Multi1 t2 = new Multi1();   
		t1.start();  
		t2.start();   
		// if calling thread twice
		//t1.start();  // java.lang.IllegalThreadStateException
		
		// if run directly run method
		// here t1 and t2 will be treated as 
		// normal object not thread object. 
		System.out.println("test run() directly");
		t1.run();  
		t2.run();
		
		// join method
		System.out.println("test join()");
		Multi1 t3=new Multi1();  
		Multi1 t4=new Multi1();  
		Multi1 t5=new Multi1();  
		t3.start();  
		try {  
			t3.join(500);
			// t3.join();  
		} 
		catch(Exception e) {
			System.out.println(e);
		}  
		  
		t4.start();  
		t5.start();  
		
		// priorities
		System.out.println("test priorities");
		Multi1 m1=new Multi1();  
		Multi1 m2=new Multi1();  
		m1.setName("min priority thread");
		m2.setName("max priority thread");
		m1.setPriority(Thread.MIN_PRIORITY);  
		m2.setPriority(Thread.MAX_PRIORITY);  
		m1.start();  
		m2.start();
		
		// test daemon thread
		System.out.println("test daemon thread");
		Multi1 d1=new Multi1();  
		Multi1 d2=new Multi1();  
		d1.setName("Daemon");
		d2.setName("Not daemon");
		d1.setDaemon(true);
		d1.start();
		d2.start();
	}  
}
