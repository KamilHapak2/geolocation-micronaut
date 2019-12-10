package pl.com.britenet.interview.geolocation;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("geolocation")
@Getter
@Setter
class MongoConfiguration {

  private String database = "geolocation";
  private String coordinatesCollection = "deviceLocations";
}
