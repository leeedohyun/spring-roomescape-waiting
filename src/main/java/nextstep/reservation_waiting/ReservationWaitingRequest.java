package nextstep.reservation_waiting;

public class ReservationWaitingRequest {
    private Long scheduleId;

    public ReservationWaitingRequest() {
    }

    public ReservationWaitingRequest(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }
}