import java.util.Scanner;

public class APYInterestCalculator {
	public static void main(String[] args) throws UnsupportedInputException {
		try (
			Scanner input = new Scanner(System.in);
		){
			// Instantiate Variables
			double apy, apr, balance, interest, accured = -1, sum = 0;
			String choice = "";
			float comp = 1f;
			
			// Prompt user for APY
			System.out.print("Enter your APY (Annual Percentage Yield): ");
			apy = input.nextDouble();
			
			// Create error/exception if APY input is negative value
			if (apy < 0) {
				throw new UnsupportedInputException(apy, "APY cannot be negative");
			}
			
			// Prompt user for compounding-frequency of APY
			System.out.printf("%nDaily = 0\tWeekly = 1\tMonthly = 2%n");
			System.out.print("How often does your Annual Percentage Yield Compound?: ");
			
			// Fill Number of Periods value and store choice
			switch (input.nextInt()) {
			
			case 0:
				comp = 365f;
				accured = 365;
				choice = "daily";
				break;
			case 1:
				comp = 52f;
				accured = 7;
				choice = "weekly";
				break;
			case 2:
				comp = 12f;
				accured = 31;
				choice = "monthly";
				break;
			}
			// Calculate and print APR
			apr = (((Math.pow((apy / 100) + 1, (1/comp))) - 1) * comp) * 100;
			
			System.out.printf("%nAn APY of %.2f%c compounding %s is equivalant to: %.2f%c APR%n"
														, apy, '%', choice, apr, '%');
			
			// Trim APR to two decimal positions
			apr = Math.round(apr * 100.0);
			apr = (apr / 100) / comp;
			
			// Prompt user for balance
			System.out.printf("%nPlease enter your balance to calculate your estimated %s interest: ", choice);
			balance = input.nextDouble();
			
			// Throw an error if a negative value is entered
			if (balance < 0) {
				throw new UnsupportedInputException(balance, "balance cannot be negative");
			}
			
			System.out.println();
			System.out.println();
			
			for(int i = 1; i <= comp; i++) {
				interest = (balance * apr) / 100;
				balance += interest;
				sum += interest;
				System.out.printf("%s INTEREST #%d:"
								+ "%n\t\t\t Daily Estimated Interest: $%,.4f"
								+ "%n\t\t\t Total Earned Interest: $%,.4f"
								+ "%n\t\t\t Updated Balance: $%,.2f %n",
												choice.toUpperCase(), i, interest / accured ,interest, balance);
			}
			System.out.printf("%n%n"
							+ "Starting Balance:\t\t$%,.2f %n"
							+ "Earning %s interest at: \t%.2f APY %n"
							+ "Total Interest Earned in Year:\t$%,.2f %n"
							+ "Ending Balance:\t\t\t$%,.2f", 
							balance - sum, choice, apy, sum, balance);
		} catch (UnsupportedInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class UnsupportedInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double negativeWeight;
	private String reason;
	
	public UnsupportedInputException(double negativeWeight, String reason) {
		setNegativeWeight(negativeWeight);
		setReason(reason);
	}

	public double getNegativeWeight() {
		return negativeWeight;
	}

	public void setNegativeWeight(double negativeWeight) {
		this.negativeWeight = negativeWeight;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return String.format("ERROR: %.1f input-- %s Please restart the program and enter proper input.", getNegativeWeight(), getReason());
	}
}
