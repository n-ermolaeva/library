package com.library.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    @Getter
    @Setter
    private Integer authorId;

    @Column(name = "author_name")
    @Getter
    @Setter
    private String authorName;

}
