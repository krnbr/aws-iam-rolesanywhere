package in.neuw.aws.rolesanywhere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class S3Service {

    @Autowired
    S3Client s3Client;

    public Set<String> getBuckets() {
        return s3Client.listBuckets()
                .buckets().stream().map(b -> b.name()).collect(Collectors.toSet());
    }

}
