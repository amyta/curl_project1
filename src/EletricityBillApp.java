import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EletricityBillApp {
	public static void main(String[] args) {
		// Declare variables
		Scanner sc = new Scanner(System.in);
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		double rate = 0;
		double overCharge = 1.10;
		double excessCharge = 1.25;	
		double limit1 = 350;
		double limit2 = 500;
		double over;
		double excess;
		double total;
		
		Date now = new Date(); // Get current date and time				
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("Date: " + sdf.format(now));
		
		System.out.print("Please enter your name (Last, First) > ");
		String name = sc.nextLine();
	
	// Validate Date Entered
	String billingDate = null;
	boolean valid = false;	
	while (!valid) {
		try {
		// Original Scanner
		Scanner scandate = new Scanner(System.in);
		System.out.print("Meter reading date > ");
		billingDate = scandate.nextLine();

		sdf.setLenient(false);
		sdf.parse(billingDate);
		valid = true;
		
		// New Scanner
		Scanner scandate2 = new Scanner(billingDate);
		scandate2.useDelimiter("/");
		
		// Parse input for individual values (MM/dd/yyyy)
		int month = scandate2.nextInt();
		
		if (month == 12 | month == 1 | month == 2)
			rate = .10;
		else if (month >= 3 && month < 6)
			rate = .12;
		else 
			rate = .15;
	
		} catch (Exception e) {
			System.out.println("Date entered is not valid. Please try again.");
		}	
	} 
	// Validate kwused input
	Scanner sc3 = new Scanner(System.in);
	String kwusedEntered = null;
	double base;
	boolean isValid = false;
	while (isValid == false) {
	try {	
		System.out.print("Electricity Used (KW) > ");
		kwusedEntered = sc3.nextLine();
		Integer.parseInt(kwusedEntered);
		isValid = true;
		
		Scanner sc4 = new Scanner(kwusedEntered);
		int kwused = sc4.nextInt();
	
		System.out.println("\nSouthwest Power & Light\nBilling Statement");
		System.out.println("\nName: " + name);
		System.out.println("Meter reading date: " + billingDate);
		System.out.println("Electricity Used (KW): " + kwusedEntered + "\n");
		
		if (kwused >= 0 && kwused <= 350) {
			base = kwused*rate;
			base = Math.round(base*100)/100.;
			over = 0;
			over = Math.round(over*100)/100.;
			excess = 0;
			excess = Math.round(excess*100)/100.;
			total = base + over + excess;
			System.out.printf("%-25s %6s\n", "Baseline charge ", fmt.format(base));
			System.out.printf("%-25s %6s\n", "Over-baseline charge ", fmt.format(over));
			System.out.printf("%-25s %6s\n", "Excess charge ", fmt.format(excess));
			System.out.printf("%-25s %7s\n", "\nTotal amount due: ", fmt.format(total));
		}
		else if (kwused > 350 && kwused <= 500) {
			base = limit1*rate;
			base = Math.round(base*100)/100.;
			over = (kwused-limit1)*(rate*overCharge);
			over = Math.round(over*100)/100.;
			excess = 0;
			excess = Math.round(excess*100)/100.;
			total = base + over + excess;
			System.out.printf("%-25s %6s\n", "Baseline charge ", fmt.format(base));
			System.out.printf("%-25s %6s\n", "Over-baseline charge ", fmt.format(over));
			System.out.printf("%-25s %6s\n", "Excess charge ", fmt.format(excess));
			System.out.printf("%-25s %7s\n", "\nTotal amount due: ", fmt.format(total));
		}
		else if (kwused > 500) {
			base = limit1*rate;
			base = Math.round(base*100)/100.;
			over = (limit2-limit1)*(rate*overCharge);
			over = Math.round(over*100)/100.;
			excess = (kwused-limit2)*(rate*excessCharge);
			excess = Math.round(excess*100)/100.;
			total = base + over + excess;
			System.out.printf("%-25s %6s\n", "Baseline charge ", fmt.format(base));
			System.out.printf("%-25s %6s\n", "Over-baseline charge ", fmt.format(over));
			System.out.printf("%-25s %6s\n", "Excess charge ", fmt.format(excess));
			System.out.printf("%-25s %7s\n", "\nTotal amount due: ", fmt.format(total));
		}
		sc4.close();
		} catch (Exception e) {
			System.out.println("Please enter only numeric values.");
		} // End Try Catch for kw used
		System.out.println("\nThank you for letting us serve you!");		
		}
	sc.close();
	sc3.close();
	}
}