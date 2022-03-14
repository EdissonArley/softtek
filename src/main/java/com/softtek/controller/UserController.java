package com.softtek.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtek.entity.User;
import com.softtek.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH,
		RequestMethod.DELETE })
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		Optional<User> user = userService.findById(id);

		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> add(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addOrUpdate(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?>  update(@PathVariable int id, @RequestBody User userUpdate) {
		Optional<User> user = userService.findById(id);

		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		user.get().setName(userUpdate.getName());
		user.get().setLastName(userUpdate.getLastName());
		user.get().setDni(userUpdate.getDni());
		user.get().setEmployee(userUpdate.getEmployee());
		return ResponseEntity.status(HttpStatus.OK).body(userService.addOrUpdate(user.get()));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Optional<User> user = userService.findById(id);

		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(id));
	}

}
