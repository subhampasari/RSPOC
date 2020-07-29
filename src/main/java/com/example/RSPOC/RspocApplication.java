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

@SpringBootApplication
public class RspocApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RspocApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RspocApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS entities(" +
				"type VARCHAR(255), value VARCHAR(255), timestamp BIGINT)");

		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ref_markers(" +
				"refName VARCHAR(255), experience VARCHAR(255), feature VARCHAR(255), product VARCHAR(255), timestamp BIGINT)");
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
