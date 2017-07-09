package br.com.lab03si1.lab03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository  extends JpaRepository<Serie, Long> {
	
	
    List<Serie> findByName(String name);
    
   
    List<Serie> findById(String id);
    
 

}
