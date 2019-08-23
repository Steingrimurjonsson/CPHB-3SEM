/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author stein
 */
public class CustomerDTO {
    private long customerID;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO(BankCustomer bcust) {
        this.customerID = bcust.getId();
        this.fullName = bcust.getFirstName() + " " + bcust.getLastName();
        this.accountNumber = bcust.getAccountNumber();
        this.balance = bcust.getBalance();
    }

    public long getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    
}
