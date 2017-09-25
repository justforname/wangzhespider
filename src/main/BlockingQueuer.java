package main;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueuer {
	public static void main(String[] agrs) throws InterruptedException{
		ExecutorService service = Executors.newCachedThreadPool();
		BlockingQueue queue = new LinkedBlockingQueue(5);
		Consumer consumer = new Consumer(queue);
		Producer producer = new Producer(queue);
		for(int i=0;i<10;i++)
		service.execute(producer);
		service.execute(consumer);
		Thread.sleep(10*1000);
		service.shutdown();
	}
}


class Producer implements Runnable{
	private boolean isRunning = true;
	private BlockingQueue queue;
	private static AtomicInteger count = new AtomicInteger();
	private static final int DEFAULT_RANGE_FOR_SLEEP = 1;
	
    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String data = null;
		Random r = new Random();
		System.out.println("启动生产者线程!");
	    try {
            for(int i=0;i<50;i++) {
                System.out.println("正在生产数据...");
                //Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                data = "data:" + count.incrementAndGet();
                System.out.println("将数据：" + data + "放入队列...");
                if (!queue.offer(data, 10, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
		
	}
	
	
}


class Consumer implements Runnable{

	private BlockingQueue queue;
	private int DEF = 1000;
	
	public Consumer(BlockingQueue queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("启动消耗进程");
		boolean isRunning = true;
		try {
			while(isRunning){
				System.out.println("正在读取队列数据");
				String data = (String) queue.poll(2, TimeUnit.SECONDS);
				if(null!=data){
					Thread.sleep(200);
					System.out.println("拿到数据:"+data);
				}else{
					isRunning = false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}