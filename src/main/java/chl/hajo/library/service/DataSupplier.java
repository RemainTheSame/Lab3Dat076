package chl.hajo.library.service;

import chl.hajo.library.core.Address;
import chl.hajo.library.core.Author;
import chl.hajo.library.core.Book;
import chl.hajo.library.core.Genre;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility to get some data to the simulated database
 *
 * @author hajo
 */
public class DataSupplier {

    private final static Address[] ADDRS = {new Address("Vägen", 1, "Bruksorten"),
        new Address("Stigen", 2, "Centralorten"), new Address("Allen", 3, "Storstaden")};
    private static final Random RAND = new Random();

    public static Address getRandomAddress() {
        return ADDRS[RAND.nextInt(ADDRS.length)];
    }

    public static List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        String[] auths = {
            "FF;Fia;Fiasson;fia@mail",
            "NN;Nisse;Nissesson;nisse@mail",
            "PP;Pelle;Pellesson;pelle@mail",
            "SN;Siv;Nissesson;siv@mail",
            "PE;Pelle;Eriksson;eriksson@mail",
            "RU;Ruben;Eriksson;ruben@mail"
            };
        for (String s : auths) {
            String[] d = s.split(";");
            Author a = new Author(d[0], d[1], d[2], d[3], getRandomAddress());
            authors.add(a);
        }
        return authors;
        
    }
    
    public static List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        String[] bookStrings = {
            "01;Bok1;THRILLER;22",
            "02;Bok2;BIOGRAPHY;11",
            "03;Bok3;NOVEL;21",
            "04;Bok4;NOVEL;12",
            "05;Bok5;NOVEL;21",
            "06;Bok6;NOVEL;12"
            };
        for (String s : bookStrings) {
            String[] d = s.split(";");
            Book b = new Book(d[0], d[1], Double.parseDouble(d[3]), Genre.valueOf(d[2]), getRandomAuthor());
            books.add(b);
        }
        return books;
        
        
        
        
        /*
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("01","Hej",1.0,Genre.BIOGRAPHY);
        Book book2 = new Book("02","Då",2.0,Genre.ROMANTIC_NOVEL);
        books.add(book2);
        books.add(book1);
        return books;*/
    }
    
    public static Author getRandomAuthor() {
        List<Author> authors = getAuthors();
        Random rand = new Random();
        int i = rand.nextInt(authors.size());
        return authors.get(i);
    }

}
