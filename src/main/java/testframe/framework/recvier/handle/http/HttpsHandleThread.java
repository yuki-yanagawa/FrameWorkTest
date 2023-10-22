package testframe.framework.recvier.handle.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.net.ssl.SSLSocket;

import testframe.framework.common.log.Logger;
import testframe.framework.recvier.handle.HandleThread;
import testframe.framework.recvier.handle.exception.HttpCreateResponseException;

public class HttpsHandleThread extends HandleThread {
	public HttpsHandleThread(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		Thread.currentThread().setName("HttpsHandleThread");
		Logger.info(Thread.currentThread().getName(), "client connected. Client Handle Start..");
		SSLSocket sslsocket = (SSLSocket)clientSocket_;
		try(InputStream is = sslsocket.getInputStream();
			OutputStream os = sslsocket.getOutputStream()) {
			byte[] recvbyte = new byte[HttpHandleUtil.getRecvBufize()];
			is.read(recvbyte);
			String httpRequest = new String(recvbyte);
			String responseMess = createResponseMess(httpRequest);
			os.write(responseMess.getBytes());
			os.flush();
			sslsocket.close();
		} catch(IOException e) {
			
		} 
	}

	private String createResponseMess(String httpRequest) {
		String retMess = "";
		try {
			retMess = HttpHandleUtil.httpRequestAnalizeAndCreateResponse(httpRequest);
		} catch (HttpCreateResponseException e) {
			Logger.warn("[Failed Connect Client ip Addr :" + clientSocket_.getInetAddress() + " / port :" + clientSocket_.getPort() + "]");
			retMess = e.createResponseHeaderBad();
		}
		return retMess;
	}
}
