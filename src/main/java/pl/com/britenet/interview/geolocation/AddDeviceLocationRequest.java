package pl.com.britenet.interview.geolocation;

import lombok.NonNull;
import lombok.Value;

@Value
public class AddDeviceLocationRequest {

  @NonNull
  private String deviceId;
  @NonNull
  private Long longitude;
  @NonNull
  private Long latitude;
}
