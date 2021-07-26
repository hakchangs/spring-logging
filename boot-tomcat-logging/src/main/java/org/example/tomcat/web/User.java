package org.example.tomcat.web;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor @Builder
@Getter @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long id;
    String name;

}
