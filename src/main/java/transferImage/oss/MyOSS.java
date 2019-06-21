package transferImage.oss;

import com.aliyun.oss.OSSClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class MyOSS {
  String endpoint = "http://oss-cn-zhangjiakou.aliyuncs.com";
  // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
  String accessKeyId = "*";
  String accessKeySecret = "*";
  String bucketName = "*";

//  String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//  // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//  String accessKeyId = "*";
//  String accessKeySecret = "*";
//  String bucketName = "*";
  private OSSClient ossClient;

  public MyOSS() {
    initOSS();
  }

  private void initOSS() {
   // 创建OSSClient实例。
    ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
  }

  public void upFile(String objectName,String url) throws IOException {
    // 上传网络流。
    InputStream inputStream = new URL(url).openStream();
    ossClient.putObject(bucketName, objectName, inputStream);  }

  public void close() {
    // 关闭OSSClient。
    ossClient.shutdown();
  }

  public URL generateUrl(String key){
    Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
    URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
    return url;
  }
}
