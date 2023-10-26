package com.example.simpleweb.repository;

import com.example.simpleweb.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> findAll();
    Optional<Contact> findById(Long id);
    Contact save(Contact contact);
    void deleteById(Long id);
    Contact update(Contact contact);

    void batchInsert(List<Contact> contacts);


}
