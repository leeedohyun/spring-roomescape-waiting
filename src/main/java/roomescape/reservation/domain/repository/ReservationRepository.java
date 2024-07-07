package roomescape.reservation.domain.repository;

import java.time.LocalDate;
import java.util.List;

import roomescape.reservation.domain.Reservation;
import roomescape.theme.domain.Theme;
import roomescape.time.domain.ReservationTime;
import roomescape.user.domain.User;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    List<Reservation> findAll();

    List<Reservation> findAllByUserIdAndThemeIdAndDateBetween(Long userId, Long themeId, LocalDate dateFrom, LocalDate dateTo);

    List<Reservation> findAllByUser(User user);

    void deleteById(Long reservationId);

    boolean existsById(Long reservationId);

    boolean existsByReservationTimeId(Long reservationTimeId);

    boolean existsByDateAndTimeId(String date, Long timeId);

    boolean existsByDateAndReservationTimeAndTheme(String date, ReservationTime reservationTime, Theme theme);

    boolean existsByThemeId(Long themeId);

    boolean existsByUserAndDateAndTimeAndTheme(User user, LocalDate date, ReservationTime time, Theme theme);
}
