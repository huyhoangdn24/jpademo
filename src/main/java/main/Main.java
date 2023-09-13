package main;

import configuration.JPAConfig;
import entity.BookEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import repository.BookRepository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static main.MainTestTransaction.applicationContext;


public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
    static BookRepository repository = applicationContext.getBean("BookRepository", BookRepository.class);

//    private static void createNewBook(){
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setName("Java A-Z");
//        bookEntity.setAuthor("Roger");
//        bookEntity.setCategory("IT books");
//        bookEntity.setIsbn("ISIBF1323242");
//        bookEntity.setNumberPage(234);
//        bookEntity.setPrice(20.5);
//        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));
//        BookEntity result = bookRepository.save(bookEntity);
//        if (result !=null){
//            System.out.println("A new book saved successfully,book ID="+bookEntity.getId());
//        }
        private static void updateNewBook() {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setName("Java A-Z");
            bookEntity.setAuthor("Bruno");
            bookEntity.setCategory(" books");
            bookEntity.setIsbn("ISIBF1323242");
            bookEntity.setNumberPage(244);
            bookEntity.setPrice(19.5);
            bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));
            BookEntity result = bookRepository.save(bookEntity);
            if (result != null) {
                System.out.println("A new book saved successfully,book ID=" + bookEntity.getId());
            }
        }
    private static void readBook() {
        List<BookEntity> bookEntity = (List<BookEntity>) bookRepository.findAll();
        System.out.println("Found" + bookEntity.size()+ "book(s) in the table book");
        System.out.println("They are : ");
        for(BookEntity book: bookEntity){
            System.out.println(bookEntity.toString());
        }
    }
         private static void deletedById(){
             bookRepository.deleteById(1);
             Optional<BookEntity> bookEntity = bookRepository.findById(2);
             if (bookEntity.isPresent()) {
                 BookEntity book1 = bookEntity.get();
                 bookRepository.delete(book1);
             }
             bookRepository.deleteAll();
         }
    private static void updatePrice(Double newPrice, Double oldPrice){
        List<BookEntity> bookEntity=bookRepository.findAllByPriceEquals(oldPrice);
           if(!bookEntity.isEmpty()){
                bookEntity.forEach(    book -> {
                book.setPrice(newPrice);
                bookRepository.save(book);
              });
               System.out.println("Price is updated");
           } else {
            System.out.println("Not found any book with price = "+oldPrice);
        }
    }
    private static void  findBooksPublishedBeforeNow(){
        LocalDate currentDate = LocalDate.now();
        List<BookEntity> bookEntity = bookRepository.findBooksPublishedBeforeNow(currentDate);
        if (!bookEntity.isEmpty()) {
            System.out.println("List of books whose publishDate is less than the current date:");
            for (BookEntity book : bookEntity) {
                System.out.println( book.getPublishDate());
            }
        } else {
            System.out.println("There are no books with a publishDate less than the current date.");
        }
    }
    private static void findAllByNameContaining(String name){
        List<BookEntity> bookEntity=bookRepository.findAllByNameContaining(name);
        if(!bookEntity.isEmpty()){
            System.out.println(String.format("List with name is containing %s",name));
            bookEntity.forEach(System.out::println);

        } else {
            System.out.println(String.format("Not found any book with name is %s ", name));
        }
    }
    private static void  deletedByNameAndAuthor(){
        List<BookEntity> bookEntity= bookRepository.deletedByNameAndAuthor("Java","John");
        if(bookEntity != null){
            bookRepository.deleteAll(bookEntity);
        }

    }

    public static void main (String[] args) {
//        updateNewBook();
//        deletedByNameAndAuthor();
//           updatePrice();
//        findBooksPublishedBeforeNow();
//     findBooksNotContainingJava();
//         deletedById();
//       readBook();
    }
}