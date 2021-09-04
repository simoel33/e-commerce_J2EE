package com.occasion.maroc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.occasion.maroc.entities.UserEntity;
import com.occasion.maroc.repositories.UserRepository;
import com.occasion.maroc.services.UserServices;
import com.occasion.maroc.shared.Utils;
import com.occasion.maroc.shared.dto.ProductDto;
import com.occasion.maroc.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		UserEntity check = userRepository.findByEmail(userDto.getEmail());

		if (check != null)
			throw new RuntimeException("User Already Existe");

		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		for (int i = 0; i < userDto.getProducts().size(); i++) {
			
			ProductDto product = userDto.getProducts().get(i);
			product.setUser(userDto);
			product.setProductId(utils.generateStringId(10));
			userDto.getProducts().set(i, product); 
			
		}
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utils.generateStringId(10));
		
		

		UserEntity newUser = userRepository.save(userEntity);

		
		UserDto userCreer = modelMapper.map(newUser, UserDto.class);
		
		

		return userCreer;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
				userEntity.getEncryptedPassword(), new ArrayList<>());
	}
	
	

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String id) {

		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null) throw new UsernameNotFoundException(id);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto updtateUser(String id, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(id);
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		
		UserEntity userUpdated = userRepository.save(userEntity);
		
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

	@Override
	public void deleteUser(String id) {
		
	UserEntity userEntity =	userRepository.findByUserId(id);
	userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		List<UserDto> usersDto = new ArrayList<>();
		org.springframework.data.domain.Pageable pageblerequest = PageRequest.of(page, limit);
		
		Page<UserEntity> userPage = userRepository.findAll(pageblerequest);
		List<UserEntity> users =  userPage.getContent();
		
		for (UserEntity userEntity : users) {
			UserDto user = new UserDto();
			BeanUtils.copyProperties(userEntity, user);
			usersDto.add(user);	
		}
		
		
		
		return usersDto;
	}

}
