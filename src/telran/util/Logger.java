package telran.util;

import java.time.Instant;
import java.time.ZoneId;

public class Logger {
	
	private Level level;
	private Handler handler;
	private String name;
	
	@SuppressWarnings("static-access")
	public Logger(Handler handler, String name) {
		setLevel(level.INFO);
		this.name = name;
		this.handler = handler;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	@SuppressWarnings("static-access")
	public void error(String message) {
		this.setLevel(level.ERROR);
		createAndPublishLoggerRecord(message);
	}
	
	@SuppressWarnings("static-access")
	public void warn(String message) {
		this.setLevel(level.WARN);
		createAndPublishLoggerRecord(message);
	}
	
	@SuppressWarnings("static-access")
	public void info(String message) {
		this.setLevel(level.INFO);
		createAndPublishLoggerRecord(message);
	}
	
	@SuppressWarnings("static-access")
	public void debug(String message) {
		this.setLevel(level.DEBUG);
		createAndPublishLoggerRecord(message);
	}
	
	@SuppressWarnings("static-access")
	public void trace(String message) {
		this.setLevel(level.TRACE);
		createAndPublishLoggerRecord(message);
	}

	private void createAndPublishLoggerRecord(String message) {
		Instant timeStamp = Instant.now();
		String zoneId = ZoneId.systemDefault().getId();
		LoggerRecord loggerRecord = new LoggerRecord(timeStamp, zoneId, this.level, this.name, message);
		handler.publish(loggerRecord);
	}
	
}
