package transferImage;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RowData {
  private String picBasePath;
  private String bdzlt;
  private String xczp;
  private String qztp;
  private String ywyqz;


  public String getPicBasePath() {
    return picBasePath;
  }

  public void setPicBasePath(String picBasePath) {
    this.picBasePath = picBasePath;
  }

  public String getBdzlt() {
    return bdzlt;
  }

  public void setBdzlt(String bdzlt) {
    this.bdzlt = bdzlt;
  }

  public String getXczp() {
    return xczp;
  }

  public void setXczp(String xczp) {
    this.xczp = xczp;
  }

  public String getQztp() {
    return qztp;
  }

  public void setQztp(String qztp) {
    this.qztp = qztp;
  }

  public String getYwyqz() {
    return ywyqz;
  }

  public void setYwyqz(String ywyqz) {
    this.ywyqz = ywyqz;
  }

  public boolean isNull(){
    return StringUtils.isBlank(bdzlt) && StringUtils.isBlank(xczp) && StringUtils.isBlank(qztp) && StringUtils.isBlank(ywyqz);
  }


  public List<ImageData> getImages(){
    List<ImageData> list=new ArrayList<>();
    if(StringUtils.isNotBlank(bdzlt)){
      List<ImageData> listB=(List<ImageData>)JSONArray.toList(JSONArray.fromObject(bdzlt), ImageData.class);
      list.addAll(listB);
    }
    if(StringUtils.isNotBlank(xczp)){
      List<ImageData> listB=(List<ImageData>)JSONArray.toList(JSONArray.fromObject(xczp), ImageData.class);
      list.addAll(listB);
    }
    if(StringUtils.isNotBlank(qztp)){
      List<ImageData> listB=(List<ImageData>)JSONArray.toList(JSONArray.fromObject(qztp), ImageData.class);
      list.addAll(listB);
    }
    if(StringUtils.isNotBlank(ywyqz)){
      List<ImageData> listB=(List<ImageData>)JSONArray.toList(JSONArray.fromObject(ywyqz), ImageData.class);
      list.addAll(listB);
    }
    return list;
  }

  @Override
  public String toString() {
    return "RowData{" +
        "picBasePath='" + picBasePath + '\'' +
        ", bdzlt='" + bdzlt + '\'' +
        ", xczp='" + xczp + '\'' +
        ", qztp='" + qztp + '\'' +
        ", ywyqz='" + ywyqz + '\'' +
        '}';
  }
}
