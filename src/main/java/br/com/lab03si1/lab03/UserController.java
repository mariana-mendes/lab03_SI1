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
		} else {

			throw new RuntimeException();
		}
	}

	@RequestMapping(value = "/loginUsuario", method = RequestMethod.POST)
	public User login(@RequestBody User user) {

		if (!matchLoginESenha(user.getLogin(), user.getSenha())) {
			throw new RuntimeException();
		}
		return userRepository.findByLogin(user.getLogin());
	}

	private boolean loginRepetido(String login) {
		User logando = userRepository.findByLogin(login);
		return logando != null;

	}

	private boolean matchLoginESenha(String login, String senha) {
		User logando = userRepository.findByLogin(login);
		if (logando != null) {
			if (logando.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;

	}

}
