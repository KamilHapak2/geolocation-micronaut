package pl.com.britenet.interview.geolocation;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;
import org.reactivestreams.Publisher;

// todo return DeviceLocationDetails
public interface DeviceLocationOperation {

  @Post
  Single<DeviceLocation> addCoordinates(@Body AddDeviceLocationRequest addCoordinates);

  @Get("/{deviceId}")
  Publisher<DeviceLocation> getCoordinates(String deviceId);
}
