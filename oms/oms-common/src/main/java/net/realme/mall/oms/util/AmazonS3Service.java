package net.realme.mall.oms.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.core.impl
 *
 * @author 91000044
 * @date 2018/8/13 14:39
 */
@Component
public class AmazonS3Service {

    @Value("${amazon.access.id}")
    private String accessKeyID;
    @Value("${amazon.secret.key}")
    private String secretKey;
    @Value("${amazon.s3.bucket.name}")
    private String bucketName;

    private AmazonS3 s3client;

    @PostConstruct
    public void init() {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKeyID,
                secretKey
        );
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }

    /**
     * 上传一个公共读权限的文件
     *
     * @param path
     * @param inputStream
     * @param contentType
     * @return
     */
    public PutObjectResult publicUpload(String path, InputStream inputStream, String contentType) {
        ObjectMetadata md = new ObjectMetadata();
        md.setContentType(contentType);
        md.setHeader("Cache-Control", "max-age=" + 7776000);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, inputStream, md);
        return s3client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
    }

    /**
     * 上传一个私有权限的文件
     *
     * @param path
     * @param inputStream
     * @param contentType
     * @return
     */
    public PutObjectResult privateUpload(String path, InputStream inputStream, String contentType) {
        ObjectMetadata md = new ObjectMetadata();
        md.setContentType(contentType);
        md.setHeader("Cache-Control", "max-age=" + 7776000);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, inputStream, md);
        return s3client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.Private));
    }
}
