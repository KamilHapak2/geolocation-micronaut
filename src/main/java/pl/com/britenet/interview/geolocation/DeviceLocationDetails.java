package pl.com.britenet.interview.geolocation;

import lombok.NonNull;
import lombok.Value;

@Value
public class DeviceLocationDetails {

  @NonNull private final String deviceId;
  private final long timestamp;
  private final double latitude;
  private final double longitude;
}
