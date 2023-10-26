package com.example.simpleweb.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class Contact {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}
