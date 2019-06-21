package transferImage;

import transferImage.oss.MyOSS;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    connectPost();

  }

  private static void connectPost() {

    //picbasepath, bdzlt,xczp, qztp,ywyqz;

    Connection c = null;
    Statement stmt = null;
    try {

      Class.forName("org.postgresql.Driver");
      c = DriverManager
          .getConnection("jdbc:postgresql://192.168.1.71:5432/agriinsurance",
              "postgres", "postgresql");
      c.setAutoCommit(false);
      stmt = c.createStatement();
      stmt.setFetchSize(50);
      // 读取数据
      ResultSet rs = stmt.executeQuery("select picbasepath, bdzlt,xczp, qztp,ywyqz from yw_cbgcxxb WHERE picbasepath is not null");
      MyOSS myOss = new MyOSS();
      long index = 0;
      while (rs.next()) {
        index++;
        RowData rowData = new RowData();
        rowData.setPicBasePath(rs.getString("picbasepath"));
        rowData.setBdzlt(rs.getString("bdzlt"));
        rowData.setXczp(rs.getString("xczp"));
        rowData.setQztp(rs.getString("qztp"));
        rowData.setYwyqz(rs.getString("ywyqz"));
        //全为null,不加入计算
        if (!rowData.isNull()) {
          upFile(myOss,rowData);
        }
        System.out.println(index);
      }
      rs.close();
      stmt.close();
      c.close();
      myOss.close();

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  public static void upFile(MyOSS myOss, RowData r) {
    String baseUrl = r.getPicBasePath();
    List<ImageData> imageDataList = r.getImages();
    for (ImageData i : imageDataList) {
      String key = i.getPath();
      //上传文件
//      if (getRource(baseUrl + key)) {
//        try {
//          myOss.upFile(key, baseUrl + key);
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
      //输出url
      URL url=myOss.generateUrl(key);
      System.out.println(url);
    }
  }


  public static boolean getRource(String source) {
    try {
      URL url = new URL(source);
      URLConnection uc = url.openConnection();
      InputStream in = uc.getInputStream();
      if (source.equalsIgnoreCase(uc.getURL().toString())) {
        in.close();
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
