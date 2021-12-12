package com.library.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    @Getter
    @Setter
    private Integer author_id;

    @Column(name = "author_name")
    @Getter
    @Setter
    private String author_name;

}
