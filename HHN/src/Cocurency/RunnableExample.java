package Cocurency;

import java.util.concurrent.TimeUnit;

public class RunnableExample implements Runnable{ //lambda expressions java 8
//	public static void main(String[] args) {
//        Runnable myRunnable = () -> {
//            for (int i = 1; i <= 5; i++) {
//                System.out.println("Thread: " + Thread.currentThread().getId() + ", Count: " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        //Thread thread1 = new Thread();
//        Thread thread = new Thread(myRunnable);
//        thread.start();
//    }
	 public void run() {
	        for (int i = 1; i <= 4; i++) {
	            System.out.println("Thread: " + Thread.currentThread().getId() + ", Count: " + i);
	            try {
	                Thread.sleep(1000); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }finally {
	            	// System.out.println("Done");
	            }
	        }
	    }

	    public static void main(String[] args) {
	    	RunnableExample myRunnable = new RunnableExample();
	        Thread thread = new Thread(myRunnable);
	        thread.start(); 
	    }
}
