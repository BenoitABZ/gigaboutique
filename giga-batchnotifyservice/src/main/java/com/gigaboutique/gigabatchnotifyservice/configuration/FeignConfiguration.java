package com.gigaboutique.gigabatchnotifyservice.configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class FeignConfiguration {

	@Autowired
	SecurityConstant sc;

	@Bean
	AuthInterceptor authFeign() {
		return new AuthInterceptor();
	}

	class AuthInterceptor implements RequestInterceptor {

		@Override
		public void apply(RequestTemplate template) {

			long expiration = Long.parseLong(sc.getExpiration());
			String secret = sc.getSecret();
			String tokenPrefix = sc.getTokenPrefix();
			String header = sc.getHeader();

			List<String> roles = new ArrayList<>();

			roles.add("USER");

			String token = Jwts.builder().setSubject("fromBatchNotify")
					.setExpiration(new Date(System.currentTimeMillis() + expiration))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes()).claim("roles", roles).compact();

			template.header(header, tokenPrefix + "" + token);

		}

	}

	@Bean
	public Decoder feignDecoder() {
		return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
	}

	public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
		final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(
				new PhpMappingJackson2HttpMessageConverter());
		return new ObjectFactory<HttpMessageConverters>() {
			@Override
			public HttpMessageConverters getObject() throws BeansException {
				return httpMessageConverters;
			}
		};
	}

	public class PhpMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
		PhpMappingJackson2HttpMessageConverter() {

			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.registerModule(new JavaTimeModule());

			this.setObjectMapper(objectMapper);

			List<MediaType> mediaTypes = new ArrayList<>();
			mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
			mediaTypes.add(MediaType.APPLICATION_JSON);

			setSupportedMediaTypes(mediaTypes);
		}
	}
}
