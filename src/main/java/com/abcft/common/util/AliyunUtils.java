package com.abcft.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;

public class AliyunUtils {
	
	private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAITN0hCn7KBUzK";
    private static String accessKeySecret = "c8SOHjg15bkkW3AxQmbDyyDQA8fnNI";
    private static String bucketName = "abc-crawler";
    private static String firstKey = "backend";

	/**
	 * 向阿里云服务器写入文件
	 * @param inputStream
	 * @return 返回码
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static int doPost(InputStream inputStream,File file) throws FileNotFoundException, IOException {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {
            // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
        	PutObjectResult result=ossClient.putObject(bucketName, "backend/" + file.getName(), file);
            System.out.println("Object：" + firstKey + "存入OSS成功。");
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return -1;
	}
	
    public static void main(String[] args) {

        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {
            // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
        	PutObjectResult result=ossClient.putObject(bucketName, "backend/1.pdf", new File("e://1.pdf"));
            System.out.println("Object：" + firstKey + "存入OSS成功。");
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
