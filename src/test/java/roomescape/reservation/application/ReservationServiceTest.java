package roomescape.reservation.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.repository.ReservationRepository;
import roomescape.reservation.dto.MyReservationResponse;
import roomescape.theme.domain.Theme;
import roomescape.theme.domain.repository.ThemeRepository;
import roomescape.time.domain.ReservationTime;
import roomescape.time.domain.repository.ReservationTimeRepository;
import roomescape.user.domain.User;
import roomescape.user.domain.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationTimeRepository reservationTimeRepository;

    @Mock
    private ThemeRepository themeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void 내_예약_목록_조회() {
        // given
        ReservationTime time = new ReservationTime(1L, "10:00");
        Theme theme = new Theme(1L, "레벨1 탈출", "우테코 레벨1을 탈출하는 내용입니다.",
                "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg");
        User user = new User(1L, "브라운", "brown@email.com", "password", "USER");
        Reservation reservation = new Reservation(1L, LocalDate.now(), user, time, theme);

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(reservationRepository.findAllByUser(any())).willReturn(List.of(reservation));

        // when
        List<MyReservationResponse> responses = reservationService.getMyReservations(1L);

        // then
        assertThat(responses).hasSize(1);
    }
}
