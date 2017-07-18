package br.com.lab03si1.lab03;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/")
public class SerieController {

    private SerieRepository serieRepository;
    
    @Autowired
    public SerieController( SerieRepository serieRepository) {
          this.serieRepository = serieRepository;
    }
	   
     

    @RequestMapping(value = "/addSerie", method = RequestMethod.POST)
    public void adicionaSerie( @RequestBody Serie serie) {
    	serieRepository.save(serie);
          return;
    }
    
    
	 
    @RequestMapping(value ="getSeries/{idUsuario}", method = RequestMethod.GET)
    public List<Serie> getSeries(@PathVariable Long idUsuario ){
    	return     serieRepository.findByidUsuario(idUsuario);

    			
    	
    	
    }
    
    @RequestMapping(value="removeSerie", method = RequestMethod.POST)
    public void removeSerie(@RequestBody Long idUser){
    	
    }
   
  
}
