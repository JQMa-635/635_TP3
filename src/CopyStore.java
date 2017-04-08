import java.util.HashMap;

public class CopyStore
{
	private HashMap<String, Copy> copies;
	private int copiesCount; // Record the number of copies.

	public CopyStore()
	{
		copies = new HashMap<String, Copy>();
		copiesCount = 0;

		// load sample Copy records
		copies.put("001", new Copy("001"));
		copiesCount++;
		copies.put("002", new Copy("002"));
		copiesCount++;
		copies.put("003", new Copy("003"));
		copiesCount++;
		copies.put("004", new Copy("004"));
		copiesCount++;

	}

	public Copy fetchCopy(String copyID)
	{

		return copies.get(copyID); // null if no such copy
	}
	
	public int getCopiesCount()
	{
		return copiesCount;
	}
	
	public void reduceCopiesCount(int i)
	{
		copiesCount -= i;
	}
	

}
