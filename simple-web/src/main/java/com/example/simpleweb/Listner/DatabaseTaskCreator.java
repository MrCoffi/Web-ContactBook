package com.example.simpleweb.Listner;

import com.example.simpleweb.entity.Contact;
import com.example.simpleweb.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseTaskCreator {
    private final ContactService contactService;

//   @EventListener(ApplicationReadyEvent.class)
//    public void createTaskData() {
//        log.debug("Calling create task");
//
//        List<Contact> contactList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            int value = i + 1;
//            Contact contact = new Contact();
//            contact.setId(12+i);
//            contact.setLastname("name " + value);
//            contact.setFirstname("firstname :" + value);
//            contact.setEmail(value +"@mail.ru" );
//            contact.setPhone("+7435432321"+ value);
//            contactList.add(contact);
//        }
//
//        contactService.batchInsert(contactList);
//    }

}
