package br.com.lab03si1.lab03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository  extends JpaRepository<Serie, Long> {
	
	
    List<Serie> findByidIMDB(String idIMDB);
    
   
    List<Serie> findById(String id);


	List<Serie> findByidUsuario(Long idUsuario);
    
 

}
