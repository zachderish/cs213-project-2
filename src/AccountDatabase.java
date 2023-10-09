public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    public static final int NOT_FOUND = -1;

    /**
     * AccountDatabase Constructor
     */
    public AccountDatabase(){
        this.accounts = new Account[4];
        int initialSize = 0;
        this.numAcct = initialSize;
    }

    /**
     * Search for an account in the array
     * @param account Account object
     * @return integer representing index of desired element in array
     */
    private int find(Account account) {
        for (int i = 0; i < this.numAcct; i++) {
            if(this.accounts[i].equals(account)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    private int findByElements(String fname, String lname, Date dob, String accountType){
        for(int i = 0; i < numAcct; i++){
            String firstName = accounts[i].getHolder().getFname();
            String lastName = accounts[i].getHolder().getLname();
            Date dateOfBirth = accounts[i].getHolder().getDob();
            String accType = accounts[i].accountType();
            if(firstName.equals(fname) && lastName.equals(lname)){ //checking to see if account exists with name
                if(dateOfBirth.compareTo(dob) == 0 && accType.equals(accountType)){ //if it exists then we look for dob and account type
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }


    /**
     * Grow the array capacity by 4
     */
    private void grow(){
        int n = this.numAcct;
        Account[] temp = new Account[n + 4];
        for (int i = 0; i < n; i++) {
            temp[i] = this.accounts[i];
        }

        this.accounts = new Account[n+4];
        this.accounts = temp;
    }

    /**
    *  Checks to see if given account is en element of accounts array
    * @param account object we are searching for
    * @return boolean indicating true if it exists in array false otherwise
    */
    public boolean contains(Account account){//overload if necessary
        if (this.find(account) == NOT_FOUND) {
            return false;
        }
        return true;
    }

    /**
     * Checker to see if there is space availability in accounts array
     * @return boolean indicating true if there is space false otherwise
     */
    private boolean hasSpace(){
        if(this.accounts[accounts.length-1] != null){
            return false;
        }
        return true;
    }

    /**
     * Adds new account to accounts array
     * @param account object we are adding
     * @return boolean indicating true if it was successfully added, false otherwise
     */
    public boolean open(Account account){
        if (this.contains(account)) {
            return false;
        }
        if (!this.hasSpace()) {
            this.grow();
        }
        for (int i = 0; i < this.accounts.length; i++) {
            if(this.accounts[i] == null){
                this.accounts[i] = account;
                this.numAcct++;
                return true;
            }
        }
        return false;
    }

    /**
     * Close given account
     * @param account object we will be removing
     * @return boolean indicating true if account has been successfully removed, false otherwise
     */
    public boolean close(Account account){
        int index = this.find(account);
        if(index != NOT_FOUND){
            for (int i = index; i < this.accounts.length - 1; i++) {
                this.accounts[i] = this.accounts[i + 1];
            }
            this.numAcct--;
            return true;
        }

        return false;
    }



    /**
     * Method to withdraw money from specified account.
     * @param account object we will be withdrawing money from.
     * @return boolean indicating true if withdrawal has been successful false otherwise.
     */
    public boolean withdraw(Account account){ //the account input will be a temp account created by system.in
        String firstName = account.getHolder().getFname();
        String lastName = account.getHolder().getLname();
        Date dateOfBirth = account.getHolder().getDob();
        String accountType = account.accountType();

        double newBalance = account.getBalance();
        int index = findByElements(firstName, lastName, dateOfBirth, accountType); //we extract the data we need to search if account exists
        if(index != NOT_FOUND){ //as long as it exists
            double currentBalance = accounts[index].getBalance(); //get the current balance of the real account
            int balanceLimit = 0;
            if(newBalance >= balanceLimit){ //compare amounts and make sure theres enough money to withdraw
                accounts[index].setBalance(newBalance); //set the balance of the real account
                return true;
            }
        }
        return false;//if it dosent exist in the array or theres insufficient funds return false
    } //false if insufficient fund

    /**
     * Method to deposit money from specified account.
     * @param account the account object that is receiving the deposit.
     */
    public void deposit(Account account){
        double newBalance = account.getBalance();
        int index = find(account); //we extract the data we need to search if account exists
        if(index != NOT_FOUND) { //as long as it exists
            accounts[index].setBalance(newBalance); //set the balance of the real account
        }
    }

    // will need to add polymorphic toString methods
    public void printSorted(){
        for (int i = 0; i < this.numAcct; i++) {
            System.out.println(this.accounts[i].toString());
        }
    }
    /*
    public void printFeesAndInterests(){} //calculate interests/fees
    public void printUpdatedBalances(){} //apply the interests/fees*/

    // Testbed main, will be DELETED later
    public static void main(String[] args) {
        // testing open
        AccountDatabase accounts1 = new AccountDatabase();

        Date dob1 = new Date(2002, 1, 22);
        Profile profile1 = new Profile("Zach", "D", dob1);
        Checking checkingAccount1 = new Checking(profile1, 1000);
        accounts1.open(checkingAccount1);

        Date dob2 = new Date(2004, 10, 9);
        Profile profile2 = new Profile("Tony", "D", dob2);
        Checking checkingAccount2 = new Checking(profile2, 4000);
        accounts1.open(checkingAccount2);

        accounts1.printSorted();
        System.out.println("End of test1");

        // testing close
        accounts1.close(checkingAccount1);

        accounts1.printSorted();
        System.out.println("End of test2");

        // testing withdrawal
        Date dob3 = new Date(2004, 10, 9);
        Profile profile3 = new Profile("Tony", "D", dob3);
        Checking checkingAccount3 = new Checking(profile3, 1000);
        boolean withdrawal = accounts1.withdraw(checkingAccount3);
        System.out.println(withdrawal);
        System.out.println(checkingAccount2.getBalance());

        // testing deposit
        Date dob4 = new Date(2004, 10, 9);
        Profile profile4 = new Profile("Tony", "D", dob4);
        Checking checkingAccount4 = new Checking(profile4, 5000);
        accounts1.deposit(checkingAccount4);
        System.out.println(checkingAccount2.getBalance());
    }

}
