/**
 * 
 */
package com.zyt.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyt.dto.User;
import com.zyt.exception.UserNotExistException;

/**
 * @author Colin
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping
	public User create(@RequestBody @Validated User user, BindingResult errors) {
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		
		System.out.println("user: " + user.toString());
		
		user.setId("1");
		return user;
	}
	
	@PutMapping(value = "/{id:\\d+}")
	public User update(@RequestBody @Validated User user, BindingResult errors) {
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		
		System.out.println("user: " + user.toString());
		
		user.setId("1");
		return user;
	}
	
	@DeleteMapping(value = "/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println("id: " + id);
	}

	@GetMapping
	public List<User> query(User user, @PageableDefault(page = 1, size = 10, sort = "username.asc") Pageable pageable) {
		
		System.out.println("user: " + user.toString());
		System.out.println(pageable.toString());
		
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	//利用正则表达式 限制id只能为数字
	@GetMapping(value = "/{id:\\d+}")
	public User getInfo(@PathVariable String id) {
		
//		throw new UserNotExistException(id);
//		throw new RuntimeException("user not exist");
		System.out.println("进入getInfo服务");
		System.out.println("id: " + id);
		
		User user = new User();
		user.setUsername("tom");
		return user;
	}
}
