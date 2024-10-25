package in.neuw.aws.rolesanywhere.controller;

import in.neuw.aws.rolesanywhere.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(final S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/api/buckets")
    public Set<String> test() {
        return s3Service.getBuckets();
    }

}
