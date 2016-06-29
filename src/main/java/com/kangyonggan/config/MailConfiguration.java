package com.kangyonggan.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author kangyonggan
 * @since 16/6/29
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class MailConfiguration {

    @Getter
    private List<String> prop = new ArrayList();

    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(prop.get(5));
        mailSender.setPort(Integer.parseInt(prop.get(6)));
        mailSender.setUsername(prop.get(7));
        mailSender.setPassword(prop.get(8));
        mailSender.setDefaultEncoding("UTF-8");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", prop.get(9));
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }

}
