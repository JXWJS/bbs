package online.xuanwei.bbs.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import online.xuanwei.bbs.util.QCloudCosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class QCloudProvider {
    @Autowired
    QCloudCosUtils qCloudCosUtils;


    public String upload(MultipartFile multipartFile) {
        System.out.println(multipartFile);
        //生成唯一文件名
        String newFileName = generateUniqueName(multipartFile.getOriginalFilename());
        //文件在存储桶中的key
        String key = qCloudCosUtils.getPrefix()+newFileName;
        //声明客户端
        COSClient cosClient = null;
        //准备将MultipartFile类型转为File类型
        File file = null;
        try {
            //生成临时文件
            file = File.createTempFile("temp",null);
            //将MultipartFile类型转为File类型
            multipartFile.transferTo(file);
            //初始化用户身份信息(secretId,secretKey)
            COSCredentials cosCredentials = new BasicCOSCredentials(qCloudCosUtils.getSecretId(), qCloudCosUtils.getSecretKey());
            //设置bucket的区域
            ClientConfig clientConfig = new ClientConfig(new Region(qCloudCosUtils.getRegion()));
            //生成cos客户端
            cosClient = new COSClient(cosCredentials, clientConfig);
            //创建存储对象的请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(qCloudCosUtils.getBucketName(), key, file);
            //执行上传并返回结果信息
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return qCloudCosUtils.getUrl()+key;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
        return null;
    }

    /**
     * 根据UUID生成唯一文件名
     * @param originalName
     * @return
     */
    public String generateUniqueName(String originalName) {
        return UUID.randomUUID() + originalName.substring(originalName.lastIndexOf("."));
    }

}
