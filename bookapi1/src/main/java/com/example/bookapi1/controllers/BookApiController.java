package com.example.bookapi1.controllers;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookapi1.models.BookModel;
import com.example.bookapi1.services.BookService;









@RestController
public class BookApiController {
    
	//Inyeccion Depencias
//	 private final bookservice bookservice;
//	    public BookApiController(bookservice bookservice){
//	        this.bookservice = bookservice;
//	    }
	
	@Autowired
	private  BookService bookservice;
	
	
	//MOSTRAR INFO DE LOS LIBROS
	@GetMapping("/api/books")
	public List <BookModel> index(){
		return bookservice.allBooks();
	}
	
	//CREAR UN LIBRO
	@PostMapping(value ="/api/books")
    public BookModel create(@RequestParam(value="title")String title,
    		@RequestParam(value="description")String desc,
    		@RequestParam(value="language")String lang,
    		@RequestParam( value="pages")Integer numOfPages) {
		BookModel book = new BookModel(title, desc, lang, numOfPages);
		return bookservice.createBook(book);
	}
	
	//MOSTRAR INFORMACION ESPECIFICA DE UN LIBRO
	@GetMapping("/api/books/{id}")
	public BookModel show(@PathVariable("id")Long id) {
		BookModel book = bookservice.findBook(id);
		return book;
	}
	
	//ACTUALIZAR INFO DE UN LIBRO ESPECIFICO
    @SuppressWarnings("unused")
	@PutMapping("/api/books/{id}")
    public BookModel actualizar(@PathVariable("id")Long id,
    		@RequestParam(value="title") String title, 
    		@RequestParam(value="description") String desc,
    		@RequestParam(value="language") String lang, 
    		@RequestParam(value="pages") Integer numOfPages) {
    	BookModel book = new BookModel(id, title, desc, lang,numOfPages);
//return bookservice.actualizarLibro(book);
    	return bookservice.actualizarLibro(id, title, desc, lang, numOfPages );
    	
    }

	//BORRAR REGISTRO
    @DeleteMapping("/api/books/{id}")
    public void borrarResgistro(@PathVariable("id")Long id) {
      bookservice.borrarRegistro(id);
    }
	
	
	//CONSTRUCTOR
	public BookApiController() {
		
	}
	
	

}
