import java.util.HashMap;

public class PatronStore
{
	private HashMap<String, Patron> patronHash;

	public PatronStore()
	{
		patronHash = new HashMap<String, Patron>();

		// load sample Patron records
		patronHash.put("S000", new Patron("Eric", "S000"));
		patronHash.put("S001", new Patron("Linda", "S001"));
		patronHash.put("S002", new Patron("Anna", "S002"));
		patronHash.put("S003", new Patron("Moxie", "S003"));
	}

	public Patron fetchPatron(String patronID)
	{
		return patronHash.get(patronID); // null if not there
	}

//	public static void main(String[] args)
//	{
//		PatronStore pStore = new PatronStore();
//
//		Patron p = pStore.fetchPatron("S001");
//		System.out.println(p.toString());
//
//		Copy c = new Copy("4747");
//		p.checkCopyOut(c);
//
//		System.out.println(p);
//	}
}
