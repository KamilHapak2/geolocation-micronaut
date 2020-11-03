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
  public Single<DeviceLocationDetails> addCoordinates(
      AddDeviceLocationRequest addCoordinatesRequest) {
    return coordinatesService.save(addCoordinatesRequest);
  }

  @Override
  public Publisher<DeviceLocationDetails> getCoordinates(String A) {
    return coordinatesService.find(A);
  }
}
