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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.wallet.entity.UserData;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class UserRepositoryTest {
    
    private static final String EMAIL = "email@teste.com";
    @Autowired
    UserRepository repository;

    @Before
    public void setUp(){
        UserData u = new UserData();
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
        Optional<UserData> response = repository.findByEmailEquals(EMAIL);

        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }

    @Test
    public void testSave(){
        UserData u = new UserData();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("teste@teste.com");

        UserData response = repository.save(u);

        assertNotNull(response);
    }
}
