package testframe.framework.common.properties;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropFileReader {
	private static Properties prop_ = new Properties();
	private static String defFilePath_ = "def/server.properties"; 
	
	public static void refresh() {
		Path path = Paths.get(defFilePath_);
		try {
			FileReader fr = new FileReader(path.toFile());
			prop_.load(fr);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProperties() {
		if(prop_ == null) {
			prop_ = new Properties();
			refresh();
		}
		return prop_;
	}
}
