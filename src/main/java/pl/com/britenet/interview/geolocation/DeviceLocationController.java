package pl.com.britenet.interview.geolocation;

import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

@Controller("/locations")
@RequiredArgsConstructor
public class DeviceLocationController implements DeviceLocationOperation {

  private final DeviceLocationService coordinatesService;

  @Override
  public Single<DeviceLocation> addCoordinates(AddDeviceLocationRequest addCoordinatesRequest) {
    return coordinatesService.save(addCoordinatesRequest);
  }

  @Override
  public Publisher<DeviceLocation> getCoordinates(String deviceId) {
    return coordinatesService.find(deviceId);
  }
}