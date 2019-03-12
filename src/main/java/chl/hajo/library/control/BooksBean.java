/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chl.hajo.library.control;

import chl.hajo.library.core.Author;
import chl.hajo.library.core.Book;
import chl.hajo.library.core.Genre;
import chl.hajo.library.dao.BookCatalogue;
import chl.hajo.library.service.DataSupplier;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.component.dataTable.DataTable;
import net.bootsfaces.utils.FacesMessages;


/**
 *
 * @author oscar
 */
@Named("book")
@SessionScoped
public class BooksBean implements Serializable{

    private static final Logger LOG = Logger.getLogger(BooksBean.class.getName());
    @EJB        // Injecting an Enterprise bean
    private BookCatalogue boC;
    @Getter     // Lombok
    @Setter
    private Book tmp = new Book();


    @PostConstruct // CDI life cycle callbacks
    void post() {
        out.println(this + "Alive");
    }

    public void page() {
       // Faces context hold all data relevant for this request
       DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("bookForm:bookTable");
       LOG.log(Level.INFO, "Test {0}", dt.getJQueryEvents()); 
    }
    // ------------ Navigation -------------------

    public void cancel() {
        tmp = new Book();
    }

    // --------- Call backend -------------------------
    public void setBook() {
        tmp = boC.find(tmp.getIsbn());
    }

    public List<Book> findAll() {
        return boC.findAll();
    }

    public void add() {

        try {
            boC.create(tmp);
            FacesMessages.info("Success");
        } catch (RuntimeException sql) {
            String message = sql.getMessage();
            FacesMessages.info("Fail " + message);
        }
        tmp = new Book();
    }
    
    public List<Book> findAuthorBooks(Author author){
        return boC.findAuthorBooks(author);
    }

    public Genre[]getGenres(){
        Genre[] genres = Genre.values();
        /*List<String> genreStrings = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            genreStrings.add(genres[i].name());
        }*/
        return genres;
    }



    public void update() {
        boC.update(tmp);
        tmp = new Book();
    }

    public void delete() {
        boC.delete(tmp.getIsbn());
        tmp = new Book();
    }
}
