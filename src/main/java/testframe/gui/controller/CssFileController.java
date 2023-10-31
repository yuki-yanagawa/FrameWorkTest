package testframe.gui.controller;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.controller.ControllerFrame;
import testframe.gui.model.FileCreaterModel;

public class CssFileController extends ControllerFrame {
	public <T> HttpResFrame cssFile(T args) {
		FileCreaterModel fileCreaterModel = (FileCreaterModel)args;
		String filename = fileCreaterModel.getRequestData();
		return css("frontparts" + filename);
	}
}
