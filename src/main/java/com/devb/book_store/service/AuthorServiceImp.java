package com.devb.book_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devb.book_store.DTO.AuthorDTO;
import com.devb.book_store.entity.Author;
import com.devb.book_store.repository.AuthorRepositoy;
import com.devb.book_store.service.exception.DataIntegratyViolationException;
import com.devb.book_store.service.exception.ObjectNotFoundException;

@Service
public class AuthorServiceImp implements AuthorService {

	@Autowired
	AuthorRepositoy authorRepositoy;

	@Override
	public Author findByEmail(String email) {
		Optional<Author> autorOptional = authorRepositoy.findByEmailIgnoreCase(email);

		return autorOptional
				.orElseThrow(() -> new ObjectNotFoundException("Author with EMAIL: " + email +" not found"));
	}

	@Override
	public List<Author> getAll() {

		List<Author> lstAuthors = authorRepositoy.findAll();
		return lstAuthors;
	}

	@Override
	public Author findById(Integer id) {
		Optional<Author> author = authorRepositoy.findById(id);

		return author.orElseThrow(() -> new ObjectNotFoundException("Author with ID: " + id + " not found"));
	}

	@Override
	public Author update(Integer id, AuthorDTO authorDTO) {

		checkIfEmailisPresent(authorDTO.getEmail(), id);
		Author author = new Author(id, authorDTO.getFirstname(), authorDTO.getLastname(), authorDTO.getEmail());
		return authorRepositoy.save(author);
	}

	@Override
	public Author save(AuthorDTO authorDTO) {
		Optional<Author> at = authorRepositoy.findByEmailIgnoreCase(authorDTO.getEmail());
		if (at.isPresent()) {
			throw new DataIntegratyViolationException("EMAIL : " + authorDTO.getEmail() + " already exist");
		}
		Author author = new Author(null, authorDTO.getFirstname(), authorDTO.getLastname(), authorDTO.getEmail());

		return authorRepositoy.save(author);
	}

	@Override
	public void checkIfEmailisPresent(String email, Integer id) {
		Optional<Author> authorOptional = authorRepositoy.findByEmailIgnoreCase(email);
		if (authorOptional.isPresent() && !authorOptional.get().getId().equals(id)) {
			throw new DataIntegratyViolationException("Author with EMAIL : " + email + " is already Present");
		}
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		try {
			authorRepositoy.deleteById(id);
		} catch (DataIntegratyViolationException e) {
			throw new DataIntegratyViolationException("Object cannot be deleted : DB Rules ");
		}
	}
}
