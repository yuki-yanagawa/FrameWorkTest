package testframe.framework.common.http;

public class HttpResFrame {
	private final String header_;
	private final String body_;
	private final ContentType type_;
	
	public HttpResFrame(String header, String body, ContentType type) {
		header_ = header;
		body_ = body;
		type_ = type;
	}

	public String createResponse() {
		String header = header_;
		String body = body_;
		if(!header_.endsWith("\r\n")) {
			header = header + "\r\n";
		}
		if(!body.endsWith("\r\n")) {
			body = body + "\r\n";
		}
		return header + body + "\r\n";
	}
}
