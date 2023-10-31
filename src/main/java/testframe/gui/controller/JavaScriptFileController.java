package testframe.gui.controller;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.controller.ControllerFrame;
import testframe.gui.model.FileCreaterModel;

public class JavaScriptFileController extends ControllerFrame {	
	public <T> HttpResFrame jsFile(T args) {
		FileCreaterModel fileCreaterModel = (FileCreaterModel)args;
		String filename = fileCreaterModel.getRequestData();
		return js("frontparts" + filename);
	}
}
