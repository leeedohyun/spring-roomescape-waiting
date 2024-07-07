package roomescape.waiting.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import roomescape.user.domain.User;
import roomescape.waiting.domain.ReservationWaiting;

public interface ReservationWaitingRepository extends JpaRepository<ReservationWaiting, Long> {

    List<ReservationWaiting> findAllByUser(User user);
}
