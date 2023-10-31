package testframe.framework.router;

import java.util.Map;
import java.util.Optional;

public class RouterActionClassPathCreater {
	public static String createActionClassPath(String requestMethod, String requestData) {
		Map<String,Object> routesrFileMapAllList = RouterFileReader.getInstance().getRouterFileMap();
		Map<String,String> routerFileMap = (Map<String,String>)routesrFileMapAllList.get(requestMethod);
		if(requestData.startsWith("/js/")) {
			return createJSActionClassPath(routerFileMap, requestData);
		} else if(requestData.startsWith("/css/")) {
			return createCSSActionClassPath(routerFileMap, requestData);
		} else {
			return routerFileMap.get(requestData);
		}
	}
	
	private static String createJSActionClassPath(Map<String,String> routerFileMap ,String requestData) {
		Optional<String> retPathOpt = routerFileMap.keySet().stream().filter(e -> {
			if(requestData.matches(e + ".*\\.js")) {
				return true;
			} else {
				return false;
			}
		}).findFirst();
		if(!retPathOpt.isPresent()) {
			return null;
		}
		String key = retPathOpt.get();
		return routerFileMap.get(key);
	}

	private static String createCSSActionClassPath(Map<String,String> routerFileMap ,String requestData) {
		Optional<String> retPathOpt = routerFileMap.keySet().stream().filter(e -> {
			if(requestData.matches(e + ".*\\.css")) {
				return true;
			} else {
				return false;
			}
		}).findFirst();
		if(!retPathOpt.isPresent()) {
			return null;
		}
		String key = retPathOpt.get();
		return routerFileMap.get(key);
	}
}
