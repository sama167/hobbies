package me.sama.hibernate.v5_2.stream;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author SAMA
 * @since 04/04/2018.
 */
@Repository
public class StreamingDAO {


    private SessionFactory sessionFactory;

    public StreamingDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> list() {
        Stream<Book> stream = sessionFactory.getCurrentSession().createQuery("SELECT b FROM Book b", Book.class).stream();
        return stream.collect(Collectors.toList());
    }
}
