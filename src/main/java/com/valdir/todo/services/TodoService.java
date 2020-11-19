package com.valdir.todo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.todo.domain.Todo;
import com.valdir.todo.repositories.TodoRepository;
import com.valdir.todo.services.exceptions.ObjectNotFoundException;

@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;

	public List<Todo> findAll() {
		return repository.findAll();
	}

	public Todo findById(Integer id) {
		Optional<Todo> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Todo.class.getName()));
	}

	public Todo insert(Todo obj) {
		obj.setId(null);
		obj.setCreatedDate(LocalDateTime.now());
		return !obj.getDescription().isEmpty() && obj.getDescription().length() > 3 ? repository.save(obj) : null;

	}

	public Todo update(Todo obj) {
		LocalDateTime date = findById(obj.getId()).getCreatedDate();
		Todo newObj = new Todo(obj.getId(), obj.getDescription(), date, obj.getDone(), obj.getFinishedDate());
		return repository.save(newObj);
	}

	public void deleteById(Integer id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new NotYetImplementedException("Método ainda não implementado");
		}
	}

	public Todo markAsDone(Integer id) {
		Todo obj = findById(id);
		obj.setDone(true);
		obj.setFinishedDate(LocalDateTime.now());
		return repository.save(obj);
	}

}
