package telran.util;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoggerTests {
	
	File logFile;
	Logger logger;

	@BeforeEach
	void setUp() {
		logFile = new File("logFile.txt");
		logFile.delete();
		logFile = new File("logFile.txt");
		try {
			PrintStream printStream = new PrintStream(logFile.getName());
			
			Handler handler = new SimpleStreamHandler(printStream);
			logger = new Logger(handler, logFile.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	void logTest() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(logFile.getName()));
		logger.error("error");
		assertTrue(reader.readLine().contains("error"));
		logger.warn("warning");
		assertTrue(reader.readLine().contains("warning"));
		logger.info("info");
		assertTrue(reader.readLine().contains("info"));
		logger.debug("debug");
		assertTrue(reader.readLine().contains("debug"));
		logger.trace("trace");
		assertTrue(reader.readLine().contains("trace"));
	}
	
	@Test
	void logToConsoleTest() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(logFile.getName()));
		logger.error("error");
		logger.warn("warning");
		logger.info("info");
		logger.debug("debug");
		logger.trace("trace");
		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
		reader.close();
	}

}
