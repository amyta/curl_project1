import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElectricityBillingApp2 {
	public static void main(String[] args) {
		// Declare Scanner 1
		Scanner repeat = new Scanner(System.in); // Scanner for Calculating another bill
		String choice = "y | yes";
		while (!choice.equalsIgnoreCase("n")) {

			double rate = 0;
			double overCharge = 1.10; // Over-baseline charge for KW used
			double excessCharge = 1.25; // Excess charger for KW used
			double tier1 = 350; // Cap of baseline KW used
			double tier2 = 500; // Cap of over-baseline KW used
			double over = 0;
			double excess = 0;
			double total= 0;

			Scanner nameInput = new Scanner(System.in); // Scanner for name input
			NumberFormat fmt = NumberFormat.getCurrencyInstance();

			System.out
					.println("\nSouthwest Power & Light\nBilling Statement\n");

			Date now = new Date(); // Get current date and time
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println("Date: " + sdf.format(now));

			System.out.print("Please enter your name (Last, First) > ");
			String name = nameInput.nextLine();

			// Validate Date Entered
			String billingDate = null;
			boolean valid = false;
			while (!valid) {
				try {
					Scanner scandate = new Scanner(System.in); // Scanner #1 for scanning date
					System.out.print("Meter reading date > ");
					billingDate = scandate.nextLine();

					sdf.setLenient(false);
					sdf.parse(billingDate);
					valid = true;

					Scanner scandate2 = new Scanner(billingDate); // Scanner #2 for scanning date
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
					System.out
							.println("Date entered is not valid. Please try again.");
				}
			}
			// Validate KW Used input
			Scanner sc1 = new Scanner(System.in); // Scanner #1 for KW used input
			String kwusedEntered = null;
			double base = 0;
			boolean isValid = false;
			while (isValid == false) {
				try {
					System.out.print("Electricity Used (KW) > ");
					kwusedEntered = sc1.nextLine();
					Integer.parseInt(kwusedEntered);
					isValid = true;

					Scanner sc2 = new Scanner(kwusedEntered); // Scanner #2 for KW used input
					int kwused = sc2.nextInt();

					System.out.println("\nName: " + name);
					System.out.println("Meter reading date: " + billingDate);
					System.out.println("Electricity Used (KW): "
							+ kwusedEntered + "\n");

					if (kwused >= 0 && kwused <= 350) {
						base = kwused * rate;
						over = 0;
						excess = 0;
					} else if (kwused > 350 && kwused <= 500) {
						base = tier1 * rate;
						over = (kwused - tier1) * (rate * overCharge);
						excess = 0;
					} else if (kwused > 500) {
						base = tier1 * rate;
						over = (tier2 - tier1) * (rate * overCharge);
						excess = (kwused - tier2) * (rate * excessCharge);
					}
				} catch (Exception e) {
					System.out.println("Please enter only whole numeric values.");
				} // End Try Catch for KW used
			}
			base = Math.round(base * 100) / 100.;
			over = Math.round(over * 100) / 100.;
			excess = Math.round(excess * 100) / 100.;
			total = base + over + excess;		
			
			System.out.printf("%-25s %6s\n", "Baseline charge ",
					fmt.format(base));
			System.out.printf("%-25s %6s\n",
					"Over-baseline charge ", fmt.format(over));
			System.out.printf("%-25s %6s\n", "Excess charge ",
					fmt.format(excess));
			System.out.printf("%-25s %8s\n",
					"\nTotal amount due: ", fmt.format(total)
							+ "\n");
			
			System.out.println("Thank you for letting us serve you!\n");
			
			System.out
					.print("Would you like to calculate another bill: (y/n) > ");
			choice = repeat.next();
			System.out.print("You've entered: " + choice + "\n");
		}
		System.out.println("\nThank you for using Southwest's Billing App.");
	}
}