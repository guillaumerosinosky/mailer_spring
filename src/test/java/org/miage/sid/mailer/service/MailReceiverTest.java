package org.miage.sid.mailer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.miage.sid.mailer.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Config.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class MailReceiverTest {

    @Autowired
    MailReceiver mailReceiver;

    @Test
    @WithMockUser(roles = "USER")
    public void receiveTest() throws IOException, MessagingException {
        //
        assert(mailReceiver.getMails(10).size() == 10);

    }
}
