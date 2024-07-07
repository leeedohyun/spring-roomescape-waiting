package roomescape.waiting.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import roomescape.waiting.domain.ReservationWaiting;

public interface ReservationWaitingRepository extends JpaRepository<ReservationWaiting, Long> {
}
