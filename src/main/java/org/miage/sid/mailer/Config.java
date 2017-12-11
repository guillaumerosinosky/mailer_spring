package org.miage.sid.mailer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Configuration
@ImportResource("classpath:beans.xml")
@ComponentScan(basePackages={"org.miage.sid.mailer"})
public class Config {

}
