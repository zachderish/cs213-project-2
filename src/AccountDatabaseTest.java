import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDatabaseTest {

    @Test
    void CloseNonExistentAccount(){
        AccountDatabase database = new AccountDatabase();
        Profile profile1 = new Profile("Peter", "Parker", "12/1/2005");
        double balance1 = 2000.00;
        Checking checking1 = new Checking(profile1,balance1);
        database.open(checking1);

        Profile profile2 = new Profile("Jimmy", "Fallon", "11/5/2006");
        double balance2 = 2200.00;
        Checking checking2 = new Checking(profile2,balance2);

        assertFalse(database.close(checking2));

    }

    @Test
    void CloseExistingAccount(){
        AccountDatabase database = new AccountDatabase();
        Profile profile1 = new Profile("Vanilla", "Ice", "5/5/2004");
        double balance1 = 2000.00;
        Checking checking1 = new Checking(profile1,balance1);
        database.open(checking1);

        assertTrue(database.close(checking1));

    }

    @Test
    void CloseSavingsAccount(){
        AccountDatabase database = new AccountDatabase();
        Profile profile1 = new Profile("Sam", "Iam", "7/31/2003");
        double balance1 = 2000.00;
        Savings savings1 = new Savings(profile1,balance1, true);
        database.open(savings1);

        assertTrue(database.close(savings1));

    }

    @Test
    void CloseMoneyMarketAccount(){
        AccountDatabase database = new AccountDatabase();
        Profile profile1 = new Profile("Peter", "Parker", "12/1/2005");
        double balance1 = 2000.00;
        int withdrawals =0;
        MoneyMarket moneyAcc = new MoneyMarket(profile1,balance1, withdrawals);
        database.open(moneyAcc);

        Profile profile2 = new Profile("Jimmy", "Fallon", "11/5/2006");
        double balance2 = 2200.00;
        Checking checking2 = new Checking(profile2,balance2);
        database.open(checking2);

        assertTrue(database.close(moneyAcc));

    }

    @Test
    void CloseSingleAccountFromMultipleAccountOwner(){
        AccountDatabase database = new AccountDatabase();
        Profile profile1 = new Profile("Spongebob", "Squarepants", "3/5/2000");
        double balance1 = 2000.00;
        Savings savings1 = new Savings(profile1,balance1, true);
        database.open(savings1);
        double balance2 = 3000.00;
        Checking checking1 = new Checking(profile1, balance2);
        database.open(checking1);
        database.close(savings1);
        assertTrue(database.contains(checking1));
    }

    @Test
    void confirmClosedAccountIsRemoved(){
        AccountDatabase database = new AccountDatabase();
        Profile profile1 = new Profile("Spongebob", "Squarepants", "3/5/2000");
        double balance1 = 2000.00;
        Savings savings1 = new Savings(profile1,balance1, true);
        database.open(savings1);

        database.close(savings1);

        assertFalse(database.close(savings1));
    }

}