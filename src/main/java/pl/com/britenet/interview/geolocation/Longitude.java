package pl.com.britenet.interview.geolocation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Longitude {

  private static final Double MAX_VALUE = 180.0;
  private static final Double MIN_VALUE = -180.0;

  private final Double value;

  @BsonCreator
  public static Longitude of(@BsonProperty("value") Double value) {

    if (value > MAX_VALUE || MIN_VALUE > value) {
      throw new IllegalArgumentException(
          String.format("Value has to be between %s and %s", MIN_VALUE, MAX_VALUE));
    }
    return new Longitude(value);
  }
}
