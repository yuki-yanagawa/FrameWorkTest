package testframe.gui.controller;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.controller.ControllerFrame;

public class JavaScriptFileController extends ControllerFrame {
	public HttpResFrame main() {
		return js("frontparts/js/main.js");
	}

	public HttpResFrame jqueryMain() {
		return js("frontparts/js/jquery-3.7.1.min.js");
	}
}
