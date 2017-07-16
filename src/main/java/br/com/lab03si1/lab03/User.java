package br.com.lab03si1.lab03;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 

	  @Column(name="login", nullable=false)
      private String login; 
     
     @Column(name="senha" , nullable=false)
     private String senha;


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	


}
