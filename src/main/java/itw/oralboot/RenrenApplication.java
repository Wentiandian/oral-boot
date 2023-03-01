package itw.oralboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class RenrenApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenrenApplication.class, args);
		Logger logger = LoggerFactory.getLogger(RenrenApplication.class);
		logger.info("..........项目已启动..........");
	}

}