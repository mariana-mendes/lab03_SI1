package br.com.lab03si1.lab03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class UserController {

	private UserRepository userRepository;
	private List<User> usuariosCadastrados;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getResource() {
		usuariosCadastrados = userRepository.findAll();
		return usuariosCadastrados;
	}

	@RequestMapping(value = "/postUser", method = RequestMethod.POST)
	public void postUser(@RequestBody User user) {
		System.out.println(user);

		if (!loginRepetido(user.getLogin())) {
			userRepository.save(user);
			return;
		}

		throw new RuntimeException();

	}
	
	@RequestMapping(value="/loginUsuario", method = RequestMethod.POST)
	public void login(@RequestBody User user) {
		System.out.println(user.getLogin());
		if(!matchLoginESenha(user.getLogin(), user.getSenha())) {
			throw new RuntimeException();
		}
		return;
	}
	
	private boolean loginRepetido(String login) {
		usuariosCadastrados = userRepository.findAll();
		for (User user : usuariosCadastrados) {
			if (user.getLogin().equalsIgnoreCase(login)) {
				return true;
			}
		}
		return false;

	}

	private boolean matchLoginESenha(String login, String senha) {
		usuariosCadastrados = userRepository.findAll();
		for (User user : usuariosCadastrados) {
			if (user.getLogin().equalsIgnoreCase(login) && user.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;

	}

}
