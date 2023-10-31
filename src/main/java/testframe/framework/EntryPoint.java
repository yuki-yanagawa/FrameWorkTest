package testframe.framework;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import testframe.framework.common.properties.PropFileReader;
import testframe.framework.performance.PerformanceChecker;
import testframe.framework.recvier.HttpReciver;
import testframe.framework.recvier.HttpsReciver;
import testframe.framework.taskmanager.TaskManagerEntry;

public class EntryPoint {
	public static void main(String[] args) {
		try {
			//Peformance Checker
			PerformanceChecker perfomanceCheck = PerformanceChecker.getInstance();
			RuntimeMXBean rm = ManagementFactory.getRuntimeMXBean();
			long processId = Long.parseLong(rm.getName().split("@")[0]);
			long startTime = rm.getStartTime();
			perfomanceCheck.setProcessId(processId);
			perfomanceCheck.setStartTime(startTime);
			
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
