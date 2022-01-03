package com.library.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    @Getter
    @Setter
    private Integer bookId;

    @Column(name="book_name")
    @Getter
    @Setter
    private String bookName;

    @Column(name="author_books_id")
    @Getter
    @Setter
    private Integer authorBooksId;
}
