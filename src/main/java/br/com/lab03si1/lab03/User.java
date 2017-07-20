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
     
     @Column(name="nota")
     private int nota;
     
     @Column(name="episodio")
     private String episodio;
     


	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getEpisodio() {
		return episodio;
	}

	public void setEpisodio(String episodio) {
		this.episodio = episodio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
