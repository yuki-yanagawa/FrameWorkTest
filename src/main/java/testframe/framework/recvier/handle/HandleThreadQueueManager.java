package testframe.framework.recvier.handle;

import java.util.concurrent.ConcurrentLinkedQueue;

public class HandleThreadQueueManager {
	private static int threadCountMax_ = 10;
	private static HandleThreadQueueManager handleThread_ = new HandleThreadQueueManager();
	
	private ConcurrentLinkedQueue<HandleThread> queue_ = new ConcurrentLinkedQueue<>();
	
	private HandleThreadQueueManager() {
		
	}
	
	public static synchronized HandleThreadQueueManager getInstacnce(){ 
		if(handleThread_ == null) {
			handleThread_ = new HandleThreadQueueManager();
		}
		return handleThread_;
	}
	
	public synchronized void addHandleThread(HandleThread e) throws InterruptedException {
		while (threadCountMax_ < queue_.size()) {
			wait();
		}
		queue_.add(e);
		notifyAll();
	}

	public synchronized HandleThread getHandleThread() throws InterruptedException {
		while(queue_.size() <= 0) {
			wait();
		}
		HandleThread handleThread = queue_.poll();
		notifyAll();
		return handleThread;
	}
}
