package com.odds.accountservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClientBuilder;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

@Service
@Slf4j
public class AwsConfigService {

    private final AwsConfig awsConfig;

    @Value("${aws.datasource.secrets-manager.secret-arn}")
    private String secretArn;

    @Value("${aws.datasource.url}")
    private String url;

    private String accessKey = "";
    private String secretKey = "";
    private String region = "";

    @Autowired
    public AwsConfigService(AwsConfig awsConfig) {
        this.awsConfig = awsConfig;

        this.accessKey = awsConfig.getAccessKey();
        this.secretKey = awsConfig.getSecretKey();
        this.region = awsConfig.getRegion();
    }

    public AwsBasicCredentials awsBasicCredentials() {
        return AwsBasicCredentials.create(
                accessKey,
                secretKey
        );
    }

    @Bean
    public DataSource dataSource() throws Exception {

        SecretsManagerClient secretsManagerClient = SecretsManagerClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials()))
                .build();

        SecretsManagerClientBuilder secretsManagerClientBuilder = SecretsManagerClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials()));

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretArn)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = secretsManagerClient.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            log.error("Failed to retrieve database secrets. Region: {}, AccessKey: {}, SecretKey: {}. {}", region, accessKey, secretKey, e);
            throw new Exception("Failed to retrieve database secrets");
        }

        String secretString = getSecretValueResponse.secretString();
        JSONObject secret = new JSONObject(secretString);
        String username = secret.getString("username");
        String password = secret.getString("password");


        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();

    }

}
