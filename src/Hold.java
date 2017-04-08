class Hold
{
	// Overdue copy
	// Bad behavior
	//
	private String name;
	
	
	public Hold(String h)
	{
		this.name = h;
	}
	
	public Hold(int i)
	{
		if (i == 1)
			this.name = "Overdue";
		else if (i == 2)
			this.name = "Bad Behavior";
	}
	
	
	public String getHoldName()
	{
		return name;
	}
	
}