package pl.com.britenet.interview.geolocation;

import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
class DeviceLocationService {

  private final DeviceLocationRepository deviceLocationRepository;
  private final DeviceLocationConverter converter;

  Single<DeviceLocation> save(AddDeviceLocationRequest addCoordinatesRequest) {
    return deviceLocationRepository.save(converter.from(addCoordinatesRequest));
  }

  Publisher<DeviceLocation> find(String deviceId) {
    return deviceLocationRepository.find(deviceId);
  }
}
