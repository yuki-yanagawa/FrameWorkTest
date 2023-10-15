package testframe.framework.recvier.handle.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import testframe.framework.common.log.Logger;
import testframe.framework.recvier.handle.HandleThread;
import testframe.framework.recvier.handle.exception.HttpCreateResponseException;

public class HttpHandleThread extends HandleThread {
	public HttpHandleThread(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		Thread.currentThread().setName("HttpHandleThread");
		Logger.info(Thread.currentThread().getName(), "client connected. Client Handle Start..");
		try(InputStream is = clientSocket_.getInputStream();
			OutputStream os = clientSocket_.getOutputStream()) {
			byte[] recvbyte = new byte[HttpHandleUtil.getRecvBufize()];
			is.read(recvbyte);
			String httpRequest = new String(recvbyte);
			String responseMess = createResponseMess(httpRequest);
			os.write(responseMess.getBytes());
			os.flush();
			clientSocket_.close();
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
