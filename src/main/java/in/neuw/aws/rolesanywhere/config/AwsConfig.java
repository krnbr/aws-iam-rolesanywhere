package in.neuw.aws.rolesanywhere.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.neuw.aws.rolesanywhere.credentials.IAMRolesAnywhereSessionsCredentialsProvider;
import in.neuw.aws.rolesanywhere.config.props.AwsRolesAnywhereProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider(final AwsRolesAnywhereProperties awsRolesAnywhereProperties,
                                                         final ObjectMapper objectMapper) {
        var rolesAnywhereCredentialsProvider = new IAMRolesAnywhereSessionsCredentialsProvider
                .Builder(awsRolesAnywhereProperties, objectMapper)
                .asyncCredentialUpdateEnabled(true)
                .build();
        return rolesAnywhereCredentialsProvider;
    }

    @Bean
    public AwsCredentialsProvider awsCredentialsProviderV2(final AwsRolesAnywhereProperties awsRolesAnywhereProperties,
                                                           final ObjectMapper objectMapper) {
        // intention of this one is when you do not want to initiate the provider strictly based on Spring props.
        // accordingly - one may remove spring related properties files.
        // presently the provider makes use of RestClient which is Spring based,
        // a slight change on it can help to use other non-spring HttpClients
        // provider makes use of fasterXml's ObjectMapper for object to json
        var rolesAnywhereCredentialsProvider = new IAMRolesAnywhereSessionsCredentialsProvider
                .Builder(objectMapper)
                .roleArn(awsRolesAnywhereProperties.getRoleArn())
                .profileArn(awsRolesAnywhereProperties.getProfileArn())
                .trustAnchorArn(awsRolesAnywhereProperties.getTrustAnchorArn())
                .encodedPrivateKey(awsRolesAnywhereProperties.getEncodedPrivateKey())
                .encodedX509Certificate(awsRolesAnywhereProperties.getEncodedX509Certificate())
                .durationSeconds(awsRolesAnywhereProperties.getDurationSeconds())
                .region(awsRolesAnywhereProperties.getRegion())
                .asyncCredentialUpdateEnabled(true)
                .prefetch(true)
                .build();
        return rolesAnywhereCredentialsProvider;
    }

    @Bean
    S3Client s3Client(final AwsCredentialsProvider awsCredentialsProvider,
                      final AwsRolesAnywhereProperties awsRolesAnywhereProperties) {
        return S3Client.builder().credentialsProvider(awsCredentialsProvider).region(Region.of(awsRolesAnywhereProperties.getRegion())).build();
    }

}
