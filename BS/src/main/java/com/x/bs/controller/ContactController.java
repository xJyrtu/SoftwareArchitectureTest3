package com.x.bs.controller;


import com.x.bs.common.Result;
import com.x.bs.entity.Contact;
import com.x.bs.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService = new ContactService();

    @GetMapping
    public Result getAllContacts() throws IOException {
        List<Contact> allContacts = contactService.getAllContacts();
        return Result.success(allContacts);
    }

    @PostMapping
    public Result addContact(@RequestBody Contact contact) throws IOException {
        contactService.addContact(contact);
        return Result.success();
    }

    @PutMapping
    public Result updateContact(@RequestParam String oldName, @RequestBody Contact newContact) throws IOException {
        Boolean b= contactService.updateContact(new Contact(oldName, "", ""), newContact);
        return Result.success(b);
    }

    @DeleteMapping
    public Result deleteContact(@RequestParam String name) throws IOException {
       Boolean b= contactService.deleteContact(name);
       return Result.success(b);
    }
}
