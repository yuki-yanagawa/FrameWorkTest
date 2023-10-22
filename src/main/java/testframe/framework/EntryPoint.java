package testframe.framework;

import testframe.framework.common.properties.PropFileReader;
import testframe.framework.recvier.HttpReciver;
import testframe.framework.recvier.HttpsReciver;
import testframe.framework.taskmanager.TaskManagerEntry;

public class EntryPoint {
	public static void main(String[] args) {
		try {
			//Properties File read
			PropFileReader.refresh();
			Thread.sleep(100);
			
			//Task Manager Start
			TaskManagerEntry.taskManagerStart();
			Thread.sleep(100);
			
			//HttpReciver Start
			//HttpReciver reciver = new HttpReciver();
			//reciver.httpReciverStart();
			HttpsReciver reciver = new HttpsReciver();
			reciver.httpReciverStart();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
