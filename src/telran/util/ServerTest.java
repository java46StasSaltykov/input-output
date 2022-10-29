package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;
import java.net.*;
import org.junit.jupiter.api.*;
import telran.net.ServerStringAppl;

public class ServerTest {
	
	static PrintStream output;
	static BufferedReader input;
	static Socket socket;
	
	@BeforeAll
	static void createConnection() throws Exception{
		socket = new Socket("localhost", ServerStringAppl.PORT);
		output = new PrintStream(socket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	@AfterAll
	static void closeConnection() throws Exception {
		socket.close();
	}
	
	@Test
	void normalFlowTest() throws IOException {
		output.println("length#12345");
		String response = input.readLine();
		assertEquals("5", response);
		output.println("reverse#12345");
		response = input.readLine();
		assertEquals("54321", response);
	}
	
	@Test
	void wrongRequestTest() throws IOException {
		output.println("length12345");
		String response = input.readLine();
		assertEquals("Wrong request: should be <type>#<string>", response);
	}
	
	@Test
	void wrongRequestType() throws IOException {
		output.println("lenth#12345");
		String response = input.readLine();
		assertEquals("Wrong request type, should be either 'reverse' or 'length'", response);
	}
}
