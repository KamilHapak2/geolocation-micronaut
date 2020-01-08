package pl.com.britenet.interview.geolocation;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;
import org.reactivestreams.Publisher;

public interface DeviceLocationOperation {

  @Post
  Single<DeviceLocationDetails> addCoordinates(@Body AddDeviceLocationRequest addCoordinates);

  @Get("/{deviceId}")
  Publisher<DeviceLocationDetails> getCoordinates(String deviceId);
}
