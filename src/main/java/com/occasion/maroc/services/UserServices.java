package com.occasion.maroc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.occasion.maroc.shared.dto.UserDto;

public interface UserServices extends UserDetailsService {
	
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	
	UserDto getUserByUserId(String id);
	
	UserDto updtateUser(String id,UserDto userDto);
	
	void deleteUser(String id);
	List<UserDto> getUsers(int page,int limit);

}
