package br.com.lab03si1.lab03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/")
public class UserController {
	/*
	 * Sem usar o h2, guardando apenas num array
	 */
	List<User> usuarios = new ArrayList<User>();
	
	private UserRepository userRepository;
	
	  @Autowired
	    public UserController( UserRepository userRepository) {
	          this.userRepository = userRepository;
	    }
	  

		@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
		public List<User> getResource(){
				return usuarios;
		}
		
	  
	  
	  @RequestMapping(value="/postUser", method = RequestMethod.POST)
	  public void postUser(@RequestBody User user) {
		  usuarios.add(user);
		  return;
	  }

}
