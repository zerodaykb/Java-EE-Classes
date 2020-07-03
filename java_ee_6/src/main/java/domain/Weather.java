package domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weather {

  private Clouds clouds;
  private Main main;
  private Wind wind;

  public Clouds getClouds () {
    return clouds;
  }

  public void setClouds (Clouds clouds) {
    this.clouds = clouds;
  }

  public Main getMain () {
    return main;
  }

  public void setMain (Main main) {
    this.main = main;
  }

  public Wind getWind () {
    return wind;
  }

  public void setWind (Wind wind) {
    this.wind = wind;
  }
}
