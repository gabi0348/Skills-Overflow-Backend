package com.internshipProject.SkillsOverflowBackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SkillsOverflowBackendApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;


    static final Set<Role> roles = new HashSet<>();
    static {
        roles.add(new Role(1L, "admin"));
    }
    static final User user1 = new User("me", "mail@mail", "first", "last", "string", false, null, roles);

    //unlike the unit tests, there is nothing mocked in integration tests
    @Test
    public void testPost() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user1);

        mvc.perform(post("/singUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        List<User> found = userRepository.findAll();
        assertThat(found).extracting(User::getUserName).containsOnly("me");
    }
}
