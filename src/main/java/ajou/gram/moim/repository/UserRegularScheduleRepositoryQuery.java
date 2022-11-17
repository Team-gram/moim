package ajou.gram.moim.repository;

import ajou.gram.moim.domain.UserRegularSchedule;
import ajou.gram.moim.dto.CreateRegularScheduleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@AllArgsConstructor
public class UserRegularScheduleRepositoryQuery {
    private final EntityManager em;

    public boolean validateSchedule(long userId, long scheduleId, CreateRegularScheduleDto createRegularScheduleDto) {
        boolean patch = false;
        String query =
                "select m from user_regular_schedule m " +
                "where m.userId = :userId " +
                "and m.day = :day " +
                "and m.startTime < str_to_date(:endTime, '%H:%i') " +
                "and m.endTime > str_to_date(:startTime, '%H:%i') ";
        if (scheduleId > 0) {
            query += "and m.id != :scheduleId";
            patch = true;
        }

        TypedQuery<UserRegularSchedule> userRegularScheduleTypedQuery = em.createQuery(query, UserRegularSchedule.class).setMaxResults(1000);
        userRegularScheduleTypedQuery = userRegularScheduleTypedQuery.setParameter("userId", userId);
        userRegularScheduleTypedQuery = userRegularScheduleTypedQuery.setParameter("day", createRegularScheduleDto.getDay());
        userRegularScheduleTypedQuery = userRegularScheduleTypedQuery.setParameter("startTime", createRegularScheduleDto.getStartTime());
        userRegularScheduleTypedQuery = userRegularScheduleTypedQuery.setParameter("endTime", createRegularScheduleDto.getEndTime());
        if (patch) {
            userRegularScheduleTypedQuery = userRegularScheduleTypedQuery.setParameter("scheduleId", scheduleId);
        }
        return userRegularScheduleTypedQuery.getResultList().size() > 0 ? false : true;
    }
}
