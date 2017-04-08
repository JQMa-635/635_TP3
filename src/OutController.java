import java.util.ArrayList;

/*
 * 
	1. Worker starts new transaction.
		public boolean startOutTransaction()
		
	2. Worker enters Patron ID and SuD displays Patron info.
		public Patron enterPatron(patronID)
		
	3. Worker enters Copy ID and SuD displays Copy info.
		public Copy enterCopyOut(copyID)
		
		Repeat 3 for all copies
		
	4. Worker oks transaction and SuD checks out all entered Copies with due date.
		public void endOutTransaction()

 */

public class OutController
{
	private PatronStore pStore;
	private CopyStore cStore;
	private Patron currentPatron;
	private ArrayList<Copy> copiesEntered;

	public OutController(PatronStore ps, CopyStore cs)
	{
		this.pStore = ps;
		this.cStore = cs;
		this.copiesEntered = new ArrayList<Copy>();
	}

	public boolean startOutTransaction()
	{
	/* Smell:
	 * 	
	 * Self-encapsulation
	 * 
	 * Refactoring: Extract Method
	 */

		clearCopiesEntered();
		
		return true;
	}

	private void clearCopiesEntered()
	{
		this.copiesEntered.clear();
	}
	
	public Patron enterPatronForCheckOut(String patronID)
	{
		this.currentPatron = this.pStore.fetchPatron(patronID);
		return currentPatron;
	}

	public Copy enterCopyGoingOut(String copyID)
	{
		Copy copy = this.cStore.fetchCopy(copyID);
		if (copy.getOutTo() == null)
		{
			this.copiesEntered.add(copy);
			return copy;
		}
		else
		{
			StdOut.println("Copy already sold.");
			return null;
		}
	}

	public void endOutTransaction()
	{
		for (Copy copy : this.copiesEntered)
		{
			currentPatron.checkCopyOut(copy);
		}

	}

	public Patron getPatronInfo(String patronID) // new added System Event
	{
		return pStore.fetchPatron(patronID);
	}

//	public static void main(String[] args)
//	{
//		// TODO Auto-generated method stub
//	}
}
