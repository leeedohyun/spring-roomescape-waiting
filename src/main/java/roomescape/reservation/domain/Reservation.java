package roomescape.reservation.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import roomescape.theme.domain.Theme;
import roomescape.time.domain.ReservationTime;
import roomescape.user.domain.User;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_id")
    private ReservationTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    protected Reservation() {
    }

    public Reservation(LocalDate date, User user, ReservationTime time, Theme theme) {
        this(null, date, user, time, theme);
    }

    public Reservation(Long id, String date, User user, ReservationTime time, Theme theme) {
        this(id, LocalDate.parse(date), user, time, theme);
    }

    public Reservation(Long id, LocalDate date, User user, ReservationTime time, Theme theme) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.time = time;
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public ReservationTime getTime() {
        return time;
    }

    public Theme getTheme() {
        return theme;
    }
}
