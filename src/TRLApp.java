import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TRLApp
{
	private static CopyStore cStore;
	private static PatronStore pStore;
	private static OutController outController;
	private static InController inController;
	private static SellController sellController;

	public static void main(String[] args)
	{
		cStore = new CopyStore();
		pStore = new PatronStore();

		outController = new OutController(pStore, cStore);
		inController = new InController(pStore, cStore);
		sellController = new SellController(pStore, cStore);

		StdOut.println("Welcome to TRLApp.");
		
		boolean quitting = false;

		while (!quitting)
		{
			printMenu();
			String cmd = getCommand();

			switch (cmd)
			{
			case "1":
				doCheckOut();
				break;
			case "2":
				doCheckIn();
				break;
			case "3":
				doDisplayPatronInfo();
				break;
			case "5":
				addHold();
				break;
			case "4":
				doSelling();
				break;
			case "0":
				StdOut.println("exiting...");
				quitting = true;
				break;
			default:
				StdOut.println("Enter a valid command.");
				break;
			}
		}
		StdOut.println("Thank you for choosing TRLApp.");
	}

	private static void addHold()
	{
		StdOut.print("Enter patron ID:");
		String pid = StdIn.readString();
		Patron p = outController.enterPatronForCheckOut(pid);
		while (true)
		{
			StdOut.println("Enter hold type:1. Overdue  2. Bad behavior. 0 to finish");
			int i = StdIn.readInt();
			if (i == 0)
				break;
			p.addHold(new Hold(i));
		}
	}
	
	private static void ifHasHold(Patron p)
	{
		ArrayList<Hold> hold = p.getHolds();
		
		if (!hold.isEmpty())
		{
			StdOut.println("ALERT!!! This patron has holds.");
			for (Hold h : hold)
				StdOut.print(h.getHoldName() + "   ");
			StdOut.println();
		}
		
	}
	
	private static void doCheckOut()
	{
		StdOut.println("Checking copies out...");
		StdOut.println("Enter Patron ID:");
		String pid = StdIn.readString();
		StdOut.println("You entered: " + pid);

		boolean result = outController.startOutTransaction(); // pStore.fetchPatron(pid);

		Patron p = outController.enterPatronForCheckOut(pid);
		ifHasHold(p);
		StdOut.println("Checking out copies to patron: " + p);

		while (true)
		{
			String copyID = getCopyID();

			if (copyID.equals("0"))
				break;

			Copy c = null;
			c = outController.enterCopyGoingOut(copyID); // change
			if (c != null)								 // change
			{
				StdOut.println("Checking out copy: ");
				StdOut.println(c);
			}
			else
				StdOut.println("Bad copy: reenter:");

		}

		outController.endOutTransaction();

		StdOut.println("End of doCheckOut()");
	}

	private static String getCopyID()
	{
		StdOut.println("Enter copyID to check out, 0 to finish:");
		String copyID = StdIn.readString();
		return copyID;
	}
	
	private static void doCheckIn()
	{
		StdOut.println("Checking copies in...");
		StdOut.println("Enter Patron ID:");
		String pid = StdIn.readString();

		StdOut.println("You entered: " + pid);

		boolean result = inController.startInTransaction(); // pStore.fetchPatron(pid);

		Patron p = inController.enterPatronForCheckIn(pid);
		ifHasHold(p);
		StdOut.println("Checking in copies from patron: " + p);

		while (true)
		{
			StdOut.println("Enter copyID to check in, 0 to finish:");
			String copyID = StdIn.readString();
			if (copyID.equals("0"))
				break;

			Copy c = inController.enterCopyGoingIn(copyID); // how to indicate
															// copy is already
															// checked out?

			if (c != null)
			{
				c = inController.enterCopyGoingIn(copyID);
				StdOut.println("Checking in copy: ");
				StdOut.println(c);
			}
			else
				StdOut.println("Bad copy: reenter:");
		}

		inController.endInTransaction();

		StdOut.println("End of doCheckIn()");
	}

	private static void doDisplayPatronInfo()
	{
		StdOut.println("Enter patron ID: ");
		String pid = StdIn.readString();

		Patron p = outController.getPatronInfo(pid);
		StdOut.println(p);
	}

	private static void printMenu()
	{
		StdOut.println("Select an option:\n");
		StdOut.println("1 => Start check out transaction");
		StdOut.println("2 => Start check in transaction");
		StdOut.println("3 => Display Patron Info");
		StdOut.println("4 => Sell copy to patron");
		StdOut.println("5 => Add Hold to patron");
		StdOut.println("0 => Quit");
	}

	private static String getCommand()
	{
		return StdIn.readString();
	}
	
	private static void doSelling()
	{
		Scanner input = new Scanner(System.in);
		int copyNumbers = 0;
		StdOut.println("Selling copies...");
		StdOut.println("Enter Patron ID:");
		String pid = StdIn.readString();

		StdOut.println("You entered: " + pid);
		
		Patron p = sellController.enterPatronIDForSale(pid);
		ifHasHold(p);
		StdOut.println("Selling copies to patron: " + p);
		int copiesSold = 0;

		while (true)
		{
			System.out.println("Enter the number of copies, 0 to finish: ");
			if (cStore.getCopiesCount() > 0)
				System.out.println(cStore.getCopiesCount() + " copies in stock.");
			else
			{
				System.out.println("No stock now, you have to finish, so go finish.");
				break;
			}
			try
			{
				copyNumbers = input.nextInt();
			}
			catch (InputMismatchException e)
			{
				System.out.print("Invalid input. Enter again. ");	
				input.next();
			}
			if (copyNumbers == 0)
				break;
			else
			{
				sellController.enterCopyNumsGoingOut(copyNumbers);
				cStore.reduceCopiesCount(copyNumbers);
				copiesSold += copyNumbers;
			}
		}
		
		System.out.println("Sold " + copiesSold + ". Sale end. NO RETURNS!!!");
	}
}
