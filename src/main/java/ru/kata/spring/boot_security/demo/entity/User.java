package ru.kata.spring.boot_security.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;

}