package com.example.RSPOC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

@SpringBootApplication
public class RspocApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RspocApplication.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RspocApplication.class);
//		SpringApplication.run(RspocApplication.class, args);
		Properties properties = new Properties();
		properties.put("spring.datasource.driver-class-name", "com.amazon.redshift.jdbc42.Driver");
		properties.put("spring.datasource.url", "jdbc:redshift://redshift-cluster-1.cxjtloxikgod.us-east-1.redshift.amazonaws.com:5439/dev");
		properties.put("spring.datasource.username", "awsuser");
		properties.put("spring.datasource.password", "AWSuser1");
		properties.put("spring.jpa.show-sql", "true");
		properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
		application.setDefaultProperties(properties);

		application.run(args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS entities(" +
				"type VARCHAR(255), value VARCHAR(255), timestamp BIGINT," +
				"CONSTRAINT unique_combo UNIQUE (type, value))");

		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ref_markers(" +
				"refName VARCHAR(255) PRIMARY KEY, experience VARCHAR(255), feature VARCHAR(255), product VARCHAR(255), timestamp BIGINT)");
	}

	@Configuration
	@EnableWebMvc
	public class WebConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**");
		}
	}
}