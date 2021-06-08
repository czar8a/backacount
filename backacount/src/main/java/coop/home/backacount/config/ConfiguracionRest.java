package coop.home.backacount.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
class ConfiguracionRest {

	/**
	 * Bean que permite el autowired de la clase ModelMapper.
	 * 
	 * @return una instancia de la clase ModelMapper.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Bean que permite el autowired de la clase RestTemplate
	 * 
	 * @return una instancia de la clase RestTemplate
	 */
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	/**
	 * Bean que permite el autowired de la clase ObjectMapper (para convertir json a
	 * DTO)
	 * 
	 * @return una instancia de la clase ObjectMapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
