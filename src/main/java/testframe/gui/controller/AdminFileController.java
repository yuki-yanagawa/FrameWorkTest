package testframe.gui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.common.properties.PropFileReader;
import testframe.framework.controller.ControllerFrame;
import testframe.gui.model.PropertiesFileModel;

public class AdminFileController extends ControllerFrame {
	public HttpResFrame main() {
		List<PropertiesFileModel> list = new ArrayList<>();
		Properties prop = PropFileReader.getProperties();
		for(Object key : prop.keySet()) {
			Object value = prop.get(key);
			list.add(new PropertiesFileModel(key, value));
		}
		return adminHtml("frontparts/html/propfile.html", list);
	}
}
