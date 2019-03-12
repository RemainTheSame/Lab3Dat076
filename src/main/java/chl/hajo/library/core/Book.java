package chl.hajo.library.core;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A book written by some author(s)
 * Core model class
 * @author hajo
 */
@NoArgsConstructor
@EqualsAndHashCode(of = {"isbn"})
public class Book implements Serializable {

    @Setter
    @Getter
    @NotNull(message = "Isbn cannot be null")
    private String isbn;
    @Setter
    @Getter
    @NotNull(message = "Title cannot be null")
    private String title;
    @Setter
    @Getter
    private Genre genre;
    @Setter
    @Getter
    private double price;
    @Setter
    @Getter
    private Author author;
    

    public Book(String isbn, String title, double price, Genre genre, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.genre = genre;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title="
                + title + ", genre=" + genre
                + ", price=" + price + '}';
    }

}
