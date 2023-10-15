package testframe.framework.recvier;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import testframe.framework.common.log.Logger;
import testframe.framework.common.properties.PropFileReader;
import testframe.framework.recvier.handle.HandleThreadQueueManager;
import testframe.framework.recvier.handle.http.HttpHandleThread;

public class HttpReciver {
	private ServerSocket svrSock_;
	private boolean serverExeuteFlg = true;
	public HttpReciver() throws IOException {
		int defautPortObj = Integer.parseInt(PropFileReader.getProperties().get("settingPort").toString());
		svrSock_ = new ServerSocket(defautPortObj);
	}
	
	public void httpReciverStart() throws IOException {
		Thread.currentThread().setName("HttpReciver");
		while(serverExeuteFlg) {
			try {
				Socket socket = svrSock_.accept();
				HandleThreadQueueManager.getInstacnce().addHandleThread(new HttpHandleThread(socket));
			} catch(InterruptedException e) {
				Logger.warn(Thread.currentThread().getName(), "HandleThread Add Queue Failed...");
			} catch(IOException e) {
				Logger.warn(Thread.currentThread().getName(), "Server Socket Accept Failed...");
			} catch(Error e) {
				Logger.error(e.getMessage());
				serverExeuteFlg = false;
			}
		}
		closeServerSocket();
	}
	
	private void closeServerSocket() throws IOException {
		if(svrSock_ != null) {
			svrSock_.close();
		}
	}
}
