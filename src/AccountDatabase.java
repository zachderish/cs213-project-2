public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    public static final int NOT_FOUND = -1;
    /**
     * AccountDatabase Constructor
     * @param array of account objects
     * @param int number of accounts in array
     */
    public AccountDatabase(Account[] accounts, int numAcct){
        this.accounts = accounts;
        this.numAcct = numAcct;

    }
    /**
     * Search for an account in the array
     * @param Account object
     * @return integer representing index of desired element in array
     */
    private int find(Account account) {
        for(int i = 0; i<numAcct; i++){
            if(this.accounts[i].equals(account)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Grow the array capacity by 4
     *
     */
    private void grow(){
        int n = numAcct;
        Account[] temp = new Account[n + 4];
        for(int i =0; i<n; i++){
            temp[i] = this.accounts[i];
        }

        this.accounts = new Account[n+4];
        this.accounts = temp;
    }
/**
 * Checks to see if given account is en element of accounts array
 * @param account object we are searching for
 * @return boolean indicating true if it exists in array false otherwise
 */
    public boolean contains(Account account){//overload if necessary
        for(int i =0; i<numAcct; i++){
            if(!this.accounts[i].equals(account)){
                return false;
            }
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
    public boolean open(Account account){//add a new account
        if(!hasSpace()){
            grow();
        }
       for(int i =0; i<this.account.length; i++){
           if(accounts[i] == null){
               accounts[i] = account;
               numAcct++;
               return true;
           }
       }
return false;//never really a situation where we cant add an event
    }

    /**
     * Close given account
     * @param account object we will be removing
     * @return boolean indicating true if account has been successfully removed, false otherwise
     */
    public boolean close(Account account){//remove the given account
    int index = find(account);
    if(index != NOT_FOUND){
        accounts[index] = null;
        numAcct--;
        return true;
    }

    return false;
    }

    /**
     * Method to withdraw money from specified account
     * @param account object we will be withdrawing money from
     * @return boolean indicating true if withdrawal has been successful false otherwise
     */
    public boolean withdraw(Account account){

    } //false if insufficient fund
    public void deposit(Account account){}
    public void printSorted(){} //sort by account type and profile
    public void printFeesAndInterests(){} //calculate interests/fees
    public void printUpdatedBalances(){} //apply the interests/fees*/
}
