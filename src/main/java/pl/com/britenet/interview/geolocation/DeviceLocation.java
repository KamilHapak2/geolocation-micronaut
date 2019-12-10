package pl.com.britenet.interview.geolocation;

import lombok.NonNull;
import lombok.Value;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Value
public class DeviceLocation {

  private long timestamp;
  @NonNull
  private String deviceId;
  @NonNull
  private Longitude longitude;
  @NonNull
  private Latitude latitude;

  @BsonCreator
  public static DeviceLocation of(
          @BsonProperty("timestamp") long timestamp,
          @BsonProperty("deviceId") String deviceId,
          @BsonProperty("longitude") Longitude longitude,
          @BsonProperty("latitude") Latitude latitude) {
    return new DeviceLocation(timestamp, deviceId, longitude, latitude);
  }
}
