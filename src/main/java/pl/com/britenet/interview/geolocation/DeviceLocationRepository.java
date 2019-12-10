package pl.com.britenet.interview.geolocation;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

import static com.mongodb.client.model.Filters.eq;

@Singleton
@RequiredArgsConstructor
class DeviceLocationRepository {

  private final MongoConfiguration configuration;

  private final MongoClient mongoClient;

  private MongoCollection<DeviceLocation> getCollection() {
    return mongoClient
            .getDatabase(configuration.getDatabase())
            .getCollection(configuration.getCoordinatesCollection(), DeviceLocation.class);
  }

  Single<DeviceLocation> save(DeviceLocation coordinates) {
    return Single.fromPublisher(getCollection().insertOne(coordinates)).map(s -> coordinates);
  }

  Publisher<DeviceLocation> find(String deviceId) {
    return getCollection().find(
            eq("deviceId", deviceId));
  }
}
