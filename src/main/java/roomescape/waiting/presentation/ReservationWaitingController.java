package roomescape.waiting.presentation;

import java.net.URI;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import roomescape.auth.UserId;
import roomescape.waiting.application.ReservationWaitingService;
import roomescape.waiting.dto.ReservationWaitingRequest;

@RestController
public class ReservationWaitingController {

    private final ReservationWaitingService reservationWaitingService;

    public ReservationWaitingController(ReservationWaitingService reservationWaitingService) {
        this.reservationWaitingService = reservationWaitingService;
    }

    @PostMapping("/reservations/waiting")
    public ResponseEntity<Void> addWaiting(@RequestBody @Valid ReservationWaitingRequest request, @UserId Long userId) {
        Long waitingId = reservationWaitingService.addWaiting(request, userId);
        return ResponseEntity.created(URI.create("/reservations/waiting/" + waitingId)).build();
    }

    @DeleteMapping("/reservations/waiting/{waitingId}")
    public ResponseEntity<Void> addWaiting(@PathVariable Long waitingId, @UserId Long userId) {
        reservationWaitingService.deleteWaiting(waitingId, userId);
        return ResponseEntity.noContent().build();
    }
}
