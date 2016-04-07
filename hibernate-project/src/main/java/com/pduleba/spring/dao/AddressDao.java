package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.Address;

public interface AddressDao {

    void addAddress(Address address);
    
    void deleteAddress(Address address);
     
    List<Address> getAllAddresses();
 
    Address getAddressById(long id);
    
    void updateAddress(Address address);
    
    void deleteAllAddresses();
    
}
