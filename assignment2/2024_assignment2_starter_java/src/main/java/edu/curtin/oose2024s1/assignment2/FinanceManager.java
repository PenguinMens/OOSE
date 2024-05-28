package edu.curtin.oose2024s1.assignment2;

public class FinanceManager {
    private int totalRevenue = 0;
    private float balance;
    private int workers;
    public FinanceManager()
    {
        this.balance = 15000.0f;// default balance
        workers = 1;
    }

    public void payWorker()
    {
        this.balance -= 1000;
    }
    public void addMoney(float moneyIn)
    {
        this.balance += moneyIn;
        this.totalRevenue += moneyIn;
    }

    public void deductMoney(float moneyIn)
    {
        this.balance -= moneyIn;
    }

    public float getBalance()
    {
        return balance;
    }

    public int getTotalRevenue()
    {
        return totalRevenue;
    }

    public String printStats()
    {
        return "Total Revenue: " + totalRevenue + "\nBalance: " + balance;
        // System.out.println("Total Revenue: " + totalRevenue);
        // System.out.println("Balance: " + balance);
   
    }
}
