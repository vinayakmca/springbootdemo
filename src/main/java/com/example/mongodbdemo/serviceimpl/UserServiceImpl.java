package com.example.mongodbdemo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mongodbdemo.DTO.UserDTO;
import com.example.mongodbdemo.entity.User;
import com.example.mongodbdemo.repository.UserRepository;
import com.example.mongodbdemo.sequence.UserSequences;
import com.example.mongodbdemo.service.UserService;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private MongoOperations mongo;
	
	 public int getNextSequence(String seqName)
	    {
	        UserSequences counter = mongo.findAndModify(
	            query(where("_id").is(seqName)),
	            new Update().inc("seq",1),
	            options().returnNew(true).upsert(true),
	            UserSequences.class);
	        return counter.getSeq();
	    }

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		
		User user=new User();
		user.setFirstName(userDTO.getFirstName());
		String encodedPassword = new BCryptPasswordEncoder().encode(userDTO.getLastName());
        user.setLastName(encodedPassword);
        user.setCity(userDTO.getCity());
        user.setImage(userDTO.getImage());
        user.setId(getNextSequence("userSequences"));
		 userRepository.save(user);
		userDTO.setId(user.getId());
		
		return userDTO;
		
	}
	
	@Override
    public User getUser(String id) {
	
		return userRepository.findById(Integer.parseInt(id)).orElse(null);
	}
	@Override
    public User updateUser(UserDTO userUpdateDTO, String id) {
		User user=getUser(id);
		user.setFirstName(userUpdateDTO.getFirstName());
		user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setCity(userUpdateDTO.getCity());
        user.setImage(userUpdateDTO.getImage());
        userRepository.save(user);
		return user;
	}
	@Override
    public String deleteUser(String id) {
		userRepository.deleteById(Integer.parseInt(id));
		return "record deleteed succefully";
	}
	
	@Override
	  public Page<User> getAllUser(int pageNo){
		 Pageable pageable = PageRequest.of(pageNo-1,5);
		return userRepository.findAll(pageable);
		
	}
	
	@Override
	 public UserDetails findByUserName(String name) {
		 User user=userRepository.findByFirstName(name);
		 if(user==null) {
			 throw new UsernameNotFoundException("User not found with username: " + name);
		 }
		 return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getLastName(),
	                new ArrayList<>());
	 }
}
