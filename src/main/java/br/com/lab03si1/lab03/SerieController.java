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
	public SerieController(SerieRepository serieRepository) {
		this.serieRepository = serieRepository;
	}

	@RequestMapping(value = "/addSerie", method = RequestMethod.POST)
	public void addSerie(@RequestBody Serie serie) {
		serieRepository.save(serie);
		return;
	}

	@RequestMapping(value = "getSeries/{idUsuario}", method = RequestMethod.GET)
	public List<Serie> getSeries(@PathVariable Long idUsuario) {
		return serieRepository.findByidUsuario(idUsuario);

	}

	@RequestMapping(value = "watchlistToPerfil/{idUsuario}", method = RequestMethod.PUT)
	public void watchlistToPerfil(@PathVariable Long idUsuario, @RequestBody String idIMDB) {
		List<Serie> series = getSeriesByUser(idUsuario);
		Serie serie = getSerieById(series, idIMDB);
		serie.setWatchlist(false);
		serieRepository.save(serie);
	} 

	private List<Serie> getSeriesByUser(Long idUsuario) {
		return serieRepository.findByidUsuario(idUsuario);
	}

	
	private Serie getSerieById(List<Serie> seriesDoUser, String imdbId) {
		for (Serie serie : seriesDoUser) {
			if (serie.getIdIMDB().equals(imdbId)) {
				return serie;
			}
		}
		return null;

	};

	@RequestMapping(value = "/{idUsuario}/removeSerie", method = RequestMethod.PUT)
	public void removeSerie(@RequestBody String imdbId, @PathVariable Long idUsuario) {
		List<Serie> series = getSeriesByUser(idUsuario);
		Serie serie = getSerieById(series, imdbId);
		serieRepository.delete(serie);
	

	}

}
