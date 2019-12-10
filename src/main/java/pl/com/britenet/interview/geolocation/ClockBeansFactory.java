package pl.com.britenet.interview.geolocation;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import java.time.Clock;

@Factory
class ClockBeansFactory {
  @Bean
  Clock getClock() {
    return Clock.systemUTC();
  }
}
