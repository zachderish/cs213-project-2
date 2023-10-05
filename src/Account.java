public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    /**
     * Account object constructor.
     * @param holder Profile object of account holder.
     * @param balance integer for account.
     */
    public Account(Profile holder, double balance){
        this.holder = holder;
        this.balance = balance;
    }

    /**
     * compareTo method for Account objects by account type and holder information.
     * @param account the object to be compared.
     * @return 1 if this.account greater than account, -1 if this.account less than account, and 0 otherwise.
     */
    public int compareTo(Account account){
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
}
