package com.example.simpleweb.repository;

import com.example.simpleweb.entity.Contact;
import com.example.simpleweb.exeption.ContactNotFoundException;
import com.example.simpleweb.mapper.TaskRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class DatabaseContactRepository implements ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        log.debug("calling databaseTaskRepository-> findAll");
        String sql = "SELECT * FROM tasks_shema.contact";
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("calling databaseTaskRepository-> findById");
        String sql = "SELECT * FROM tasks_shema.contact WHERE id = ? ";

        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new TaskRowMapper(), 1))
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("calling databaseTaskRepository-> save");
        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO tasks_shema.contact(firstname,lastname,email,phone,id) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, contact.getFirstname(), contact.getLastname(), contact.getEmail(),contact.getPhone(), contact.getId());
        return contact;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("calling databaseTaskRepository-> deleteById");
        String sql = "DELETE FROM tasks_shema.contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("calling databaseTaskRepository-> update");
        Contact existedContact = findById(contact.getId()).orElse(null);
        if (existedContact != null) {
            String sql = "UPDATE tasks_shema.contact SET firstname=?, lastname=?, email=?,phone=? WHERE id =?";
            jdbcTemplate.update(sql,
                    contact.getFirstname(), contact.getLastname(),
                    contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }
        log.warn("not found  task id for update {}", contact.getId());

        throw new ContactNotFoundException("Task for update not found id: " + contact.getId());
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        log.debug("calling databaseTaskRepository-> batchInsert");

        String sql = "INSERT INTO tasks_shema.contact(firstname,lastname,email,phone,id) VALUES(?,?,?,?,?)";


        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Contact contact = contacts.get(i);
                ps.setString(1, contact.getFirstname());
                ps.setString(2, contact.getLastname());
                ps.setString(3, contact.getEmail());
                ps.setString(4, contact.getPhone());
                ps.setLong(5, contact.getId());
            }

            @Override
            public int getBatchSize() {
                return contacts.size();
            }
        });
    }
}
