import java.util.*;

public class APYInterestCalculator {
    public static void main(String[] args) {
        try (
            Scanner input = new Scanner(System.in);
        ) {
            // Instantiate Variables
            double apy = -1, apr = -1, balance, interest, accured = -1, sum = 0;
            float comp = 1f;
            String choice = "";
            
            do {
                // Prompt user for APY
                System.out.print("Enter your APY (Annual Percentage Yield): ");
                apy = input.nextDouble();

                // Create error/exception if APY input is negative value
                if (apy < 0) {
                    System.out.printf("%nError:\t%.2f is invalid. APY cannot be negative%n%n", apy);
                }
            } while (apy < 0);
            
            // Prompt user for compounding-frequency of APY
            System.out.printf("%nDaily = 0\tWeekly = 1\tMonthly = 2%n"
            		+ "How often does your Annual Percentage Yield Compound?: ");

            // Fill Number of Periods value and store choice
            switch (input.nextInt()) {
                case 0:
                    comp = 365f;
                    accured = 365;
                    choice = "Daily";
                    break;
                    
                case 1:
                    comp = 52f;
                    accured = 7;
                    choice = "Weekly";
                    break;
                    
                case 2:
                    comp = 12f;
                    accured = 31;
                    choice = "Monthly";
                    break;
            }
            // Calculate and print APR
            apr = (((Math.pow((apy / 100) + 1, (1 / comp))) - 1) * comp) * 100;

            System.out.printf("%nAn APY of %.2f%c compounding %s is equivalant to: %.2f%c APR%n", apy, '%', choice, apr, '%');

            // Trim APR to two decimal positions
            apr = Math.round(apr * 100.0);
            apr = (apr / 100) / comp;
            do {
                // Prompt user for balance
                System.out.printf("%nPlease enter your balance to calculate your estimated %s interest: ", choice);
                balance = input.nextDouble();

                // Throw an error if a negative value is entered
                if (balance < 0) {
                    System.out.printf("%nError:\t%.2f is invalid. Balance cannot be negative%n%n", balance);
                }
            } while (balance < 0);

            System.out.printf("%n%n");

            for (int i = 1; i <= comp; i++) {
                interest = (balance * apr) / 100;
                balance += interest;
                sum += interest;
                System.out.printf("%s INTEREST #%d:" +
                    "%n\t\t\t Daily Estimated Interest: $%,.4f" +
                    "%n\t\t\t %s Earned Interest: $%,.4f" +
                    "%n\t\t\t Updated Balance: $%,.2f %n",
                    choice, i, interest / accured, choice, interest, balance);
            }
            System.out.printf("%n%n" +
                "Starting Balance:\t\t$%,.2f %n" +
                "Earning %s interest at: \t%.2f APY %n" +
                "Total Interest Earned in Year:\t$%,.2f %n" +
                "Ending Balance:\t\t\t$%,.2f",
                balance - sum, choice, apy, sum, balance);
            
        } catch (InputMismatchException e) {
            System.out.printf("%nEXCEPTION: %s%nRestart the program and be sure to input the correct type.", e.toString());
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
