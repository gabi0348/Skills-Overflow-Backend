package com.internshipProject.SkillsOverflowBackend;

//import com.internshipProject.SkillsOverflowBackend.threads.ResetPasswordTokenThread;
//import com.internshipProject.SkillsOverflowBackend.threads.VerificationTokenThread;
import com.internshipProject.SkillsOverflowBackend.threads.BlockedUserTokenThread;
import com.internshipProject.SkillsOverflowBackend.threads.ResetPasswordTokenThread;
import com.internshipProject.SkillsOverflowBackend.threads.VerificationTokenThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SkillsOverflowBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SkillsOverflowBackendApplication.class, args);


		VerificationTokenThread verificationTokenThread = new VerificationTokenThread();
		context.getAutowireCapableBeanFactory().autowireBean(verificationTokenThread);

		ResetPasswordTokenThread resetPasswordTokenThread = new ResetPasswordTokenThread();
		context.getAutowireCapableBeanFactory().autowireBean(resetPasswordTokenThread);

		BlockedUserTokenThread blockedUserTokenThread = new BlockedUserTokenThread();
		context.getAutowireCapableBeanFactory().autowireBean(blockedUserTokenThread);



		verificationTokenThread.start();
		resetPasswordTokenThread.start();
		blockedUserTokenThread.start();

	}

}
