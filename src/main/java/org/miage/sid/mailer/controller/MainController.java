package org.miage.sid.mailer.controller;

import org.miage.sid.mailer.misc.Mail;
import org.miage.sid.mailer.service.MailReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailReceiver mailReceiver;

    @RequestMapping(value={"/","/getmail"})
    public String liste(Map<String, Object> model) throws IOException, MessagingException {
        List<Mail> mails = mailReceiver.getMails(10);
        model.put("mails", mails);
        return "liste";
    }
    @ExceptionHandler
    public String error(Map<String, Object> model, Exception e) {
        model.put("error", e);
        return "liste";
    }

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }
}
