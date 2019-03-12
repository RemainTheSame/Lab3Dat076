package chl.hajo.library.dao;

import chl.hajo.library.core.Author;
import chl.hajo.library.core.Book;
import chl.hajo.library.service.DataSupplier;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * All orders Responsible for putting new PurchaseOrders objects into the model
 *
 * @author hajo
 */
@Stateless
public class BookCatalogue  {
    private final List<Book> books = DataSupplier.getBooks();
    
    public BookCatalogue(){}

    public Book find(String id) {
        for( Book b : books){
            if( b.getIsbn().equals(id)){
                return b;
            }
        }
        return null;
    }

    public List<Book> findAll() {
        return books;
    }

    public void create(Book book) {
         books.add(book);
    }

    public void update(Book book) {
        delete(book.getIsbn());
        create(book);
    }

    public void delete(String id) {
        Book b = find(id);
        if( b != null){
            books.remove(b);
        }
    }
    
    public List<Book> findAuthorBooks(Author author){
        List<Book> bList = new ArrayList();
        for (int i = 0; i < books.size(); i++) {
            Author tmp = books.get(i).getAuthor();
            if(tmp.equals(author))
                bList.add(books.get(i));
        }
        return bList;
    }

}
