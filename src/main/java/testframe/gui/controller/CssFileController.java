package testframe.gui.controller;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.controller.ControllerFrame;

public class CssFileController extends ControllerFrame {
	public HttpResFrame main() {
		return css("frontparts/css/main.css");
	}
}
