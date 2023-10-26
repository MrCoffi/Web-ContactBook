package com.example.simpleweb.repository;

import com.example.simpleweb.entity.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class InMemoryContactRepository implements ContactRepository {
    private final List<Contact> contactList = new ArrayList<>();

    @Override
    public List<Contact> findAll() {
        log.debug("call findALL");
        return contactList;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("call findById");
        return contactList.stream().filter(f -> f.getId() == id).findFirst();
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("call save");
        contact.setId(System.currentTimeMillis());
        contactList.add(contact);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("call deleteById {}", id);
        findById(id).ifPresentOrElse(contactList::remove,null);
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("call update");
        Contact existContact = findById(contact.getId()).orElse(null);
        if (existContact != null) {
            existContact.setEmail(contact.getEmail());
            existContact.setFirstname(contact.getFirstname());
            existContact.setLastname(contact.getLastname());
            existContact.setPhone(contact.getPhone());

        }
        return existContact;
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        this.contactList.addAll(contacts);
    }
}