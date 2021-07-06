package reference.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;

/**
 * Created by taeKwang.
 * FileName : RestTemplateConfig
 * Date: 2021-05-17
 * Time: 오전 9:15
 */
@Configuration
public class RestTemplateConfig {

	@Value("${api.timeout}")
    private int timeOut;
	
	@Value("${api.charset}")
    private String charSet;
	
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(timeOut)) // connection-timeout
                .setReadTimeout(Duration.ofMillis(timeOut)) // read-timeout
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName(charSet)))
                .build();
    }
}
