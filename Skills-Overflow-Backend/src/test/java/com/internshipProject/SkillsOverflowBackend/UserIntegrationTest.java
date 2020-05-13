/*
package com.internshipProject.SkillsOverflowBackend;

import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.TokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;

import com.internshipProject.SkillsOverflowBackend.services.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.internshipProject.SkillsOverflowBackend.JsonUtil.toJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SkillsOverflowBackendApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private TokenService tokenService;

    static final Set<Role> roles = new HashSet<>();
    static {
        roles.add(new Role(1L, "admin"));
    }
    static final User user1 = new User(null,"me", "mail@mail", "first", "last", "string", false, null, roles);

    //unlike the unit tests, there are no layers mocked in integration tests; they all be real
    //https://mkyong.com/spring-boot/spring-test-how-to-test-a-json-array-in-jsonpath/
    @Test
    public void testPost() throws Exception {
        mvc.perform(post("/singUp")
                .content(toJson(user1))
                .contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //astea de aici doar dacă ÎNTORCI ceva din metoda din controller
//                .andExpect(jsonPath("$.userName").exists())
//                .andExpect(jsonPath("$.password").exists())
//                .andExpect(jsonPath("$.email").exists());

        Thread.sleep(500);
        List<User> found = userRepository.findAll();
        assertThat(found).extracting(User::getUserName).containsOnly("me");
    }

    @Test
    public void testRegistrationRequest() throws Exception {
        tokenService.createVerificationTokenForUser(user1, "baba");

        Thread.sleep(500);
        mvc.perform(get("/registrationConfirm?token=baba"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost:8081/users"));
    }

    @Test
    public void testNullPassword() throws Exception {
        user1.setPassword(null);

        mvc.perform(post("/singUp")
                .content(toJson(user1))
                .contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
*/
