package com.valdir.todo;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.valdir.todo.domain.Todo;
import com.valdir.todo.repositories.TodoRepository;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner{
	
	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Todo t1 = new Todo(null, "Estudar Spring", LocalDateTime.now(), null, null);
		Todo t2 = new Todo(null, "Estudar Angular", LocalDateTime.now(), null, null);
		Todo t3 = new Todo(null, "Deploy do backend no Heroku", LocalDateTime.now(), null, null);
		Todo t4 = new Todo(null, "Angular no Github pages", LocalDateTime.now(), null, null);
		
		todoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
	}

}
