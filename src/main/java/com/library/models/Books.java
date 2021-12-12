package com.library.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    @Getter
    @Setter
    private Integer book_id;

    @Column(name="book_name")
    @Getter
    @Setter
    private String book_name;

    @Column(name="author_books_id")
    @Getter
    @Setter
    private Integer author_books_id;
}
