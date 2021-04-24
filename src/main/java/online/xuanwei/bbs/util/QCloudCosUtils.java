package online.xuanwei.bbs.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@Component
public class QCloudCosUtils {
    @Value("${qcloud.client.secretId}")
    private String secretId;
    @Value("${qcloud.client.secretKey}")
    private String secretKey;
    @Value("${qcloud.client.region}")
    private String region;
    @Value("${qcloud.client.bucketName}")
    private String bucketName;
    @Value("${qcloud.client.url}")
    private String url;
    @Value("${qcloud.client.prefix}")
    private String prefix;

}
