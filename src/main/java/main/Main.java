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
//        private static void updateNewBook(){
//            BookEntity bookEntity = new BookEntity();
//            bookEntity.setName("Java A-Z");
//            bookEntity.setAuthor("Bruno");
//            bookEntity.setCategory(" books");
//            bookEntity.setIsbn("ISIBF1323242");
//            bookEntity.setNumberPage(244);
//            bookEntity.setPrice(19.5);
//            bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));
//            BookEntity result = bookRepository.save(bookEntity);
//            if (result !=null){
//                System.out.println("A new book saved successfully,book ID="+bookEntity.getId());
//            }
private static void readBook(){
    List<Book> bookList = (List<Book>) repository.findAll();
    System.out.println("Found " + bookList.size() + " book(s) in the table book");
    System.out.println("They are: ");
    bookList.forEach(System.out::println);
}
    private static void readBook(Long id){
        Optional<Book> book=repository.findById(id);

        if(book.isPresent()){
            System.out.println( book.get().toString());
        } else {
            System.out.println("Not found any book with book ID!");
        }
    }
    private static void deletedById(Long id){
        Optional<Book> isExisted=repository.findById(id);

        if(isExisted.isPresent()){
            repository.deleteById(id);
            System.out.println("Book is deleted!");
        } else {
            System.out.println("Not found any book with book ID!");
        }
    }

    // Hiển thị danh sách các Sách không có tên là Java
    public List<Book> findBooksNotContainingJava() {
        String keyword = "Java";
        List<Book> books = bookRepository.findAllByNameNotLike("%" + keyword + "%");
        return books;
    }
    // Hiển thị danh sách các Sách có ngày publishDate bé hơn ngày hiện tại
    public List<Book> findBooksPublishedBeforeNow() {
        LocalDate currentDate = LocalDate.now();
        List<Book> books = bookRepository.findAllByPublishDateBefore(currentDate);
        return books;
    }
       // Update toàn bộ Sách có Giá lớn hơn 100 thành giá 90
     private static void updatePrice(Double newPrice, Double oldPrice){
         List<Book> bookList=bookRepository.findAllByPriceEquals(oldPrice);

         if(!bookList.isEmpty()){
             bookRepository.updateByPrice(newPrice, oldPrice);
             System.out.println("Updating is successful!");
         } else {
             System.out.println("Not found any book with price = "+oldPrice);
         }
     }
     //  Xoá toàn bộ Sách có tên là Java và tác giả là Jonh
    private static void deletedByNameAndAuthor(String name, String author){

        List<Book> bookList=bookRepository.findAllByNameLikeAndAuthorLike(name, author);

        if(!bookList.isEmpty()){
            bookRepository.deleteAllByNameLikeAndAuthorLike(name, author);
            System.out.println("Deleting is successful!");
        } else {
            System.out.println("Not found any book with name ");
        }
    }
    public static void main(String[] args) throws Exception {
//        updateNewBook();
//        deletedByNameAndAuthor
//           updatePrice
//        findBooksPublishedBeforeNow
//       findBooksNotContainingJava
//         deletedById
//         readbook
    }
}