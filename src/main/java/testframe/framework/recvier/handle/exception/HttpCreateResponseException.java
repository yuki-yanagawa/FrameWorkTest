package testframe.framework.recvier.handle.exception;

import testframe.framework.common.http.HttpResponseHeader;

public class HttpCreateResponseException extends Exception {
	public HttpCreateResponseException(String mess) {
		super(mess);
	}
	
	public String createResponseHeaderBad() {
		return HttpResponseHeader.createResponseHeaderBad();
	}
}
