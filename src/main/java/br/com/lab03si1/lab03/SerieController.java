package br.com.lab03si1.lab03;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
	   
     
//    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//    public String listaSeries(@PathVariable("name") String name, Model model) {
//           List<Serie> listaSeries = serieRepository.findByName(name);
//          if (listaSeries != null) {
//        	  	
//                model.addAttribute("series", listaSeries);
//                for (Serie serie : listaSeries) {
//                	  System.out.println(serie.getName());
//				}
//              
//          }
//          return "listaSeries";
//    }

    @RequestMapping(value = "/serie", method = RequestMethod.POST)
    public String adicionaSerie( @RequestBody Serie serie) {
          serie.setName(serie.getName());
          serieRepository.save(serie);
          return "redirect:/{serie}";
    }
	  
   
  
}
