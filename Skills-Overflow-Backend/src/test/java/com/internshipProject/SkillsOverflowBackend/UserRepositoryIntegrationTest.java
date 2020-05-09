//package com.internshipProject.SkillsOverflowBackend;
//
//import com.internshipProject.SkillsOverflowBackend.models.Role;
//import com.internshipProject.SkillsOverflowBackend.models.User;
//import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserRepositoryIntegrationTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void whenFindByName_thenReturnUser() {
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1L, "user"));
//        User deliric = new User(1L, "deliric", "mail", "password", "deliric", "n-am", null, null, roles);
//        entityManager.persist(deliric);
//        entityManager.flush();
//
//        User found = UserRepository.findByName(alex.getName());
//
//        assertThat(found.getName())
//                .isEqualTo(alex.getName());
//    }
//}