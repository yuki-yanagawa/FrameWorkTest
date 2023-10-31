package testframe.gui.model.factory;

import java.util.Map;

import testframe.gui.model.FileCreaterModel;
import testframe.gui.model.HTMLFileCreaterModel;
import testframe.gui.model.HTMLInsideMapCreater;
import testframe.gui.model.StandartFileCreaterModel;

public class FileCreaterModelFactory {
	public static FileCreaterModel createFileCreaterModel(String requestData) {
		if(requestData.matches(".*\\.html$") || requestData.equals("/")) {
			Map<String, Object> paramData = HTMLInsideMapCreater.createInsideMap(requestData);
			return new HTMLFileCreaterModel(requestData, paramData);
		} else {
			return new StandartFileCreaterModel(requestData);
		}
	}
}
