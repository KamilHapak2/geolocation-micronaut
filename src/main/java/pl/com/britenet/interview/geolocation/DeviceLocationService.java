package pl.com.britenet.interview.geolocation;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
class DeviceLocationService {

  private final DeviceLocationRepository deviceLocationRepository;
  private final DeviceLocationConverter converter;

  Single<DeviceLocationDetails> save(AddDeviceLocationRequest addCoordinatesRequest) {
    return deviceLocationRepository
        .save(converter.from(addCoordinatesRequest))
        .flatMap(
            deviceLocation ->
                Single.fromObservable(Observable.just(converter.from(deviceLocation))));
  }

  Publisher<DeviceLocationDetails> find(String deviceId) {
    return Observable.fromPublisher(deviceLocationRepository.find(deviceId))
        .map(converter::from)
        .toFlowable(BackpressureStrategy.LATEST);
  }
}
