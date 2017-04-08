import java.util.ArrayList;

/*
 
	1. Worker starts new transaction.
		public boolean startInTransaction()
		
	2. Worker enters Patron ID and SuD displays Patron info.
		public Patron enterPatronID (patronID)
		
	3. Worker enters Copy ID and SuD displays Copy info.
		public Copy enterCopyGoingIn (copyID)
		
		Repeat 3 for all copies
		
	4. Worker oks transaction and SuD checks in all entered Copies.
		public void endInTransaction()

 */
public class InController
{
	private PatronStore pStore;
	private CopyStore cStore;
	private ArrayList<Copy> copiesEntered;

	private Patron patron;

	public InController(PatronStore ps, CopyStore cs)
	{
		this.pStore = ps;
		this.cStore = cs;
		this.copiesEntered = new ArrayList<Copy>();
	}

	// 1. Worker starts new transaction.
	public boolean startInTransaction()
	{
		return true;
	}

	// 2. Worker enters Patron ID and SuD displays Patron info.
	public Patron enterPatronForCheckIn(String patronID)
	{
		this.patron = pStore.fetchPatron(patronID);
		return this.patron;
	}

	// 3. Worker enters Copy ID and SuD displays Copy info.

	public Copy enterCopyGoingIn(String copyID)
	{
		Copy c = this.cStore.fetchCopy(copyID);
		ArrayList<Copy> currentPatronCopies = this.patron.getCopiesOut();
		if (currentPatronCopies.contains(c))
		{
			this.copiesEntered.add(c);
			return c;
		}
		else
		{
			StdOut.println("Patron doesn't have this copy.");
			return null;
		}
	}

	// Repeat 3 for all copies

	// 4. Worker oks transaction and SuD checks in all entered Copies.

	public void endInTransaction()
	{
		for (Copy copy : this.copiesEntered)
		{
			patron.checkCopyIn(copy);
		}
	
	}

//	public static void main(String[] args)
//	{
//		// TODO Auto-generated method stub
//	}
}
