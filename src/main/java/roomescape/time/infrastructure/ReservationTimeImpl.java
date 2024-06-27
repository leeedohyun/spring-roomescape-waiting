package roomescape.time.infrastructure;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import roomescape.time.domain.ReservationTime;
import roomescape.time.domain.repository.ReservationTimeRepository;

@Repository
public class ReservationTimeImpl implements ReservationTimeRepository {

    private final JpaReservationTimeRepository jpaReservationTimeRepository;

    public ReservationTimeImpl(JpaReservationTimeRepository jpaReservationTimeRepository) {
        this.jpaReservationTimeRepository = jpaReservationTimeRepository;
    }

    @Override
    public ReservationTime save(ReservationTime reservationTime) {
        return jpaReservationTimeRepository.save(reservationTime);
    }

    @Override
    public Optional<ReservationTime> findById(Long reservationTimeId) {
        return jpaReservationTimeRepository.findById(reservationTimeId);
    }

    @Override
    public List<ReservationTime> findAll() {
        return jpaReservationTimeRepository.findAll();
    }

    @Override
    public boolean existsByStartAt(LocalTime startAt) {
        return jpaReservationTimeRepository.existsByStartAt(startAt);
    }

    @Override
    public void deleteById(Long reservationTimeId) {
        jpaReservationTimeRepository.deleteById(reservationTimeId);
    }
}
