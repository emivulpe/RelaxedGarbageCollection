import static org.junit.Assert.*;

import org.junit.Test;


public class MainTest {

	@Test
	public void test1() {
		assertTrue(Main.testing("C:/Users/Emi/Desktop/traces/normal.txt.tar.gz"));
	}
	@Test
	public void test2() {
		assertFalse(Main.testing("C:/Users/Emi/Desktop/traces/after dead.txt.tar.gz"));
	}
	@Test
	public void test3() {
		assertFalse(Main.testing("C:/Users/Emi/Desktop/traces/not born.txt.tar.gz"));
	}
}
