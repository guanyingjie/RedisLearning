package com.north.learning.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto implements Serializable {

    private static final long serialVersionUID = 590818301412456351L;
    private String name;
    private Integer age;
}
