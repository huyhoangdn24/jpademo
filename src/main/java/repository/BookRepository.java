package repository;

import entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookEntity,Integer> {


    List<BookEntity> findAllByPriceEquals(Double oldPrice);

    List<BookEntity> findBooksPublishedBeforeNow(LocalDate currentDate);

    List<BookEntity> findAllByNameContaining(String name);

    List<BookEntity> deletedByNameAndAuthor(String java, String john);
}
