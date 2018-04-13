package ru.sbt.jschool.session5.problem2.data;

import ru.sbt.jschool.session5.problem1.Column;
import ru.sbt.jschool.session5.problem1.Currency;
import ru.sbt.jschool.session5.problem1.PrimaryKey;
import ru.sbt.jschool.session5.problem1.Table;

import java.util.Date;
import java.util.Objects;

/**
 */
@Table(name = "BANK_ACCOUNTS")
public class Account {
    @Column
    private long clientID;

    public Payment pay;

    public long sfca = 1242141;

    @PrimaryKey
    private long accountID;

    @PrimaryKey
    private long bankID;

    @Column(name = "CURR")


    int[] arr;
    String[] strArr;
    Date date;

    @Column
    private float balance;



    public Account(long clientID, long accountID, long bankID, Currency currency, float balance) {
        this.clientID = clientID;
        this.accountID = accountID;
        this.bankID = bankID;

        this.balance = balance;
        pay = new Payment(345345,43636,43636,463436,46346,4646436);
        arr = new int[5];
        arr[3] = 0;
        arr[2] = 234;
        strArr = new String[4];
        strArr[0] = "segsegesg";
        strArr[2] = "segsedfsdgesg";
        date = new Date();


    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public long getBankID() {
        return bankID;
    }

    public void setBankID(long bankID) {
        this.bankID = bankID;
    }



    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }


}

