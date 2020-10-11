package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

// Multithreading is a process of executing multiple threads simultaneously.
//
// Multitasking is a process of executing multiple tasks simultaneously.
//
// Multitasking can be achieved in two ways:
// -- Process-based Multitasking (Multiprocessing)
//     - Each process has an address in memory. 
//       In other words, each process allocates 
//       a separate memory area.
//     - A process is heavyweight.
//     - Cost of communication between the process 
//       is high.
//     - Switching from one process to another 
//       requires some time for saving and loading 
//       registers, memory maps, updating lists, etc.
// -- Thread-based Multitasking (Multithreading)
//     - Threads share the same address space.
//     - A thread is lightweight.
//     - Cost of communication between the thread is low.
//
// The life cycle of the thread in java is controlled by JVM. 
// The java thread states are as follows:
//
//    New
//    Runnable
//    Running
//    Non-Runnable (Blocked)
//    Terminated

// Daemon thread in java is a service provider 
// thread that provides services to the user thread. 
// Its life depend on the mercy of user threads i.e. 
// when all the user threads dies, JVM terminates this 
// thread automatically.

// Java Thread pool represents a group of worker threads 
// that are waiting for the job and reuse many times.

// A ThreadGroup represents a set of threads. 
// A thread group can also include the other thread 
// group. The thread group creates a tree in which every thread 
// group except the initial thread group has a parent.
//
// A thread is allowed to access information about its own 
// thread group, but it cannot access the information about 
// its thread group's parent thread group or any other thread groups.

// Java thread group is implemented by java.lang.ThreadGroup class.

//The JVM shuts down when:
//
//    user presses ctrl+c on the command prompt
//    System.exit(int) method is invoked
//    user logoff
//    user shutdown etc.

// The addShutdownHook(Thread hook) method
//
// The addShutdownHook() method of Runtime class 
// is used to register the thread with the Virtual Machine.

// Java Runtime class is used to interact with java runtime environment. 
// Java Runtime class provides methods to execute a process, invoke GC, 
// get total and free memory etc. There is only one instance of 
// java.lang.Runtime class is available for one java application.
//
// The Runtime.getRuntime() method returns the singleton instance of Runtime class.


public class MultithreadingExample {
		
	     public static void main(String[] args) {  
	         ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads  
	         for (int i = 0; i < 10; i++) {  
	             Runnable worker = new Multi2("" + i);  
	             executor.execute(worker);//calling execute method of ExecutorService  
	         }  
	         executor.shutdown();  
	         while (!executor.isTerminated()) {   }  
	   
	         System.out.println("Finished all threads");  
	     }  
}
