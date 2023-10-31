package testframe.framework.common.http;

import java.time.LocalDateTime;

import testframe.framework.common.properties.PropFileReader;

public class HttpResponseHeader {
	private static String serverName;
	public static String getServerName() {
		if(serverName == null) {
			serverName = (String)PropFileReader.getProperties().get("settingPort");
		}
		return serverName;
	}

	public static String createResponseHeaderBad() {
		String retStr = "";
		retStr = "HTTP/1.1 404 BAD\r\n";
		retStr = retStr + "server : " + getServerName() + "\r\n";
		retStr = retStr + "date : " + LocalDateTime.now().toString() + "\r\n";
		retStr = retStr + "connection : close\r\n";
		retStr = retStr + "\r\n";
		return retStr;
	}
	
	public static String createResponseHeaderSuccess(ContentType type) {
		String retStr = "";
		retStr = "HTTP/1.1 200 OK\r\n";
		switch(type) {
		case HTML:
			retStr = retStr + "content-type: text/html; charset='utf-8'\r\n";
			break;
		case CSS:
			retStr = retStr + "content-type: text/css; charset='utf-8'\r\n";
			break;
		case JS:
			retStr = retStr + "content-type: text/javascript; charset='utf-8'\r\n";
			break;
		case JSON:
			retStr = retStr + "content-type: application/json; charset='utf-8'\r\n";
			break;
		}
		retStr = retStr + "server : " + getServerName() + "\r\n";
		retStr = retStr + "date : " + LocalDateTime.now().toString() + "\r\n";
		retStr = retStr + "connection : close\r\n";
		retStr = retStr + "\r\n";
		return retStr;
	}

	public static String createResponseHeaderSuccess(ContentType type, int fileSize) {
		String retStr = "";
		retStr = "HTTP/1.1 200 OK\r\n";
		retStr = retStr + "content-length : " + String.valueOf(fileSize + "\r\n".length()) + "\r\n";
		switch(type) {
		case HTML:
			retStr = retStr + "content-type: text/html; charset='utf-8'\r\n";
			break;
		case CSS:
			retStr = retStr + "content-type: text/css; charset='utf-8'\r\n";
			break;
		case JS:
			retStr = retStr + "content-type: text/javascript; charset='utf-8'\r\n";
			break;
		case JSON:
			retStr = retStr + "content-type: application/json; charset='utf-8'\r\n";
			break;
		}
		retStr = retStr + "server : " + getServerName() + "\r\n";
		retStr = retStr + "date : " + LocalDateTime.now().toString() + "\r\n";
		retStr = retStr + "connection : close\r\n";
		retStr = retStr + "\r\n";
		return retStr;
	}

	public static String createResponseHeaderInternalServerError() {
		String retStr = "";
		retStr = "HTTP/1.1 500 Internal Server Error\r\n";
		retStr = retStr + "server : " + getServerName() + "\r\n";
		retStr = retStr + "date : " + LocalDateTime.now().toString() + "\r\n";
		retStr = retStr + "connection : close\r\n";
		retStr = retStr + "\r\n";
		return retStr;
	}

	public static String createResponseHeaderInternalServerError(String body) {
		String retStr = "";
		retStr = "HTTP/1.1 500 Internal Server Error\r\n";
		retStr = retStr + "server : " + getServerName() + "\r\n";
		retStr = retStr + "date : " + LocalDateTime.now().toString() + "\r\n";
		retStr = retStr + "connection : close\r\n";
		retStr = retStr + "\r\n";
		retStr = retStr + body + "\r\n\r\n";
		return retStr;
	}
}
