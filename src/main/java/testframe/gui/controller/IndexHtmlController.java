package testframe.gui.controller;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.controller.ControllerFrame;

public class IndexHtmlController extends ControllerFrame {
	public HttpResFrame main() {
		return html("frontparts/html/index.html");
	}
}
