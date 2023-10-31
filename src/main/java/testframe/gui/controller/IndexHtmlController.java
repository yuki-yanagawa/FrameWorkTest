package testframe.gui.controller;


import testframe.framework.common.http.HttpResFrame;
import testframe.framework.controller.ControllerFrame;
import testframe.gui.model.FileCreaterModel;

public class IndexHtmlController extends ControllerFrame {
	public <T> HttpResFrame main(T args) {
		return html("frontparts/html/index.html", (FileCreaterModel)args);
	}
}
