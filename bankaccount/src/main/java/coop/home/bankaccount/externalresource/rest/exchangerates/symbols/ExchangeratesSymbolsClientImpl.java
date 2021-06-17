package coop.home.bankaccount.externalresource.rest.exchangerates.symbols;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import coop.home.bankaccount.dto.accounttransfer.SymbolDTO;
import coop.home.bankaccount.externalresource.rest.ExchangeratesSymbolsClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
class ExchangeratesSymbolsClientImpl implements ExchangeratesSymbolsClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Value("${exchangerates.access_key}")
	private String accessKey;
	
	@Value("${exchangerates.symbols.url}")
	private String symbolsurl;
	
	public List<SymbolDTO> getSymbols() {
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>(null, headers);	
		
		String response = restTemplate.exchange(MessageFormat.format(symbolsurl, accessKey), 
				HttpMethod.GET, request, String.class).getBody();

		return mapSymbolsResponse((SymbolsResponse) fromJsonToDto(response, new SymbolsResponse()));
	}
	
	private Object fromJsonToDto(String json, Object object) {
		try {
			return objectMapper.readValue(json, object.getClass());
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			return object;
		}

	}
	
	private List<SymbolDTO> mapSymbolsResponse(SymbolsResponse symbolsResponse) {
		

		List<SymbolDTO> symbols = new ArrayList<>();
		
		Field[] declaredFields = Symbols.class.getDeclaredFields();
		
		try {
	        for(Field f:declaredFields){
	        	
	        	Field field;
				
					field = symbolsResponse.getSymbols().getClass().getDeclaredField(f.getName());
		        	field.setAccessible(true);
	
					Object value = field.get(symbolsResponse.getSymbols());
					
					symbols.add(new SymbolDTO(f.getName().toUpperCase(),value.toString()));
	        }
        
		} catch (NoSuchFieldException | SecurityException e) {
			log.error(e.getMessage());
		} 
		catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
						
		return symbols;
	}
	
}
