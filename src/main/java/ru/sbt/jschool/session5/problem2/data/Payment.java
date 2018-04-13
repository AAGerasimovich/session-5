package ru.sbt.jschool.session5.problem2.data;

import ru.sbt.jschool.session5.problem1.Column;
import ru.sbt.jschool.session5.problem1.PrimaryKey;
import ru.sbt.jschool.session5.problem1.Table;

import java.util.*;

/**
 */
@Table(name = "PAYMENTS")
public class Payment {
    @PrimaryKey
    private long operationID;

    @Column
    private long payerID;

    @Column
    private long payerAccountID;

    @Column
    private long recipientID;

    private Map<String,String> map;
    private Map<Double,Map<String,String>> map2;
    Set<Integer> set;
    Set<Set<String>> setStr;
    Set<String> tmp;

    @Column
    private long recipientAccountID;

    private float amount;

    public Payment(long operationID, long payerID, long payerAccountID, long recipientID, long recipientAccountID,
                   float amount) {
        this.operationID = operationID;
        this.payerID = payerID;
        this.payerAccountID = payerAccountID;
        this.recipientID = recipientID;
        this.recipientAccountID = recipientAccountID;
        this.amount = amount;
        map = new HashMap<>();
        map.put("dfa","efegag");
        map.put("dfdfa","efegasfdg");
        map.put("ddsffa","efsdfegag");
        map.put("dfsfa","efegasdfg");
        set = new HashSet<>();
        set.add(345);
        set.add(3345);
        set.add(3425);
        set.add(3455);
        map2 = new HashMap<>();
        map2.put(123.0, map);
        map2.put(1223.0, map);
        setStr = new HashSet<>();
        tmp = new HashSet<>();
        tmp.add("dgadg");
        tmp.add("dgaeeedg");
        setStr.add(tmp);

    }

    public long getPayerID() {
        return payerID;
    }

    public void setPayerID(long payerID) {
        this.payerID = payerID;
    }

    public long getPayerAccountID() {
        return payerAccountID;
    }

    public void setPayerAccountID(long payerAccountID) {
        this.payerAccountID = payerAccountID;
    }

    public long getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(long recipientID) {
        this.recipientID = recipientID;
    }

    public long getRecipientAccountID() {
        return recipientAccountID;
    }

    public void setRecipientAccountID(long recipientAccountID) {
        this.recipientAccountID = recipientAccountID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getOperationID() {
        return operationID;
    }

    public void setOperationID(long operationID) {
        this.operationID = operationID;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Payment payment = (Payment)o;
        return operationID == payment.operationID &&
            payerID == payment.payerID &&
            payerAccountID == payment.payerAccountID &&
            recipientID == payment.recipientID &&
            recipientAccountID == payment.recipientAccountID &&
            Float.compare(payment.amount, amount) == 0;
    }

    @Override public int hashCode() {

        return Objects.hash(operationID, payerID, payerAccountID, recipientID, recipientAccountID, amount);
    }
}
