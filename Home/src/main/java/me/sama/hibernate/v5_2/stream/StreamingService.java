package me.sama.hibernate.v5_2.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SAMA
 * @since 04/04/2018.
 */
@Service
public class StreamingService {


    private StreamingDAO dao;

    @Autowired
    public StreamingService(StreamingDAO dao) {
        this.dao = dao;
    }

    public List<Book> list(){
        return  dao.list();
    }
}
