package io.elasticsearch.awsconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class AWSCredentialsConfiguration {

    @Value("${aws.es.accessKeyId:}")
    private String accessKeyId = null;

    @Value("${aws.es.secretKey:}")
    private String esSecretKey = null;

    @Bean
    public AWSStaticCredentialsProvider awsDynamoCredentialsProviderDevelopment() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(
        		accessKeyId, esSecretKey));
    }
}
