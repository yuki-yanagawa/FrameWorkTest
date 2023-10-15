package testframe.framework.recvier.handle;

import java.net.Socket;

abstract public class HandleThread extends Thread {
	protected Socket clientSocket_;
	public HandleThread(Socket socket) {
		clientSocket_ = socket;
	}
}
