package testframe.gui.controller;

import java.util.HashMap;
import java.util.Map;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.controller.ControllerFrame;

public class TestAPI extends ControllerFrame {
	public HttpResFrame testapi(Map<String,String> argslist) {
		Map<String,String> map = new HashMap<>();
		map.put("test", "testOK123444");
		return json(map);
	}
}
