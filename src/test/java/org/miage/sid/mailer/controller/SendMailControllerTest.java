package org.miage.sid.mailer.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miage.sid.mailer.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SendMailControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private MailSender mailSender;

    @Before
    public void setup() {
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGet() throws Exception {
        this.mvc.perform(get("/sendmail"))
                .andExpect(view().name("send_form"));

    }

    @Test
    @WithAnonymousUser
    public void testGetFormNotAuthorized() throws Exception {
        this.mvc.perform(get("/sendmail"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithMockUser(roles = "USER")
    public void testForm() throws Exception {
        doNothing().when(mailSender).sendMail("test@test.com", "test sub", "pas content");
        this.mvc.perform(post("/sendmail")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("to", "test@test.com")
                .param("subject", "test sub")
                .param("content", "pas content"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testNonValidForm() throws Exception {
        // https://www.dontpanicblog.co.uk/2013/11/14/mockmvc-to-test-spring-mvc-form-validation/
        doNothing().when(mailSender).sendMail("test@test.com", "test sub", "pas content");
        this.mvc.perform(post("/sendmail")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("to", "")
                .param("subject", "")
                .param("content", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("send_form"))
                .andExpect(model().attributeHasFieldErrors("sendFormBean", "to", "subject"))
                .andDo(print());
    }
}
