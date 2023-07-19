package Cocurency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPool {

	    public static void main(String[] args) {
	        ExecutorService executor = Executors.newFixedThreadPool(3);

	        for (int j = 0; j < 5; j++) {
	            Runnable worker = new RunnableExample();
	            executor.execute(worker);
	    
	            //executor.shutdownNow();//chạy luồng đầu tiên đã tắt executor -> chỉ chạy luồng 14
	        }

	        //executor.shutdownNow(); //cả 3 luồng được chạy nhưng rồi bị tắt -> chạy đến j=2 là dừng
	        executor.shutdown(); //chạy 3 luồng từ j=0 đến j=2, sau đó 2 luồng chạy từ j=3 đến j=4 rồi mới tắt
	    }
	}
