package com.ashit.service;

import com.ashit.repository.PhoneBookRepository;
import com.ashit.request.Subscriber;
import com.ashit.response.Contact;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    Map<Integer, Contact> bookMap = new HashMap<Integer, Contact>();
    int count = 1;

    @Override
    public boolean addSubscriber(Subscriber subscriber) {
        System.out.println(subscriber);
        if (!ObjectUtils.isEmpty(subscriber)) {
            Subscriber save = phoneBookRepository.save(subscriber);
            System.out.println(save);
            return true;
        }
        return false;
    }
    @Override
    public Contact getSubscriber(String name) {
        Subscriber subscriber = phoneBookRepository.findByName(name);
        if (!ObjectUtils.isEmpty(subscriber)) {
            Contact contact = new Contact();
            BeanUtils.copyProperties(subscriber,
                    contact);
            contact.setSerial(subscriber.getName().charAt(0));
            System.out.println(contact);
            return contact;
        }
        return null;
    }

    @Override
    public boolean updateContact(String name, Subscriber subscriber) {

        Subscriber byName = phoneBookRepository.findByName(name);
        System.out.println(byName);
        if (!ObjectUtils.isEmpty(byName)) {
            phoneBookRepository.save(subscriber);
            return true;
        }
//        Subscriber save = phoneBookRepository.save(subscriber);
//        System.out.println(save );
//        if (!ObjectUtils.isEmpty(save)) {
//            return true;
//        }

        return false;
    }

    @Override
    public boolean deleteContact(String name) {
        Integer deleteByName = phoneBookRepository.deleteByName(name);
            if (deleteByName == 1) {
                return true;
        }
        return false;
    }

    @Override
    public List<Contact> getAllSubscriber() {
        List<Subscriber> subscriberList = phoneBookRepository.findAll();
        System.out.println(subscriberList);
        Contact contact = null;
        List<Contact> contactList = new ArrayList<>();
        for (Subscriber subscriber:
             subscriberList) {
            contact = new Contact();
          //  BeanUtils.copyProperties(subscriber,contact);
            contact.setName(subscriber.getName());
            contact.setPhoneNumber(subscriber.getPhoneNumber());
            contact.setSerial(subscriber.getName().charAt(0));
            System.out.println(contact);
            boolean add = contactList.add(contact);
            System.out.println(contactList);
            contact = null;
        }
        System.out.println(contactList);
        return contactList;
    }
}
