package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.Address;

public interface AddressService {

	Address getAddressById(long id);

	void addAddress(Address address);

	void updateAddress(Address address);

	void deleteAddress(Address address);

	List<Address> getAllAddress();
}
