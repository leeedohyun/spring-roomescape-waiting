package nextstep.reservation_waiting;

import nextstep.schedule.Schedule;
import nextstep.theme.Theme;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationWaitingDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<ReservationWaiting> rowMapper = (resultSet, rowNum) -> new ReservationWaiting(
            resultSet.getLong("reservation_waiting.id"),
            new Schedule(
                    resultSet.getLong("schedule.id"),
                    new Theme(
                            resultSet.getLong("theme.id"),
                            resultSet.getString("theme.name"),
                            resultSet.getString("theme.desc"),
                            resultSet.getInt("theme.price")
                    ),
                    resultSet.getDate("schedule.date").toLocalDate(),
                    resultSet.getTime("schedule.time").toLocalTime()
            ),
            resultSet.getLong("reservation_waiting.member_id")
    );

    public ReservationWaitingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(ReservationWaiting reservationWaiting) {
        String sql = "INSERT INTO reservation_waiting (schedule_id, member_id) VALUES (?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, reservationWaiting.getSchedule().getId());
            ps.setLong(2, reservationWaiting.getMemberId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void deleteById(Long reservationWaitingId) {
        String sql = "DELETE FROM reservation_waiting WHERE id = ?";
        jdbcTemplate.update(sql, reservationWaitingId);
    }

    public Optional<ReservationWaiting> findById(Long id) {
        String sql = "SELECT " +
                "reservation_waiting.id, reservation_waiting.schedule_id, reservation_waiting.member_id, " +
                "schedule.id, schedule.theme_id, schedule.date, schedule.time, " +
                "theme.id, theme.name, theme.desc, theme.price " +
                "from reservation_waiting " +
                "inner join schedule on reservation_waiting.schedule_id = schedule.id " +
                "inner join theme on schedule.theme_id = theme.id " +
                "where reservation_waiting.id = ?;";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<ReservationWaiting> findByMemberId(Long memberId) {
        String sql = "SELECT " +
                "reservation_waiting.id, reservation_waiting.schedule_id, reservation_waiting.member_id, " +
                "schedule.id, schedule.theme_id, schedule.date, schedule.time, " +
                "theme.id, theme.name, theme.desc, theme.price " +
                "from reservation_waiting " +
                "inner join schedule on reservation_waiting.schedule_id = schedule.id " +
                "inner join theme on schedule.theme_id = theme.id " +
                "where reservation_waiting.member_id = ?;";
        try {
            return jdbcTemplate.query(sql, rowMapper, memberId);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Long findWaitNumById(Long id, Long scheduleId) {
        String sql = "SELECT count(*) as waitNum FROM reservation_waiting WHERE schedule_id = ? AND id <= ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> resultSet.getLong("waitNum"), id, scheduleId);
    }

    public ReservationWaiting findByScheduleId(Long scheduleId) {
        String sql = "SELECT " +
                "reservation_waiting.id, reservation_waiting.schedule_id, reservation_waiting.member_id, " +
                "schedule.id, schedule.theme_id, schedule.date, schedule.time, " +
                "theme.id, theme.name, theme.desc, theme.price " +
                "from reservation_waiting " +
                "inner join schedule on reservation_waiting.schedule_id = schedule.id " +
                "inner join theme on schedule.theme_id = theme.id " +
                "where reservation_waiting.schedule_id = ? " +
                "ORDER BY reservation_waiting.id " +
                "LIMIT 1;";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, scheduleId);
        } catch (Exception e) {
            return null;
        }
    }
}
