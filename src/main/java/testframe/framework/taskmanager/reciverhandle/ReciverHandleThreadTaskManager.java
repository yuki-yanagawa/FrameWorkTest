package testframe.framework.taskmanager.reciverhandle;

import testframe.framework.common.log.Logger;
import testframe.framework.recvier.handle.HandleThread;
import testframe.framework.recvier.handle.HandleThreadQueueManager;
import testframe.framework.taskmanager.TaskManager;

public class ReciverHandleThreadTaskManager implements TaskManager {
	@Override
	public void execute() {
		ReciverHandleThreadMain reciverHandleThreadMain = new ReciverHandleThreadMain();
		reciverHandleThreadMain.start();
	}

	class ReciverHandleThreadMain extends Thread {
		@Override
		public void run() {
			Thread.currentThread().setName("ReciverHandleThreadMain");
			while(true) {
				HandleThread handleThread = trytakeFromHandleThreadQueue();
				if(handleThread != null) {
					handleThread.start();
				}
			}
		}
		
		private  HandleThread trytakeFromHandleThreadQueue() {
			Logger.info(Thread.currentThread().getName(),"try take start......");
			HandleThread handleThread = null;
			try {
				handleThread = HandleThreadQueueManager.getInstacnce().getHandleThread();
			} catch(InterruptedException e) {
				Logger.error(Thread.currentThread().getName(), "Take HandleThread From Queue");
			}
			Logger.info(Thread.currentThread().getName(),"try take end......");
			return handleThread;
		}
	}
}
