package org.miage.sid.mailer.controller;

import org.miage.sid.mailer.misc.Mail;
import org.miage.sid.mailer.service.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping("/sendmail")
@SessionAttributes("sendFormBean")
public class SendMailController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailSender mailSender;

    @ModelAttribute("sendFormBean")
    public Mail createSendFormBean(){
        Mail mail = new Mail();

        return mail;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String form(Locale locale){
        log.info("Locale :" + locale);
        return "send_form";
    }

    @RequestMapping(method= RequestMethod.POST)
    public String submit(@ModelAttribute("sendFormBean") @Valid Mail sendFormBean, BindingResult result, Model model, RedirectAttributes redirectAttrs){
        log.info("submit");
        if (result.hasErrors()) {
            log.info("Error in mail " + result.getAllErrors());
            return "send_form";
        }

        mailSender.sendMail(
                sendFormBean.getTo().get(0),
                sendFormBean.getSubject(),
                sendFormBean.getContent()
        );
        String message = "Mail envoyé";
        redirectAttrs.addFlashAttribute("message", message);
        return "redirect:/";

    }


    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model model) {
        log.error("Error while sending", ex);
        model.addAttribute("error", ex);
        model.addAttribute("sendFormBean", new Mail());
        return "send_form";
    }
}
