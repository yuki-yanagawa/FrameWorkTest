package testframe.gui.model;

import java.util.HashMap;
import java.util.Map;

import testframe.framework.performance.PerformanceChecker;

public class HTMLInsideMapCreater {
	public static Map<String, Object> createInsideMap(String requestData) {
		if(requestData.equals("/") || requestData.equals("/index.html")) {
			return creatInsideIndex();
		} else {
			return new HashMap<>();
		}
	}
	
	private static Map<String, Object> creatInsideIndex() {
		Map<String, Object> retMap = new HashMap<>();
		PerformanceChecker performanceChecker = PerformanceChecker.getInstance();
		retMap.put("processId", performanceChecker.getProcessId());
		retMap.put("startTime", performanceChecker.getStartTime());
		return retMap;
	}
}
 