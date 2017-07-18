package br.com.lab03si1.lab03;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Serie {
	
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      @Column(name="idIMDB")
       private String idIMDB; 
      
   
	@Column(name="description")
      private String description;
      
      @Column(name="idUsuario")
      private Long idUsuario;
      
      @Column(name="watchlist")
      private boolean watchlist;
      
      public boolean getWatchlist() {
		return watchlist;
	}


	public void setWatchlist(boolean watchlist) {
		this.watchlist = watchlist;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}
      
      
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getId() {
            return id;
      }
      public void setId(Long id) {
            this.id = id;
      }
     
    
      public String getDescription() {
            return description;
      }
      public void setDescription(String description) {
            this.description = description;
      }
      public String getIdIMDB() {
  		return idIMDB;
  	}
  	public void setIdIMDB(String idIMDB) {
  		this.idIMDB = idIMDB;
  	}
 
} 
		
	

