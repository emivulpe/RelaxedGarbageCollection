package uk.ac.glasgow.etparser;

import static org.junit.Assert.*;

import java.awt.Event;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.glasgow.etparser.events.CreationEvent;
import uk.ac.glasgow.etparser.handlers.SimulatedHeap;

public class ETParserTest {

	private ETParser parser;
	private InputStream is;

	@Before
	public void setUp() throws Exception {
		is=new FileInputStream("C:/Users/Emi/Desktop/traces/beforeborn.txt");
		parser=new ETParser(is);
	}



	@Test
	public void testConstructor() {
		ETParser et=new ETParser(is);
		assertEquals(0,et.getLines());
		assertTrue(et.getHandlers()!=null);
		assertTrue(SimulatedHeap.getTheHeap()!=null);
		
	}
	
	@Test
	public void testProcessFile() {
		parser.processFile();
		assertEquals(5,parser.getLines());
	}
	
	@Test
	public void testInitialiseHandlers() {
		assertEquals(parser.getHandlers().size(),8);
	}
	
	@Test
	public void testRegister() {
		parser.registerHandler(new EventHandlerTester());
		assertEquals(parser.getHandlers().size(),9);
	}
	
	@Test
	public void testNotifyHandlers() {
		CreationEvent e=new CreationEvent("s wt hye4 10a");
		EventHandlerTester tester = new EventHandlerTester();
		parser.registerHandler(tester);
		parser.notifyHandlers(e);
		assertTrue(tester.getHandled());
	}

	@Test
	public void testPrintReport() {
		EventHandlerTester tester = new EventHandlerTester();
		parser.registerHandler(tester);
		parser.printReport();
		assertEquals(tester.getReport(),"well done!");
	}

}
