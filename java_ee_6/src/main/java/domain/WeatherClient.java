package domain;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class WeatherClient {

  public static final String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather";
  public static final String API_KEY = "KLUCZ_API";

  public Weather getByName(String name){
    Response response = ClientBuilder
      .newClient()
      .target(WEATHER_API)
      .queryParam("q", name)
      .queryParam("units", "metric")
      .queryParam("appid", API_KEY)
      .request()
      .get();

    if(response.getStatus() == 200) {
      return response.readEntity(Weather.class);
    }

    return null;
  }




}
