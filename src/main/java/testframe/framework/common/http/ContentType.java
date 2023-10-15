package testframe.framework.common.http;

public enum ContentType {
	HTML("html"),
	JS("javascript"),
	CSS("css"),
	JSON("json");
	ContentType(String typeName) {
		typeName_ = typeName;
	}
	private String typeName_;
	public String getName() {
		return typeName_;
	}
	
	public String getContentType() {
		String retStr = "";
		switch(typeName_) {
		case "html":
			retStr = "content-type: text/html; charset='utf-8'\r\n";
			break;
		case "javascript":
			retStr = "content-type: text/javascript; charset='utf-8'\r\n";
			break;
		case "css":
			retStr = "content-type: text/css; charset='utf-8'\r\n";
			break;
		case "json":
			retStr = "content-type: application/json; charset='utf-8'\r\n";
			break; 	
		}
		return retStr;
	}
}
