package telran.util;

import java.io.PrintStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class SimpleStreamHandler implements Handler {
	
	private PrintStream stream;
	
	public SimpleStreamHandler(PrintStream stream) {
		this.stream = stream;
	}

	@Override
	public void publish(LoggerRecord loggerRecord) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
				.withLocale(Locale.UK)
				.withZone(ZoneId.systemDefault());
		String instantTime = formatter.format(loggerRecord.timestamp);
		stream.printf("%s %s, %s %s, message: %s\n", 
				instantTime, loggerRecord.zoneId, loggerRecord.level.toString(), 
				loggerRecord.loggerName, loggerRecord.messege);
		
	}

}
