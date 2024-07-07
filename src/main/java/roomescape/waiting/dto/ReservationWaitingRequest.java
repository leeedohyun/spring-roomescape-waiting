package roomescape.waiting.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import roomescape.theme.domain.Theme;
import roomescape.time.domain.ReservationTime;
import roomescape.user.domain.User;
import roomescape.waiting.domain.ReservationWaiting;

public record ReservationWaitingRequest(@NotNull(message = "날짜는 필수 입력 값입니다.")
                                        String date,
                                        @NotNull(message = "테마 ID는 필수 입력 값입니다.")
                                        Long themeId,
                                        @NotNull(message = "시간 ID는 필수 입력 값입니다.")
                                        Long timeId,
                                        @NotNull(message = "회원 ID는 필수 입력 값입니다.")
                                        Long memberId) {

    public ReservationWaiting toReservationWaiting(User waitingUser, ReservationTime reservationTime, Theme theme) {
        return new ReservationWaiting(LocalDate.parse(date), waitingUser, reservationTime, theme);
    }
}
