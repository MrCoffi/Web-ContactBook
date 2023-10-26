package com.example.simpleweb.service;

import com.example.simpleweb.entity.Contact;
import com.example.simpleweb.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        log.debug("call findAll in repository");

        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("call findById in repository");
        return contactRepository.findById(id);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("call save in repository");
        return contactRepository.save(contact);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("call deleteById in repository");
        contactRepository.deleteById(id);
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("call update in repository");
        return  contactRepository.update(contact);
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        log.debug("call batchInsert in repository ");
        contactRepository.batchInsert(contacts);
    }
}
