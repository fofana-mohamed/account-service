package com.odds.accountservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local", "default"})
@ConfigurationProperties(prefix = "aws")
@Getter
@Setter
public class AwsConfig {

    private String region;
    private String accessKey;
    private String secretKey;

}
