package pl.com.britenet.interview.geolocation;

import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.time.Clock;

@Singleton
@RequiredArgsConstructor
class DeviceLocationService {

  public static final int SCALE = 4;
  private final DeviceLocationRepository deviceLocationRepository;
  private final Clock clock;

  Single<DeviceLocation> save(AddDeviceLocationRequest addCoordinatesRequest) {

    DeviceLocation deviceLocation =
            DeviceLocation.of(
                    clock.millis(),
                    addCoordinatesRequest.getDeviceId(),
                    Longitude.of(toDoubleValue(addCoordinatesRequest.getLongitude())),
                    Latitude.of(toDoubleValue(addCoordinatesRequest.getLatitude())));

    return deviceLocationRepository.save(deviceLocation);
  }

  private double toDoubleValue(Long value) {
    return BigDecimal.valueOf(value, SCALE).doubleValue();
  }

  Publisher<DeviceLocation> find(String deviceId) {
    return deviceLocationRepository.find(deviceId);
  }
}
