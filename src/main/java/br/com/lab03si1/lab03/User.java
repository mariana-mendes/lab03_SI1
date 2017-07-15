package br.com.lab03si1.lab03;


import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class User {

	
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 

	  @Column(name="login")
      private String login; 
     
     @Column(name="senha")
     private String senha;

     @ElementCollection(fetch=FetchType.EAGER)
     @CollectionTable(name="series_user")
     @MapKeyColumn(name="serie_type")
    
     private Map<String, Serie> series;
     
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

	public Map<String, Serie> getSeries() {
		return series;
	}

	public void setSeries(Map<String, Serie> series) {
		this.series = series;
	}

	


}
