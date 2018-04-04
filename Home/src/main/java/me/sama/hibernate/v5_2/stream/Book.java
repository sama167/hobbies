package me.sama.hibernate.v5_2.stream;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author SAMA
 * @since 04/04/2018.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column
    String title;

    @Column
    LocalDate publishDate;
}
