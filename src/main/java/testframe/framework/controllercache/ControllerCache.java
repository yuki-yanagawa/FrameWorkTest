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
	
	public synchronized void addCacheData(String path, HttpResFrame data) {
		if(cacheData.size() >= CASHESIZE) {
			cleanCache();
		}
		cacheData.put(path, data);
		accessMap.put(path, 0);
	}
	
	public HttpResFrame getData(String path) {
		int accesMapVal = accessMap.get(path) + 1;
		accessMap.put(path, accesMapVal);
		return cacheData.get(path);
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
