package com.valdir.todo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valdir.todo.domain.Todo;
import com.valdir.todo.services.TodoService;

@RestController
@RequestMapping(value = "/api/todo")
@CrossOrigin("http://localhost:4200")
public class TodoResource {
	
	@Autowired
	private TodoService service;
	
	@GetMapping
	public ResponseEntity<List<Todo>> findAll() {
		List<Todo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Todo> findById(@PathVariable Integer id) {
		Todo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Todo> insert(@RequestBody Todo obj) {
		Todo newObj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Todo> update(@RequestBody Todo obj, @PathVariable Integer id) {
		obj.setId(id);
		Todo newObj = service.update(obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}/done")
	public ResponseEntity<Todo> markAsDone(@PathVariable Integer id) {
		Todo obj = service.markAsDone(id);
		return ResponseEntity.ok().body(obj);
	}

}
