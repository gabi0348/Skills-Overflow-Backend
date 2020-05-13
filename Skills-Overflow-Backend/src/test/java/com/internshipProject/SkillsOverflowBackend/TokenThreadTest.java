/*
package com.internshipProject.SkillsOverflowBackend;

import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.repositories.TokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.threads.TokenThread;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class TokenThreadTest {

    @MockBean
    private static TokenRepository tokenRepository;
    @MockBean
    private static UserRepository userRepository;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public TokenThread employeeService() {
            TokenThread tokenThread = new TokenThread();
            tokenThread.setTokenRepository(tokenRepository);
            tokenThread.setUserRepository(userRepository);
            return new TokenThread();
        }
    }

    @Autowired
    private TokenThread tokenThread;

    @Before
    public void setUp() {
        VerificationToken verificationToken = new VerificationToken(1L, "aa11", LocalDateTime.now().minusSeconds(10), null);
        User user1 = new User(1L, "gicu", "mail@mail",
                "parola", "gicu", "gix", false, verificationToken, null);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1));
        when(tokenRepository.findAll()).thenReturn(Arrays.asList(verificationToken));
    }

    @Test
    public void verifyDeletes() throws InterruptedException{
        tokenThread.start();
        Thread.sleep(50);

        verify(tokenRepository).delete(any(VerificationToken.class));
        verify(userRepository).delete(any(User.class));
    }

    @Test
    public void deleteTokenButNotUser() throws InterruptedException{
        userRepository.findAll().get(0).setEnabled(true);

        tokenThread.start();
        Thread.sleep(50);

        verify(tokenRepository).delete(any(VerificationToken.class));
        verify(userRepository, never()).delete(any(User.class));
    }


}*/
