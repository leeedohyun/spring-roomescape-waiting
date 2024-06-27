package roomescape.time.infrastructure;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import roomescape.time.domain.ReservationTime;

public interface JpaReservationTimeRepository extends JpaRepository<ReservationTime, Long> {

    boolean existsByStartAt(LocalTime startAt);
}
