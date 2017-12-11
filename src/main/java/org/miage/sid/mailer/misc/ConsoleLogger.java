package org.miage.sid.mailer.misc;

import org.springframework.stereotype.Component;

@Component
public class ConsoleLogger implements Logger {
    @Override
    public void log(String str) {
        System.out.println(str);
    }
}
