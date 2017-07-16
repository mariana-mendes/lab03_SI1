package br.com.lab03si1.lab03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


	List<User> findAll();
	
	User findByLogin(String login);

}
