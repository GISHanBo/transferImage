package transferImage;

public class ImageData {
  private String name;
  private String path;
  private String desc;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public String toString() {
    return "ImageData{" +
        "name='" + name + '\'' +
        ", path='" + path + '\'' +
        ", desc='" + desc + '\'' +
        '}';
  }
}
