package coop.home.backacount.externalresource.rest.exchangerates.rates;


import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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

import coop.home.backacount.dto.acouttransfer.RatesDTO;
import coop.home.backacount.externalresource.rest.ExchangeratesClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
class ExchangeratesClientImpl implements ExchangeratesClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Value("${exchangerates.access_key}")
	private String accessKey;
	
	@Value("${exchangerates.latest.url}")
	private String url;
	
	public RatesDTO getRates() {
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>(null, headers);	
		
		String response = restTemplate.exchange(MessageFormat.format(url, accessKey), 
				HttpMethod.GET, request, String.class).getBody();
		
		RatesResponse ratesResponse = (RatesResponse) fromJsonToDto(response, new RatesResponse());
		
		return this.mapSymbolsResponse(ratesResponse);
	}
	
	private Object fromJsonToDto(String json, Object object) {
		try {
			return objectMapper.readValue(json, object.getClass());
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			return object;
		}
	}
	
	private RatesDTO mapSymbolsResponse(RatesResponse ratesResponse) {
		

		HashMap<String ,Double> rates = new HashMap<String, Double>();
		
		Field[] declaredFields = Rates.class.getDeclaredFields();
		
		try {
	        for(Field f:declaredFields){
	        	
	        	Field field;
				
					field = ratesResponse.getRates().getClass().getDeclaredField(f.getName());
		        	field.setAccessible(true);
	
					Object value = field.get(ratesResponse.getRates());
					
					rates.put(f.getName().toUpperCase(), Double.valueOf(value.toString()));
	        }
        
		} catch (NoSuchFieldException | SecurityException e) {
			log.error(e.getMessage());
		} 
		catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		try {
			date = simpleDateFormat.parse(ratesResponse.getDate());
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
						
		return new RatesDTO(date, ratesResponse.base, rates);
	}
	
}
