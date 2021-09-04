package com.occasion.maroc.controllers;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.occasion.maroc.requests.UserRequest;
import com.occasion.maroc.responses.UserResponse;
import com.occasion.maroc.services.UserServices;
import com.occasion.maroc.shared.dto.UserDto;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired // l'injection des dependances
	UserServices userService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {

		UserDto userDto = userService.getUserByUserId(id);

		UserResponse useResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, useResponse);

		return new ResponseEntity<>(useResponse, HttpStatus.OK);

	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit) {

		List<UserResponse> usersResponse = new ArrayList<>();

		List<UserDto> users = userService.getUsers(page, limit);

		for (UserDto userDto : users) {
			UserResponse user = new UserResponse();
			BeanUtils.copyProperties(userDto, user);
			usersResponse.add(user);
		}
		return usersResponse;

	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createtUser(@RequestBody @Valid UserRequest userRequest) {
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);

		UserDto creerUser = userService.createUser(userDto);

		UserResponse userResponse = modelMapper.map(creerUser, UserResponse.class);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> editUser(@PathVariable String id, @RequestBody UserRequest userRequest) {

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userRequest, userDto);

		UserDto userUpdated = userService.updtateUser(id, userDto);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(userUpdated, userResponse);

		return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deletUser(@PathVariable String id) {

		userService.deleteUser(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/all")
	public String all() {
		
		return "all was called";
	}

}
