package reference.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * @fileName : RestApiUtil.java 
 * @author : mkahn
 * @date : 2021.07.02 
 **/
@Component
public class RestApiUtil {
	private Logger logger = LoggerFactory.getLogger(RestApiUtil.class);
	
	private HttpHeaders getHeader(Map<String, String> map) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		for( String key : map.keySet() ){
			headers.add(key, map.get(key));
        }
		return headers;
	}
	
	private HttpHeaders getDefaultHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		
		return headers;
	}
	
	public ResponseEntity<String> callRestApi(String url, String rst, HttpMethod method) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = null;
		try {
			HttpEntity entity = new HttpEntity(rst, getDefaultHeader());
			logger.info("Request RestApi : " + entity.toString());
			res = restTemplate.exchange(url, method, entity, String.class);
			logger.info("OK. Response RestApi : " + res.toString());
		} catch(HttpStatusCodeException e) { 
			logger.info("NOK. Response RestApi : " + e.toString());
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()) .body(e.getResponseBodyAsString()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
