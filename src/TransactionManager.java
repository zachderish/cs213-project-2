import java.util.Scanner;

public class TransactionManager {

    final String[] COMMANDS = {"O", "C", "D", "W", "P", "PI", "UB", "Q"}; //list of commands
    public static final int NOT_FOUND = -1;

    /**
     * Check to see if command inputted is a valid executable command
     * @param input String which will represent the command we will be testing for validity
     * @return will return true (boolean) if input is listed in command array, otherwise false
     */
    private boolean validCommand(String input) {
        for (String command : COMMANDS) {
            if(input.equals(command)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given a command extracted from command line input, method will verify and run the specified command
     * @param calendar EventCalendar the calendar we will be adding an Event to
     * @param input an array of strings representing a single line from system.in
     * @return will return string indicating error or which command was successfully executed
     */
    private String runCommand(String[] input, AccountDatabase database) {
        String returnMessage = "";

        String command = input[0];
        if (command.equals("Q")) {
            return "QUIT";
        }
        /*if (command.equals("A")) {
            returnMessage = runAdd(calendar, input);
        }
        if (calendar.getNumEvents() == 0 ){
            returnMessage = "Event calendar is empty!";
        }
        else {
            if (command.equals("P")) {
                calendar.print();
            }
            if (command.equals("PD")) {
                calendar.printByDepartment();
            }
            if (command.equals("PC")) {
                calendar.printByCampus();
            }
            if (command.equals("PE")) {
                calendar.printByDate();
            }

            if (command.equals("R")) {
                returnMessage = removeEvent(calendar, input);
            }
        }*/
        return returnMessage;
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Transaction Manager is running.\n");

        AccountDatabase database = new AccountDatabase();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineItemized = line.split("\\s+");
            if (validCommand(lineItemized[0])) {
                String message = runCommand(lineItemized, database);
                if (message.equals("QUIT")) {
                    System.out.println("Transactions Manager is terminated.");
                    return;
                }
                System.out.println(message);
            }
            else if (lineItemized[0].isEmpty()) {
                System.out.print("");
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }

    // testbed main to be DELETED later
    public static void main(String[] args) {

    }

}
