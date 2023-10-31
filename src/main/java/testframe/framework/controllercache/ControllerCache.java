package testframe.framework.controllercache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import testframe.framework.common.http.HttpResFrame;

public class ControllerCache {
	private static ControllerCache controllerCache_ = new ControllerCache();
	private static int CASHESIZE = 20;
	private ConcurrentHashMap<String, HttpResFrame> cacheData = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, Integer> accessMap = new ConcurrentHashMap<>();
	private ControllerCache() {
		
	}
	public static synchronized ControllerCache getInstance() {
		if(controllerCache_ == null) {
			controllerCache_ = new ControllerCache();
		}
		return controllerCache_;
	}
	
	public synchronized void addCacheData(String requestData, HttpResFrame data) {
		if(cacheData.size() >= CASHESIZE) {
			cleanCache();
		}
		cacheData.put(requestData, data);
		accessMap.put(requestData, 0);
	}
	
	public HttpResFrame getData(String requestData) {
		int accesMapVal = accessMap.get(requestData) + 1;
		accessMap.put(requestData, accesMapVal);
		return cacheData.get(requestData);
	}

	private void cleanCache() {
		double minval = accessMap.entrySet().stream().mapToDouble(m -> m.getValue()).min().getAsDouble();
		List<String> pathList = accessMap.entrySet().stream().filter(e -> e.getValue() <= minval).map(e -> e.getKey()).collect(Collectors.toList());
		for(String path : pathList) {
			accessMap.remove(path);
			cacheData.remove(path);
		}
	}
	
	public synchronized boolean existData(String key) {
		return cacheData.containsKey(key);
	}
}
