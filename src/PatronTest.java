import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PatronTest
{
	private Copy c;
	private Copy c2;
	private Patron p;

	@Before
	public void setUp() throws Exception
	{
//		StdOut.println("setUp() called");
		p = new Patron("Uncle Bob","4747");
		// Patron p2 = new Patron("Uncle Bob","4747");
//		StdOut.println(p.equals(p2));
//		StdOut.println(p==p2);
//		
		c = new Copy("001");
		c2 = new Copy("002");
	
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCheckCopyOut()
	{
//		StdOut.println("testCheckCopyOut called");
		
		p.checkCopyOut(c);
		
		assertEquals("check if single copy out to patron", p, c.getOutTo());
		
		assertTrue("single copy out from patron's view", p.getCopiesOut().contains(c));

	}

	@Test
	public void testCheckSecondCopyOut()
	{
//		StdOut.println("testCheckCopyOut called");
		
		p.checkCopyOut(c);
		p.checkCopyOut(c2);
		
		assertEquals("check if 2nd copy out to patron", p, c2.getOutTo());

	}

	@Test
	public void testCheckCopyIn()
	{
//		StdOut.println("testCheckCopyOut called");
		fail("Not yet implemented");
	}

}
