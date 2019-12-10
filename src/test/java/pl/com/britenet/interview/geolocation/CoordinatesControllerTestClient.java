package pl.com.britenet.interview.geolocation;

import io.micronaut.http.client.annotation.Client;

@Client("/locations")
public interface CoordinatesControllerTestClient extends DeviceLocationOperation {
}
