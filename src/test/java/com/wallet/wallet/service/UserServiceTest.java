package com.wallet.wallet.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.wallet.entity.UserData;
import com.wallet.wallet.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class UserServiceTest {
    
    @MockBean
    UserRepository repository;

    @Autowired
    UserService service;

    @Before
    public void setUp(){
        BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new UserData()));
    }

    @Test
    public void testFindByEmail(){
        Optional<UserData> user = service.findByEmail("email@teste.com");
        assertTrue(user.isPresent());
    }
}
