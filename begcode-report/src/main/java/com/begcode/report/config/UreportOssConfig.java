package com.begcode.report.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.begcode.report.core.utils.ReportProperties;
import com.begcode.report.core.utils.oss.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.util.StringUtils;

@EnableScheduling
@Configuration
public class UreportOssConfig {

    @Autowired
    private ReportProperties reportProperties;

    @Bean
    public AmazonS3 getAmazonS3() {
        OssProperties oss = reportProperties.getOss();
        if (oss == null) {
            return null;
        }

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(oss.getMaxConnections());

        AWSCredentials awsCredentials = new BasicAWSCredentials(oss.getAccessKey(), oss.getSecretKey());
        ClientConfiguration baseOpts = new ClientConfiguration();

        baseOpts.setSignerOverride("S3SignerType");
        baseOpts.setProtocol(Protocol.HTTPS);

        AmazonS3ClientBuilder standard = AmazonS3ClientBuilder.standard();
        if (StringUtils.isEmpty(oss.getEndpoint())) {
            standard.withRegion(oss.getRegion());
        } else {
            standard.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(oss.getEndpoint(), oss.getRegion()));
        }

        return standard
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withClientConfiguration(baseOpts)
                .build();
    }

}
