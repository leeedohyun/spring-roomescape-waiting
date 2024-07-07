package roomescape.waiting.application;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import roomescape.reservation.domain.repository.ReservationRepository;
import roomescape.reservation.dto.MyReservationResponse;
import roomescape.reservation.exception.PastDateReservationException;
import roomescape.theme.domain.Theme;
import roomescape.theme.domain.repository.ThemeRepository;
import roomescape.time.domain.ReservationTime;
import roomescape.time.domain.repository.ReservationTimeRepository;
import roomescape.user.domain.User;
import roomescape.user.domain.repository.UserRepository;
import roomescape.waiting.domain.ReservationWaiting;
import roomescape.waiting.domain.repository.ReservationWaitingRepository;
import roomescape.waiting.dto.ReservationWaitingRequest;

@Service
@Transactional(readOnly = true)
public class ReservationWaitingService {

    private final ReservationWaitingRepository reservationWaitingRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationTimeRepository reservationTimeRepository;
    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;

    public ReservationWaitingService(ReservationWaitingRepository reservationWaitingRepository,
                                     ReservationRepository reservationRepository,
                                     ReservationTimeRepository reservationTimeRepository,
                                     ThemeRepository themeRepository,
                                     UserRepository userRepository) {
        this.reservationWaitingRepository = reservationWaitingRepository;
        this.reservationRepository = reservationRepository;
        this.reservationTimeRepository = reservationTimeRepository;
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long addWaiting(ReservationWaitingRequest request, Long userId) {
        User waitingUser = userRepository.findById(userId)
                .orElseThrow();
        ReservationTime findReservationTime = reservationTimeRepository.findById(request.timeId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 예약 시간입니다."));
        Theme findTheme = themeRepository.findById(request.themeId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 테마입니다."));

        if (LocalDate.now().isAfter(LocalDate.parse(request.date()))) {
            throw new PastDateReservationException();
        }

        if (reservationRepository.existsByUserAndDateAndTimeAndTheme(waitingUser, LocalDate.parse(request.date()), findReservationTime, findTheme)) {
            throw new IllegalArgumentException("이미 예약하셨습니다.");
        }

        ReservationWaiting reservationWaiting = request.toReservationWaiting(waitingUser, findReservationTime, findTheme);
        return reservationWaitingRepository.save(reservationWaiting).getId();
    }

    public List<MyReservationResponse> getMyWaiting(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        List<ReservationWaiting> reservationWaitings = reservationWaitingRepository.findAllByUser(user);
        return reservationWaitings.stream()
                .map(MyReservationResponse::from)
                .toList();
    }

    public void deleteWaiting(Long waitingId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        ReservationWaiting reservationWaiting = reservationWaitingRepository.findById(waitingId)
                .orElseThrow();

        if (!reservationWaitingRepository.existsByUser(user)) {
            throw new IllegalArgumentException("예약 대기 중인 내역이 없습니다.");
        }

        reservationWaitingRepository.delete(reservationWaiting);
    }
}
