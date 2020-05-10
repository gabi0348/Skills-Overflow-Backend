package com.internshipProject.SkillsOverflowBackend;

import com.internshipProject.SkillsOverflowBackend.threads.TokenThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SkillsOverflowBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SkillsOverflowBackendApplication.class, args);
		TokenThread tokenThread = new TokenThread();
		context.getAutowireCapableBeanFactory().autowireBean(tokenThread);

		tokenThread.start();
	}

}
