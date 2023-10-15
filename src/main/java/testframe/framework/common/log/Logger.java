package testframe.framework.common.log;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import testframe.framework.common.properties.PropFileReader;

public class Logger {
	private static Logger logger = new Logger();
	private static String logFilePath_ = "log/logfile_";
	private LogLevel logLevel_ = null;
	enum LogLevel {
		INFO(0),
		WARN(1),
		ERROR(2);
		LogLevel(int value) {
			logLevelValue = value;
		}
		private int logLevelValue = 0;
		public int getLogLevelValue() {
			return logLevelValue;
		}
	}
	
	private Logger() {
		readSettingLevel();
	}
	
	private static synchronized Logger getInstance() {
		if(logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	public void readSettingLevel() {
		String logLevel = (String)PropFileReader.getProperties().get("logLevel");
		switch(logLevel) {
		case "INFO":
			logLevel_ = LogLevel.INFO;
			break;
		case "WARN":
			logLevel_ = LogLevel.WARN;
			break;
		case "ERROR":
			logLevel_ = LogLevel.ERROR;
			break;
		default:
			logLevel_ = LogLevel.ERROR;
			break;
		}
	}
	
	public static void warn(String message) {
		warn("",message);
	}

	public static void warn(String threadName, String message) {
		writeLog(threadName, message, LogLevel.WARN);
	}
	
	public static void error(String message) {
		error("", message);
	}
	
	public static void error(String threadName, String message) {
		writeLog(threadName, message, LogLevel.ERROR);
	}
	
	public static void info(String message) {
		info("",message);
	}
	
	public static void info(String threadName, String message) {
		writeLog(threadName, message, LogLevel.INFO);
	}
	
	private synchronized static void writeLog(String threadName, String message, LogLevel loglvl) {
		Logger loggertmp = getInstance();
		LogLevel loggerlvl = loggertmp.getLoglevel();
		if(loggerlvl.getLogLevelValue() < loglvl.getLogLevelValue()) {
			return;
		}
		String datetime = dateTime();
		String filePath = logFilePath_ + datetime;

		message = loglvl.name() + "/[ Date : " +  dateTimeDetail() + "\t" + threadName + "] :  "+  message; 

		try(FileWriter fw = new FileWriter(Paths.get(filePath).toFile(), true)) {
			fw.write(message + "\r\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private LogLevel getLoglevel() {
		return logLevel_;
	}
	
	private static String dateTime() {
		return LocalDateTime.now().toLocalDate().toString();
	}
	
	private static String dateTimeDetail() {
		return LocalDateTime.now().toString();
	}
	
}
