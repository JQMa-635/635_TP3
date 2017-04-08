import java.util.ArrayList;

class SellController
{
	private PatronStore pStore;
	private CopyStore cStore;
	private Patron currentPatron;

	private Patron patron;
	public SellController(PatronStore ps, CopyStore cs)
	{
		this.pStore = ps;
		this.cStore = cs;
	}

	
	public Patron enterPatronForCheckOut(String patronID)
	{
		this.currentPatron = this.pStore.fetchPatron(patronID);
		return currentPatron;
	}

	public void enterCopyNumsGoingOut(int i)
	{
		if (cStore.getCopiesCount() == 0)
		{
			System.out.println("Currently no stock.");
		}
		else if (cStore.getCopiesCount() < i)
		{
			StdOut.println("Not enough copies in stock. Have only " + cStore.getCopiesCount() + " copies left.");
		}
	}

	public Patron enterPatronIDForSale(String patronID)
	{
		this.patron = pStore.fetchPatron(patronID);
		return this.patron;
	}

	public Patron getPatronInfo(String patronID) // new added System Event
	{
		return pStore.fetchPatron(patronID);
	}
	
	public int getCopiesCount()
	{
		return cStore.getCopiesCount();
	}
}