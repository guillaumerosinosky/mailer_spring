package org.miage.sid.mailer.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miage.sid.mailer.Config;
import org.miage.sid.mailer.SecurityConfig;
import org.miage.sid.mailer.persistence.User;
import org.miage.sid.mailer.persistence.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;


import javax.persistence.EntityManager;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DataJpaTest
@ContextConfiguration(classes={Config.class, SecurityConfig.class})
public class MailerApplicationTests {
    @Autowired
    private WebApplicationContext context;

    @Autowired
	MockMvc mockMvc;

	@Autowired
    private TestEntityManager entityManager;

	@Before
    public void init() {
        User user = new User();
        user.setPassword("password2");
        user.setUsername("user2");
        entityManager.persist(user);
        UserRole role = new UserRole();
        role.setRole("ROLE_USER");
        role.setUser(user);
        entityManager.persist(role);
        entityManager.flush();

    }
	@Test
	public void loginTest() throws Exception {
		RequestBuilder requestBuilder = formLogin().user("user2").password("password2");
		mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
	}

	@Test
	public void loginErrorTest() throws Exception {
		RequestBuilder requestBuilder = formLogin().user("user").password("pas_password");
		mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(status().isFound());

	}

}
