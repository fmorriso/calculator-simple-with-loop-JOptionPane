// import java.util.Scanner;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        DialogHelper.MakeDialogsEasierToSee(24);
        displayJavaVersion();
        boolean keepCalculating = true;
        while (keepCalculating) {
            performOneCalculation();
            keepCalculating = askIfWeShouldContinueCalculating();
        }
        JOptionPane.showMessageDialog(null, "Thank you for using my calculator", "Thank you", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Performs one calculation by asking the user for two numbers and an operation
     * to perform on those
     * two numbers. The numbers, the type of operation and the results of the
     * calculation are displayed.
     */
    private static void performOneCalculation() {
        double a = promptForNumber("Enter the first number >");
        double b = promptForNumber("Enter the second number >");
        String operation = getOperation();
        double result = performOperation(a, b, operation);
        String display = String.format("result of %.2f %s %.2f = %.2f%n", a, operation, b, result);
        // System.out.println(display));
        JOptionPane.showMessageDialog(null, display, "Your results", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * @param a - the first number involved in the operation
     * @param b - the second number involved in the operation
     * @param operation - the type of operation to be performed.  Valid operations are "+" (addition), "-" subtraction,
     *                     "*" (multiplication), "/" (division) or "%" (modulo).
     * @return - a double precision value with the results of the operation on the two numbers.
     */
    private static double performOperation(double a, double b, String operation) 
    {
        switch (operation)
        {
            case "+":
                return a = b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "%":
                return a % b;
            default:
                return Double.NaN;
        }        
    }

    private static double promptForNumber(String msg) 
    {
        // Scanner kb = new Scanner(System.in);
        double num = Double.NaN;
        //System.out.print(msg);
        boolean needValidNumber = true;
        do {
            try {
                //num = Double.parseDouble(kb.nextLine());
                String resp = JOptionPane.showInputDialog(null, msg);                
                if(resp == null) throw new NumberFormatException();
                num = Double.parseDouble(resp);                
                needValidNumber = false;
            } catch (NumberFormatException ex) {
                // System.out.println("That's not a number. Try again.");
                JOptionPane.showMessageDialog(null, "Invalid choice. Try again.", "Invalid number", JOptionPane.ERROR_MESSAGE);

            }
        } while (needValidNumber);
        return num;
    }

    private static String getOperation() {
        //Scanner kb = new Scanner(System.in);
        String validOperations = "+-*/%";
        String operation = "";
        boolean waitingForValidOperation = true;
        String prompt = String.format("What operation do you want to perform (%s) ?", validOperations);
        //System.out.println("Valid operations are " + validOperations);
        do {
            //System.out.print("What operation do you wnat to perform?>");
            //operation = kb.nextLine();
            operation = JOptionPane.showInputDialog(null, prompt);
            if (validOperations.indexOf(operation) >= 0)
                waitingForValidOperation = false;
            else
                //System.out.println("Invalid choice. Try again.");
                JOptionPane.showMessageDialog(null, "Invalid choice. Try again.", "Invalid Operation", JOptionPane.ERROR_MESSAGE);

        } while(waitingForValidOperation);
        return operation;

    }

    /**
     * Asks the user if they want to continue calculating.
     *
     * @return - true if the user wants to continue calculating; otherwise, returns
     *         false.
     */
    private static boolean askIfWeShouldContinueCalculating() {
        //Scanner kb = new Scanner(System.in);
        boolean awaitingValidResponse = true;
        boolean result = false;
        // final String prompt = "Do you want to make another calculation (y/n or press Enter key for yes)?>";
        // final String errorMessage = "Invalid response. Try again";
        while (awaitingValidResponse) {
            String question = "Do you want to make another calculation (y/n)?";
            //System.out.print(question);
            //String response = kb.nextLine();
            // String response = JOptionPane.showInputDialog(null, prompt,"Perform another calculation?", JOptionPane.PLAIN_MESSAGE);
            int resp = JOptionPane.showConfirmDialog(null, question, "Another calculation?", JOptionPane.YES_NO_OPTION);
            if(resp == JOptionPane.YES_OPTION) {
                awaitingValidResponse = false;
                return true;
            }
            awaitingValidResponse = false;
            return false;

            /*
            // no response at all is the same as Yes
            if (response.length() == 0) {
                result = true;
                awaitingValidResponse = false;
                continue;
            }
            // trim response to a single, lowercase value
            response = response.trim().substring(0, 1).toLowerCase();
            // all we want is just y or n
            if (response.equals("n")) {
                result = false;
                awaitingValidResponse = false;
                continue;
            }
            if (response.equals("y")) {
                result = true;
                awaitingValidResponse = false;
                continue;
            }
            */
            // if we get this far, the user did not give us a proper response,
            // so let them know and ask again.
            
            //System.out.println("Invalid response. Try again.");            
        }
        return result;
    }

    private static void displayJavaVersion() {
        String version = System.getProperty("java.runtime.version");
        System.out.println("java.runtime.version=" + version);
        /*String msg = String.format("Using Java version %s", version);
        JOptionPane.showMessageDialog(null, msg);*/
    }
}