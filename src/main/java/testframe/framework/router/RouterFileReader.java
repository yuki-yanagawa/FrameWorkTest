package testframe.framework.router;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class RouterFileReader {
	private static RouterFileReader routerFileRead = new RouterFileReader();
	private Map<String,Object> routeFileMap = new HashMap<>(); 
	
	private RouterFileReader() {
		createFileMap();
	}
	
	public static RouterFileReader getInstance() {
		return routerFileRead;
	}

	private void createFileMap() {
		File routerFile = new File("def/router");
		try (FileInputStream fi = new FileInputStream(routerFile)){
			byte[] fileread = new byte[(int)Files.size(routerFile.toPath())];
			fi.read(fileread);
			String routesconf = new String(fileread);
			String[] routesconfLine = routesconf.split("\r\n");
			
			Map<String,Object> refmap = new HashMap<>();
			for(String r : routesconfLine) {
				String[] rLine = r.split("\\s+");
				refmap.put(rLine[1].trim(),rLine[2].trim());
				routeFileMap.put(rLine[0].trim(), refmap);
			}
		} catch(IOException e) {
			
		}
	}
	
	public Map<String,Object> getRouterFileMap() {
		return routeFileMap;
	}
}
