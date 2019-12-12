package pl.com.britenet.interview.geolocation;

import io.micronaut.context.annotation.Prototype;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Clock;

@Prototype
@RequiredArgsConstructor
public class DeviceLocationConverter {

  public static final int SCALE = 4;

  private final Clock clock;

  DeviceLocation from(AddDeviceLocationRequest addDeviceLocationRequest) {
    return DeviceLocation.of(
        clock.millis(),
        addDeviceLocationRequest.getDeviceId(),
        Longitude.of(toDoubleValue(addDeviceLocationRequest.getLongitude())),
        Latitude.of(toDoubleValue(addDeviceLocationRequest.getLatitude())));
  }

  private double toDoubleValue(Long value) {
    return BigDecimal.valueOf(value, SCALE).doubleValue();
  }
}
