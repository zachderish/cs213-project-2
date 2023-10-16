package manager;

/**
 * Define abstract manager.Account class
 * @author Kenrick Eagar, Zachary Derish
 */

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();
    public abstract String accountType();

    /**
     * manager.Account object constructor.
     * @param holder manager.Profile object of account holder.
     * @param balance integer for account.
     */
    public Account(Profile holder, double balance){
        this.holder = holder;
        this.balance = balance;
    }

    /**
     * Getter method to retrieve account holder profile.
     * @return The profile/holder of the account.
     */
    public Profile getHolder(){
        return this.holder;
    }

    /**
     * Getter method to retrieve current account balance.
     * @return the balance of an account.
     */
    public double getBalance(){
        return this.balance;
    }

    /**
     * Setter method to set account balance.
     * @param newBalance the double representing the monetary balance value.
     */
    public void setBalance(double newBalance){
        this.balance = newBalance;
    }

    /**
     * compareTo method for manager.Account objects by account type and holder information.
     * @param account the object to be compared.
     * @return 1 if this.account greater than account, -1 if this.account less than account, and 0 otherwise.
     */
    @Override
    public int compareTo(Account account){
        if (this.accountType().compareTo(account.accountType()) > 0) {
            return 1;
        }
        if (this.accountType().compareTo(account.accountType()) < 0) {
            return -1;
        }
        if (this.holder.compareTo(account.holder) > 0) {
            return 1;
        }
        if (this.holder.compareTo(account.holder) < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }

    /**
     * Generic equals method to compare Accounts and see if they are equivalent or not
     * @param Account object we will be comparing
     * @return Boolean true if Accounts are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Account){
            Account account = (Account)obj;
            if(this.compareTo(account) == 0) {
                return true;
            }
        }
        return false;
    }

    //test for equals methods
    public static void main(String[] args){
        Date d1 = new Date("1/22/2002");
        Profile p1 = new Profile("Sam", "Sullet", d1);
        Account a1 = new CollegeChecking(p1, 200.00, Campus.CAMDEN);

        Date d2 = new Date("1/22/2002");
        Profile p2 = new Profile("Sam", "Sullet", d2);
        Account a2 = new Checking(p2, 200.00);

        System.out.println(a1.compareTo(a2));
    }

    /**
     * Prints the accounts profile information and balance
     * @return String stating manager.Account objects profile and balance information
     */
    @Override
    public String toString() {
        String profileInfo = this.holder.toString() + "::";
        String balance = "Balance $" + this.getBalance();
        return profileInfo + balance;
    }

}
