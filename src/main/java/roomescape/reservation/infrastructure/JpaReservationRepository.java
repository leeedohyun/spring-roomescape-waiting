package roomescape.reservation.infrastructure;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import roomescape.reservation.domain.Reservation;
import roomescape.theme.domain.Theme;
import roomescape.time.domain.ReservationTime;

public interface JpaReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId AND r.theme.id = :themeId AND r.date BETWEEN :dateFrom AND :dateTo")
    List<Reservation> findAllByUserIdAndThemeIdAndDateBetween(Long userId, Long themeId, LocalDate dateFrom, LocalDate dateTo);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.time.id = :reservationTimeId")
    boolean existsByReservationTimeId(Long reservationTimeId);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.date = :date AND r.time.id = :timeId")
    boolean existsByDateAndTimeId(LocalDate date, Long timeId);

    boolean existsByDateAndTimeAndTheme(LocalDate date, ReservationTime reservationTime, Theme theme);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.theme.id = :themeId")
    boolean existsByThemeId(Long themeId);
}
