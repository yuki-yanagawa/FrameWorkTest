package testframe.framework.taskmanager;

import testframe.framework.taskmanager.reciverhandle.ReciverHandleThreadTaskManager;

public class TaskManagerEntry {
	public static void taskManagerStart() {
		ReciverHandleThreadTaskManager reciverHandleThreadTaskManager = new ReciverHandleThreadTaskManager();
		reciverHandleThreadTaskManager.execute();
	}
}
