package vikshii.namedParameterJDBC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vikshii.namedParameterJDBC.model.User;
import vikshii.namedParameterJDBC.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String title) {
		try {
			List<User> list = userService.findAll(title);

			if (list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createusers")
	public ResponseEntity<String> createUser(@RequestBody User user) throws Exception {
		try {
			userService.createUser(user);
			return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<List<User>> findUserById(@PathVariable("id") int id) {
		List<User> user = userService.findUserById(id);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/edituser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody User user) {

		user.setId(id);
		userService.updateUser(id, user);

		if (user != null) {
			return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/deleteusers/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		try {
			int result = userService.deleteUser(id);
			if (result == 0) {
				return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("User was deleted successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getDetails")
	public ResponseEntity<List<User>> getUserDetails() {
		List<User> userDetails = userService.getUserDetails();
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
}
