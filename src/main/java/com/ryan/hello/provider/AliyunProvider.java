package com.ryan.hello.provider;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AliyunProvider {
   



    public String uploadImageToOSS(String fileName, InputStream inputStream) {
        /**
         * 创建OSS客户端
         */
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String[] names = fileName.split("[.]");
            String name = uuid + "." + names[names.length - 1];

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(names[names.length - 1]);
            objectMetadata.setContentDisposition("inline;filename=" + fileName);


            ossClient.putObject(new PutObjectRequest(bucketName, folder + name, inputStream, objectMetadata));
            return key + folder + name;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return null;

    }

}
