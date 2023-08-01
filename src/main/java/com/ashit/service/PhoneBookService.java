package com.ashit.service;

import com.ashit.request.Subscriber;
import com.ashit.response.Contact;

import java.util.List;

public interface PhoneBookService {
    public boolean addSubscriber(Subscriber subscriber);
    public Contact getSubscriber(String name);

   //public boolean updateContact(Subscriber subscriber);

    boolean updateContact(String name, Subscriber subscriber);

    public boolean deleteContact(String name);

    public List<Contact> getAllSubscriber();
}
