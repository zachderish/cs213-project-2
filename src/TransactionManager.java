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

    private Campus getCampus(int code) {
        if (code == 0) {
            return Campus.NEW_BRUNSWICK;
        }
        if (code == 1) {
            return Campus.NEWARK;
        }
        else {
            return Campus.CAMDEN;
        }
    }

    private Account makeAccount(String[] input) {
        // make profile
        String fname = input[2];
        String lname = input[3];
        Date dob = new Date(input[4]);
        Profile profile = new Profile(fname, lname, dob);
        double initialDeposit = Double.parseDouble(input[5]);
        String accountType = input[1];

        if (accountType.equals("C")) {
            Checking checking = new Checking(profile, initialDeposit);
            return checking;
        }
        if (accountType.equals("CC")) {
            int code = Integer.parseInt(input[6]);
            Campus campus = getCampus(code);
            CollegeChecking collegeChecking = new CollegeChecking(profile, initialDeposit, campus);
            return collegeChecking;
        }
        if (accountType.equals("S")) {
            int loyalCode = Integer.parseInt(input[6]);
            boolean isLoyal = false;
            if (loyalCode == 1) {
                isLoyal = true;
            }
            Savings savings = new Savings(profile, initialDeposit, isLoyal);
            return savings;
        }
        else {
            int initialWithdrawal = 0;
            MoneyMarket moneyMarket = new MoneyMarket(profile, initialDeposit, initialWithdrawal);
            return moneyMarket;
        }
    }


    private int getInputLength(String[] input) {
        String accountType = input[1];
        if (accountType.equals("C") || accountType.equals("MM")) {
            int C_MM_LENGTH = 6;
            return C_MM_LENGTH;
        }
        else {
            int CC_S_LENGTH = 7;
            return CC_S_LENGTH;
        }
    }

    // need to handle future dob and invalid date exceptions
    private String handleOpenExceptions(String[] input, AccountDatabase database) {
        if (input.length < getInputLength(input)) {
            return "Missing data for opening an account.";
        }
        try {
            Double.parseDouble(input[5]);
        }
        catch (NumberFormatException ex) {
            return "Not a valid amount";
        }
        int depositMin = 0;
        if (Double.parseDouble(input[5]) <= depositMin) {
            return "Initial deposit cannot be 0 or negative.";
        }
        int MMMin = 2000;
        if (input[1].equals("MM") && Double.parseDouble(input[5]) < MMMin) {
            return "Minimum of $2000 to open a Money Market account.";
        }
        if (input[1].equals("CC") && (Integer.parseInt(input[6]) < 0 || Integer.parseInt(input[6]) > 2)) {
            return "Invalid campus code.";
        }

        else {
            return "NO EX";
        }
    }

    private String runOpen(String[] input, AccountDatabase database) {
        String openException = handleOpenExceptions(input, database);
        if (!openException.equals("NO EX")) {
            return openException;
        }

        Account account = makeAccount(input);
        String name = input[2] + " " + input[3];
        String dob = input[4];
        String accountType = input[1];
        String returnString = name + " " + dob + " " + "(" + accountType + ")";
        if (!account.getHolder().getDob().isValid()) {
            return "DOB invalid: " + dob  + " not a valid calendar date!";
        }
        if (account.getHolder().getDob().futureOrToday()) {
            return "DOB invalid: " + dob  + " cannot be today or a future day.";
        }
        if (account.getHolder().getDob().underSixteen()) {
            return "DOB invalid: " + dob  + " under 16.";
        }
        if (account.getHolder().getDob().overTwentyFour() && accountType.equals("CC")) {
            return "DOB invalid: " + dob  + " over 24.";
        }
        if (database.containsProfile(account)) {
            return returnString + " is already in the database.";
        }
        boolean opened = database.open(account);
        if (opened) {
            return returnString + " opened.";
        }
        else {
            return returnString + " is already in the database.";
        }
    }

    private boolean isNumber(String number){
        try {
            double test = Double.parseDouble(number);
        } catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    private String runWithdraw(String[] input, AccountDatabase database){
        Account shellAccount = makeAccount(input);
        String name = input[2] + " " + input[3];
        String dob = input[4];
        String accountType = input[1];
        String returnString = name + " " + dob + " " + "(" + accountType + ")";

        if(!isNumber(input[5])){
            return "Not a valid amount.";
        }
        double withdrawalAmount = Double.parseDouble(input[5]);

        if(!database.contains(shellAccount)){
            return returnString + " " + "is not in the database.";
        }
        double currentBalance = database.getAccountBalance(shellAccount);
        if(withdrawalAmount > currentBalance){
            return returnString + " " + "Withdraw - insufficient fund.";
        }
        if(withdrawalAmount <= 0){
            return "Withdraw - amount cannot be 0 or negative.";
        }

        return returnString + " " + "Withdraw - balance updated.";
    }

    private Account makeClosingAccount(String[] input) {
        // make profile
        String fname = input[2];
        String lname = input[3];
        Date dob = new Date(input[4]);
        Profile profile = new Profile(fname, lname, dob);
        double initialDeposit = 0;
        String accountType = input[1];

        if (accountType.equals("C")) {
            Checking checking = new Checking(profile, initialDeposit);
            return checking;
        }
        if (accountType.equals("CC")) {
            int code = 0;
            Campus campus = getCampus(code);
            CollegeChecking collegeChecking = new CollegeChecking(profile, initialDeposit, campus);
            return collegeChecking;
        }
        if (accountType.equals("S")) {
            int loyalCode = 0;
            boolean isLoyal = false;
            if (loyalCode == 1) {
                isLoyal = true;
            }
            Savings savings = new Savings(profile, initialDeposit, isLoyal);
            return savings;
        }
        else {
            int initialWithdrawal = 0;
            MoneyMarket moneyMarket = new MoneyMarket(profile, initialDeposit, initialWithdrawal);
            return moneyMarket;
        }
    }

    private String runClose(String[] input, AccountDatabase database) {
        int closingLength = 5;
        if (input.length < closingLength) {
            return "Missing data for closing an account.";
        }
        Account account = makeClosingAccount(input);
        Profile holder = account.getHolder();
        String fname = holder.getFname();
        String lname = holder.getLname();
        String dob = holder.getDob().toString();
        String accountType = input[1];
        if (holder.getDob().futureOrToday()) {
            return "DOB invalid: " + dob + " cannot be today or a future day.";
        }
        if(database.close(account)) {
               return fname + " " + lname + " " + dob + "(" + accountType + ") has been closed.";
        }
        else {
            return fname + " " + lname + " " + dob + "(" + accountType + ") is not in the database.";
        }
    }

    private String runDeposit(String[] input, AccountDatabase database) {
        if (!isNumber(input[5])) {
            return "Not a valid amount.";
        }
        double depositAmount = Double.parseDouble(input[5]);
        if (depositAmount <= 0) {
            return "Deposit - amount cannot be 0 or negative.";
        }
        Account account = makeClosingAccount(input);

        Profile holder = account.getHolder();
        String fname = holder.getFname();
        String lname = holder.getLname();
        String dob = holder.getDob().toString();
        String accountType = input[1];
        String accountString = fname + " " + lname + " " + dob + "(" + accountType + ") ";

        if (!database.contains(account)) {
            return accountString + "is not in the database.";
        }
        double oldBalance = database.getAccountBalance(account);
        double newBalance = oldBalance + depositAmount;
        account.setBalance(newBalance);
        database.deposit(account);


        return accountString + "Deposit - balance updated.";
    }


    /**
     * Given a command extracted from command line input, method will verify and run the specified command
     * @param input an array of strings representing a single line from system.in
     * @param database AccountDatabase to handle transactions from
     * @return will return string indicating error or which command was successfully executed
     */
    private String runCommand(String[] input, AccountDatabase database) {
        String returnMessage = "";

        String command = input[0];
        if (command.equals("Q")) {
            return "QUIT";
        }
        if (command.equals("O")) {
            return runOpen(input, database);
        }
        if (database.getNumAcct() == 0) {
            returnMessage = "Account Database is empty!";
        } else {
            if (command.equals("P")) {
                database.printSorted();
            }
            if (command.equals("W")) {
                returnMessage = runWithdraw(input, database);
            }
            if (command.equals("C")) {
                returnMessage = runClose(input, database);
            }
            if (command.equals("D")) {
                returnMessage = runDeposit(input, database);
            }

            /*if (command.equals("R")) {
                returnMessage = removeEvent(calendar, input);*/
            }
        return returnMessage;
    }


    public void run () {
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
            } else if (lineItemized[0].isEmpty()) {
                System.out.print("");
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}