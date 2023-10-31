package testframe.framework.performance;

public class PerformanceChecker {
	private static PerformanceChecker performanceChecker = new PerformanceChecker();
	private long processId;
	private long startTime;
	public static PerformanceChecker getInstance() {
		return performanceChecker;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
