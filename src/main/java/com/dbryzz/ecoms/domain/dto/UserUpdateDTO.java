package com.dbryzz.ecoms.domain.dto;

import com.dbryzz.ecoms.domain.model.Address;
import com.dbryzz.ecoms.domain.model.PaymentAccount;

public class UserUpdateDTO {


    private String fullname;
    private PaymentAccount paymentAccount;
    private Address address;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO( String fullname, PaymentAccount paymentAccount, Address address) {

        this.fullname = fullname;
        this.paymentAccount = paymentAccount;
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDTO{" +

                ", fullname='" + fullname + '\'' +
                ", paymentAccount=" + paymentAccount +
                ", address=" + address +
                '}';
    }



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
