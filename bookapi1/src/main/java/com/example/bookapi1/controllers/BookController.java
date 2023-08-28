package com.example.bookapi1.controllers;



import java.awt.print.Book;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookapi1.models.BookModel;
import com.example.bookapi1.services.BookService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")
@Controller	
public class BookController {

//    private final BookService bookService;
//    
//    public BooksController(BookService bookService) {
//        this.bookService = bookService;
//    }
	
	@Autowired
	private BookService bookservice;
	
	@GetMapping("/")
	public String raiz() {
		return "redirect:/books";
	}
	
	
	//TODOS LOS LIBROS
	@GetMapping("/books")
	public String index(Model model) {
	List <BookModel> books = bookservice.allBooks();
	model.addAttribute("books", books);
	return "/books/index.jsp";
	
	}
	

	    
	  
	

	
	//CREAR UN NUEVO LIBRO
	@GetMapping("/books/new")
	public String formularioLibro(@ModelAttribute("libro")BookModel book) {
		
		return "/books/new.jsp";
		
	}
	
	@PostMapping("/books")
	public String crearLibro(@Valid @ModelAttribute("libro")BookModel book, BindingResult result, Model viewModel) {
		if(result.hasErrors()) {
			return "/books/new.jsp";
		}else {
			bookservice.createBook(book);
			return "redirect:/books";
		}
	}
	//BORRAR UN LIBRO
	@DeleteMapping("/books/{id}")
	public String destroy(@PathVariable("id")Long id) {
		bookservice.borrarRegistro(id);
		return "redirect:/books";
	}
	
	
	//EDITAR UN LIBRO
	@GetMapping("/books/{id}/edit")
     public String edit(@PathVariable("id")Long id, Model model) {
		BookModel book = bookservice.findBook(id);
		model.addAttribute("libro", book);
		return "/books/edit.jsp";
		}
	@PutMapping("/books/{id}")
	public String update(@Valid @ModelAttribute("libro")Model book, BindingResult result) {
		if (result.hasErrors()) {
			return "/books/edit.jsp";
		}else {
			 bookservice.actualizarLibro((BookModel) book);
			return "redirect:/books";
			
		}
	}
	
	
	
	@GetMapping("/books/{id}")
	public String show(@PathVariable("id")Long id, Model model) {
		BookModel book= bookservice.findBook(id);
		
		
		if(book != null) {
			model.addAttribute("libro", book);
			return "books/show.jsp";
		}else {
			return "redirect:/books";
		}
	}
	
	
	//CONSTRUCTOR
	public BookController() {
		
	}

}
