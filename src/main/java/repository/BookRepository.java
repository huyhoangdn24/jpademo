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
    List<Book> findAllByNameNotLike(@Param("keyword") String keyword);

    List<Book> findAllByPublishDateBefore(LocalDate currentDate);

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    List<Book> findAllByPriceEquals(Double oldPrice);

    void updateByPrice(Double newPrice, Double oldPrice);



    List<Book> findAllByNameLikeAndAuthorLike(String name, String author);
    void deleteAllByNameLikeAndAuthorLike(String name, String author);
}
