package roomescape.reservation.infrastructure;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.repository.ReservationRepository;
import roomescape.theme.domain.Theme;
import roomescape.time.domain.ReservationTime;
import roomescape.user.domain.User;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    
    private final JpaReservationRepository jpaReservationRepository;

    public ReservationRepositoryImpl(JpaReservationRepository jpaReservationRepository) {
        this.jpaReservationRepository = jpaReservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return jpaReservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return jpaReservationRepository.findAll();
    }

    @Override
    public List<Reservation> findAllByUser(User user) {
        return jpaReservationRepository.findAllByUser(user);
    }

    @Override
    public List<Reservation> findAllByUserIdAndThemeIdAndDateBetween(Long userId, Long themeId, LocalDate dateFrom, LocalDate dateTo) {
        return jpaReservationRepository.findAllByUserIdAndThemeIdAndDateBetween(userId, themeId, dateFrom, dateTo);
    }

    @Override
    public void deleteById(Long reservationId) {
        jpaReservationRepository.deleteById(reservationId);
    }

    @Override
    public boolean existsById(Long reservationId) {
        return jpaReservationRepository.existsById(reservationId);
    }

    @Override
    public boolean existsByReservationTimeId(Long reservationTimeId) {
        return jpaReservationRepository.existsByReservationTimeId(reservationTimeId);
    }

    @Override
    public boolean existsByDateAndTimeId(String date, Long timeId) {
        return jpaReservationRepository.existsByDateAndTimeId(LocalDate.parse(date), timeId);
    }

    @Override
    public boolean existsByDateAndReservationTimeAndTheme(String date, ReservationTime reservationTime, Theme theme) {
        return jpaReservationRepository.existsByDateAndTimeAndTheme(LocalDate.parse(date), reservationTime, theme);
    }

    @Override
    public boolean existsByThemeId(Long themeId) {
        return jpaReservationRepository.existsByThemeId(themeId);
    }

    @Override
    public boolean existsByUserAndDateAndTimeAndTheme(User user, LocalDate date, ReservationTime time, Theme theme) {
        return jpaReservationRepository.existsByUserAndDateAndTimeAndTheme(user, date, time, theme);
    }
}
