package com.wallet.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    
    private static final String EMAIL = "email@teste.com";
    @Autowired
    UserRepository repository;

    @Before
    public void setUp(){
        User u = new User();
        u.setName("Set up User");
        u.setPassword("Senha123");
        u.setEmail(EMAIL);

        repository.save(u);

    }

    @After
    public void tearDown(){

        repository.deleteAll();

    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = repository.findByEmailEquals(EMAIL);

        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }

    @Test
    public void testSave(){
        User u = new User();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("teste@teste.com");

        User response = repository.save(u);

        assertNotNull(response);
    }
}
