package itw.oralboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OralApplication {

	public static void main(String[] args) {
		SpringApplication.run(OralApplication.class, args);
		Logger logger = LoggerFactory.getLogger(OralApplication.class);
		logger.info("--------------------项目已启动--------------------");
	}

}