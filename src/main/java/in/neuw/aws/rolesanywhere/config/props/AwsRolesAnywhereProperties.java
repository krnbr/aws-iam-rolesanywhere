package in.neuw.aws.rolesanywhere.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aws.roles.anywhere")
public class AwsRolesAnywhereProperties implements InitializingBean {

    private String roleArn;
    private String profileArn;
    private String trustAnchorArn;
    private String region;
    private Integer durationSeconds;
    private String roleSessionName;
    private String encodedX509Certificate;
    private String encodedPrivateKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO validations
    }

}
