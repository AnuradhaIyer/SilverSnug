package edu.sjsu.seekers.silversnug.service;
import edu.sjsu.seekers.silversnug.dao.AddressBookDao;
import edu.sjsu.seekers.silversnug.model.AddressBook;
import edu.sjsu.seekers.silversnug.request.AddressBookRequest;
import edu.sjsu.seekers.silversnug.response.AddressBookResponse;
import edu.sjsu.seekers.silversnug.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import static edu.sjsu.seekers.silversnug.util.Constants.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.UUID;

@Service
public class AddressBookService {

    @Autowired
    AddressBookDao addressBookDao;

    public void authenticate(String username) {
        System.out.println("In service: " + username);
        addressBookDao.authenticate(username);
    }

    public AddressBookResponse saveAddress(AddressBookRequest request) {

        String addressUUID = UUID.randomUUID().toString();
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(request.getUserId());
        addressBook.setAddressId(addressUUID);
        addressBook.setAddressName(request.getAddressName());
        addressBook.setLatitude(request.getLatitude());
        addressBook.setLongitude(request.getLongitude());
        addressBook.setAddress(request.getAddress());
        addressBookDao.save(addressBook);

        AddressBookResponse response = new AddressBookResponse();
        List<AddressBook> addressBookList = new ArrayList<>();
        addressBookList.add(addressBook);
        response.setAddressBooks(addressBookList);
        response.setMessage(SUCCESS_GET);
        response.setStatus(HttpStatus.OK.toString());

        return response;
    }

    public AddressBookResponse getAddressByUserId(String userId) {
        AddressBookResponse response = new AddressBookResponse();

        AddressBookResponse addressBookResponse = addressBookDao.getAddressByUserId(userId);
        if (null != addressBookResponse) {
            response = addressBookResponse;
        } else {
            response.setMessage(UNSUCCESSFUL_GET_ADDRESS);
        }

        return response;
    }

    public AddressBookResponse get1AddressByUserId(String userId,String addressName) {
        AddressBookResponse response = new AddressBookResponse();

        AddressBook addressBook = addressBookDao.getAddressByAddressName(userId,addressName);
        if (null != addressBook) {
            response.setAddressBooks(Collections.singletonList(addressBook));
        } else {
            response.setMessage("No address found for this User");
        }

        return response;
    }

    public AddressBookResponse editAddress(AddressBookRequest request) {

        AddressBook addressBook = new AddressBook();

        addressBook.setUserId(request.getUserId());
        addressBook.setAddressId(request.getAddressId());
        addressBook.setAddressName(request.getAddressName());
        addressBook.setLatitude(request.getLatitude());
        addressBook.setLongitude(request.getLongitude());
        addressBook.setAddress(request.getAddress());
        if(addressBook.getAddressId()!=null) {
            addressBookDao.editAddress(addressBook);
        }

        AddressBookResponse response = new AddressBookResponse();
        List<AddressBook> addressBookList = new ArrayList<>();
        addressBookList.add(addressBook);
        response.setAddressBooks(addressBookList);
        response.setMessage(SUCCESS_GET);
        response.setStatus(HttpStatus.OK.toString());

        return response;
    }

    public GenericResponse removeAddress(String userId,String addressName) {

        GenericResponse response = new GenericResponse();
        String addressID = addressBookDao.getAddressIdByAddressName(userId,addressName);
        if(addressID!=null) {
            addressBookDao.removeAddress(addressID);
            response.setMessage(SUCCESS_GET);
            response.setStatus(HttpStatus.OK.toString());
        }

        else {
            response.setMessage(UNSUCCESSFUL_GET_ADDRESS);
        }

        return response;
    }

//    public AddressBookDao getAddressBookDao() {
//        return addressBookDao;
//    }
//
//    public void setAddressBookDao(AddressBookDao addressBookDao) {
//        this.addressBookDao = addressBookDao;
//    }
}
