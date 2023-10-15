package testframe.framework.router;

import java.lang.reflect.Method;

public class RouterContainer {
	public static <T> T callFunction(String path, Object... arglist) throws Exception {
		String[] pathList = path.split("\\.");
		String method = pathList[pathList.length - 1];
		path = path.substring(0,path.length() - method.length() - 1);
		
		Class<?> actionClass = (Class<?>)Class.forName(path);
		// Constructor is Default
		// getConstructor can search constructor from args getContructr((String)a) => ClassConst(String a)
		Object action = actionClass.getConstructor().newInstance();
		Method[] methods = actionClass.getDeclaredMethods();
		for(Method m : methods) {
			if(m.getName().equals(method)) {
				return (T)m.invoke(action, arglist);
			}
		}
		return null;
	}
}
