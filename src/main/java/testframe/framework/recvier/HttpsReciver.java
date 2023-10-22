package testframe.framework.recvier;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import testframe.framework.common.log.Logger;
import testframe.framework.common.properties.PropFileReader;
import testframe.framework.recvier.handle.HandleThreadQueueManager;
import testframe.framework.recvier.handle.http.HttpHandleThread;
import testframe.framework.recvier.handle.http.HttpsHandleThread;

public class HttpsReciver {
	private SSLServerSocket svrSock_;
	private boolean serverExeuteFlg = true;
	public HttpsReciver() throws Exception {
		int defautPortObj = Integer.parseInt(PropFileReader.getProperties().get("settingPort").toString());
		try {
			settingRecevier(defautPortObj);
		} catch(Exception e) {
			throw e;
		}
	}

	public void httpReciverStart() throws IOException {
		Thread.currentThread().setName("HttpsReciver");
		while(serverExeuteFlg) {
			try {
				SSLSocket socket = (SSLSocket)svrSock_.accept();
				HandleThreadQueueManager.getInstacnce().addHandleThread(new HttpsHandleThread(socket));
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

	private void settingRecevier(int port) throws Exception {
		// create key store
		FileInputStream fis = new FileInputStream("key/server.p12");
		KeyStore ks = KeyStore.getInstance("pkcs12");
		ks.load(fis, "TEST".toCharArray());

		//X509形式 = 証明書　管理するマネージャー
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, "TEST".toCharArray());
		
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(kmf.getKeyManagers(), null, null);
		
		SSLServerSocketFactory ssf = ctx.getServerSocketFactory();
		svrSock_ = (SSLServerSocket)ssf.createServerSocket(port);
	}

	private void closeServerSocket() throws IOException {
		if(svrSock_ != null) {
			svrSock_.close();
		}
	}
}
