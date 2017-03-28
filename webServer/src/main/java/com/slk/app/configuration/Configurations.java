package com.slk.app.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableRetry
@Configuration
public class Configurations {
	
	@Autowired
	Environment environment;
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	 private ClientHttpRequestFactory clientHttpRequestFactory() {
	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	        factory.setReadTimeout(600000);
	        factory.setConnectTimeout(600000);
	        factory.setConnectionRequestTimeout(600000);
	        return factory;
	    }
	 
	   @Bean
	    public RetryTemplate retryTemplate() {
	        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
	        retryPolicy.setMaxAttempts(5);

	        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
	        backOffPolicy.setBackOffPeriod(100); // 1.5 seconds

	        RetryTemplate template = new RetryTemplate();
	        template.setRetryPolicy(retryPolicy);
	        template.setBackOffPolicy(backOffPolicy);

	        return template;
	    }
	   
//	   @Bean
//		public FilterRegistrationBean corsFilter() {
//			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//			CorsConfiguration config = new CorsConfiguration();
//			//config.setAllowCredentials(true);
//			config.addAllowedOrigin("*");
//			config.addAllowedHeader("*");
//			config.addAllowedMethod("*");
//			source.registerCorsConfiguration("/**", config);
//			FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//			//bean.setOrder(0);
//			return bean;
//		}

//	@Bean
//	public org.springframework.data.redis.connection.RedisConnectionFactory redisConnectionFactory() {
//	//	org.springframework.data.redis.connection.jedis.JedisConnectionFactory jedisCF = new org.springframework.data.redis.connection.jedis.JedisConnectionFactory(this.getJedisPoolConfig());
//		org.springframework.data.redis.connection.jedis.JedisConnectionFactory jedisCF = new org.springframework.data.redis.connection.jedis.JedisConnectionFactory();
//		jedisCF.setHostName(environment.getProperty("redis.host"));
//		jedisCF.setPort(environment.getProperty("redis.port", int.class));
//	
//		if (null != environment.getProperty("redis.password") && !"".equals(environment.getProperty("redis.password"))) {
//			jedisCF.setPassword(environment.getProperty("redis.password"));
//		}
//		jedisCF.setTimeout(environment.getProperty("redis.timeout", int.class));
//		jedisCF.setUsePool(environment.getProperty("redis.usePool", boolean.class));
//		
//	    return jedisCF;
//	}
//
//	@Bean
//	public RedisTemplate<String, String> redisTemplate() {
//		System.out.println("redisTemplate initialize ");
//		 RedisTemplate< String, String > template =  new RedisTemplate< String, String>();
//		  template.setConnectionFactory( redisConnectionFactory() );
//		  template.setKeySerializer( new StringRedisSerializer() );
//	  	System.out.println("redisTemplate = " + template);
//	
//		 return template;
//	}
//	
//	public JedisPoolConfig getJedisPoolConfig(){
//		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//		jedisPoolConfig.setMaxTotal(200);
//		jedisPoolConfig.setMaxIdle(50);
//		jedisPoolConfig.setMinIdle(3000);
//		jedisPoolConfig.setTestOnBorrow(true);
//		return jedisPoolConfig;
//	}
	
	
	
	
	
}
