package com.ryan.hello.provider;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AliyunProvider {
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAInuH45wrxFaFy";//这里是你的授权KeyId
    private static String accessKeySecret = "7S21lOxv9Y7b0mfXwfugbpc4AVQLsb";//这里是你的授权秘钥
    private static String bucketName = "jryany";

    private static String folder = "nmqgh/";
    private static String key = "http://jryany.oss-cn-beijing.aliyuncs.com";



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
