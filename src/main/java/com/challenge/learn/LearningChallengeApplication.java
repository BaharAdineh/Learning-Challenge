package com.challenge.learn;

import com.challenge.learn.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class LearningChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningChallengeApplication.class, args);
    }

}
