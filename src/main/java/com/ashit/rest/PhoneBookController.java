package com.ashit.rest;

import com.ashit.request.Subscriber;
import com.ashit.response.Contact;
import com.ashit.service.PhoneBookService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneBookController {

    @Autowired
    private PhoneBookService phoneBookService;

    @PostMapping(value = "/addcontact", consumes = {"application/xml", "application/json"})
    public ResponseEntity<String> addContact(@RequestBody Subscriber subscriber){
        boolean b = phoneBookService.addSubscriber(subscriber);
        if (b) {
            return new ResponseEntity<>("Contact Added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Contact Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/getcontact/{name}", produces = {"application/xml", "application/json"})
    public ResponseEntity<?> getContact(@PathVariable String name){
        Contact contact = phoneBookService.getSubscriber(name);
        if(!ObjectUtils.isEmpty(contact)){
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Contact Found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/getallcontact", produces = {"application/xml", "application/json"})
    public ResponseEntity<?> getAllContact(){
        List<Contact> contact = phoneBookService.getAllSubscriber();
        if(!ObjectUtils.isEmpty(contact)){
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Contacts Found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/updatecontact/{name}")
    public ResponseEntity<String> updateContact(@PathVariable String name, @RequestBody Subscriber subscriber)
    {

        boolean updateContact = phoneBookService.updateContact(name,subscriber);
        System.out.println(subscriber);
        if (updateContact) {
            System.out.println("Updated");
           return new ResponseEntity<>("Contact Update",HttpStatus.OK);
        }
        return new ResponseEntity<>("Contact Not Updated", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/deletecontact/{name}")
    public ResponseEntity<String> deleteContact(@PathVariable String name){
        boolean deleteContact = phoneBookService.deleteContact(name);
        if (deleteContact) {
            return new ResponseEntity<>("Contact Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Contact Not Deleted", HttpStatus.BAD_REQUEST);
    }
}
