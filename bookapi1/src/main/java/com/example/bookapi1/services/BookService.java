package com.example.bookapi1.services;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookapi1.models.BookModel;
import com.example.bookapi1.repositories.BookRepo;







@Service
public class BookService {

	
	
	//Agregando el book al repositorio como una dependencia
//    private final bookrepo bookrepo;
//    
//    public BookService(bookrepo bookrepo) {
//        this.bookrepo = bookrepo;
//    }
	
	
	@Autowired
	private BookRepo bookrepo;
	
	
	//DEVOLVER TODOS LOS LIBROS
	public List<BookModel> allBooks(){
	
	
	return bookrepo.findAll();
	}
	
	
	
	//CREAR UN LIBRO 
	public BookModel createBook(BookModel b) {
		return bookrepo.save(b);
	}
	
	//OBTENER LA INFO DE UN LIBRO
	public BookModel findBook(Long id) {
		Optional<BookModel> optionalBook = bookrepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}else {
			return null;
		
		}
		
		
	}
	//ACTUALIZAR LIBRO 
	public BookModel actualizarLibro(BookModel libro) {
    	BookModel temporal = findBook(libro.getId());
    	temporal.setDescription(libro.getDescription());
    	temporal.setLanguage(libro.getLanguage());
    	temporal.setNumberOfPages(libro.getNumberOfPages());
    	temporal.setTitle(libro.getTitle());
    	return bookrepo.save(temporal);
    }

	
	//SOBRRECARGA DE METODOS PARA ACTUALIZAR UN REGISTRO
	
	   public BookModel actualizarLibro(Long id, 
    		String title, 
    		String desc, 
    		String lang, 
    		Integer numOfPages) {
    	BookModel temporal = bookrepo.findById(id).orElse(null);
    	if(temporal != null) {
    		temporal.setDescription(desc);
    		temporal.setLanguage(lang);
    		temporal.setNumberOfPages(numOfPages);
    		temporal.setTitle(title);
    		
    		bookrepo.save(temporal);
    		return temporal;
    	}else {
    		
    		return temporal;
    	}
    }
    
    //BORRAR UN REGISTRO
	   public void borrarRegistro(Long id) {
		   bookrepo.deleteById(id);
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//CONSTRUCTOR
	public BookService() {
	}

}
