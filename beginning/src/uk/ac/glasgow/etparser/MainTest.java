package uk.ac.glasgow.etparser;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MainTest {

	@Test
	public void test1() {
		assertTrue(Main.testing("C:/Users/Emi/Desktop/traces/normal.txt.gz"));
	}

	@Test
	public void test2() {
		assertFalse(Main
				.testing("C:/Users/Emi/Desktop/traces/afterdeath.txt.gz"));
	}

	@Test
	public void test3() {
		assertFalse(Main
				.testing("C:/Users/Emi/Desktop/traces/beforeborn.txt.gz"));
	}
}
