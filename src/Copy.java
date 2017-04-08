
public class Copy
{
	private String copyID;
	private Patron outTo;

	public Copy(String cid)
	{
		this.copyID = cid;
	}

	// following generated in Eclipse Source menu

	public Patron getOutTo()
	{
		return outTo;
	}

	public void setOutTo(Patron outTo)
	{
		this.outTo = outTo;
	}

	public String getCopyID()
	{
		return copyID;
	}

	public String toString()
	{
		return "Copy w/id= " + this.copyID + " out to: " + getOutTo();
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Copy))
			return false;

		return ((Copy) o).getCopyID().equals(this.copyID); // yuck.
	}

	public static void main(String[] args)
	{
		Copy c1 = new Copy("047");
		Patron p1 = new Patron("James", "S007");

		System.out.println(c1.toString());
		System.out.println(p1);
		String[] array = new String[47];
		
		StdOut.println(array.toString());
	}
}
