import java.util.ArrayList;

public class Patron
{
	private String name;
	private String patronID;
	private ArrayList<Copy> copiesOut;
	private ArrayList<Hold> holds; // Some holds for this patron

	public Patron(String n, String id)
	{
		this.name = n;
		this.patronID = id;
		this.copiesOut = new ArrayList<Copy>();
		this.holds = new ArrayList<Hold>();
	}

	@Override
	public boolean equals(Object p)
	{
		if (!(p instanceof Patron))
			return false;
		
		Patron p2 = (Patron)p;
		
		if (this.patronID.equals(p2.patronID))
				return true;
		else
			return false;
	}
	
	public ArrayList<Copy> getCopiesOut()
	{
		return this.copiesOut;
	}
	
	public ArrayList<Hold> getHolds()
	{
		return this.holds;
	}
	
	public void addHold(Hold h)
	{
		this.holds.add(h);
	}
	public boolean checkCopyOut(Copy c)
	{
		c.setOutTo(this);
		copiesOut.add(c);
		return true;
	}

	public boolean checkCopyIn(Copy c)
	{
		c.setOutTo(null);
		if (copiesOut.contains(c))
		{
			copiesOut.remove(c);
			return true;
		}
		else
			return false;
	}

	public String toString()
	{
		String toReturn = "Patron w/ name: " + this.name + ", id: " + this.patronID;

		if (this.copiesOut.isEmpty())
		{
			toReturn = toReturn + "\nNo copies checked out.\n";
		}
		else
		{
			toReturn = toReturn + "\nCopies checked out:";
			for (Copy copy : this.copiesOut)
			{	
				toReturn = toReturn + "\n\t" + copy.getCopyID() + "\n";
			}
		}
		
		if (this.holds.isEmpty())
			toReturn = toReturn + "\nNo hold.\n";
		else
		{
			toReturn = toReturn + "\nHolds:";
					for (Hold hold: this.holds)
						toReturn = toReturn + "\n\t" + hold.getHoldName() + "\n";
		}
		return toReturn;
	}

	public static void main(String[] args)
	{
		Patron p1 = new Patron("James", "007");

		System.out.println(p1);
		
		String s = "foo";
		
		StdOut.println (p1.equals(s));
		
	}

}
