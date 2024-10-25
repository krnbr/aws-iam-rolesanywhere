package in.neuw.aws.rolesanywhere;

import in.neuw.aws.rolesanywhere.config.props.AwsRolesAnywhereProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AwsRolesAnywhereProperties.class})
public class AwsRolesAnywhereApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsRolesAnywhereApplication.class, args);
    }

}
