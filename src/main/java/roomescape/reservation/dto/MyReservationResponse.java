package roomescape.reservation.dto;

import roomescape.reservation.domain.Reservation;

public record MyReservationResponse(Long reservationId, String theme, String date, String time, String status) {

    public static MyReservationResponse from(Reservation reservation) {
        return new MyReservationResponse(
                reservation.getId(),
                reservation.getTheme().getName(),
                reservation.getDate().toString(),
                reservation.getTime().getStartAt().toString(),
                "예약"
        );
    }
}
