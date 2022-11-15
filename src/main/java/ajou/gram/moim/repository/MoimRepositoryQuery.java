package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Moim;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class MoimRepositoryQuery {

    private final EntityManager em;

    public List<Moim> getMoims(int categoryId, String sido, String sigungu, String dong, String title) {
        String query = "select m from moim m where 1=1 ";
        boolean f_sido = false;
        boolean f_sigungu = false;
        boolean f_dong = false;
        boolean f_title = false;
        if (categoryId == 0) {
            query += "and m.categoryId >= :categoryId ";
        } else {
            query += "and m.categoryId = :categoryId ";
        }
        if (sido != null) {
            query += "and m.sido = :sido ";
            f_sido = true;
        }
        if (sigungu != null) {
            query += "and m.sigungu = :sigungu ";
            f_sigungu = true;
        }
        if (dong != null) {
            query += "and m.dong = :dong ";
            f_dong = true;
        }
        if (title != null) {
            query += "and m.title like :title ";
            f_title = true;
        }
        TypedQuery<Moim> moims = em.createQuery(query, Moim.class).setMaxResults(1000);
        moims = moims.setParameter("categoryId", categoryId);
        if (f_sido) {
            moims = moims.setParameter("sido", sido);
        }
        if (f_sigungu) {
            moims = moims.setParameter("sigungu", sigungu);
        }
        if (f_dong) {
            moims = moims.setParameter("dong", dong);
        }
        if (f_title) {
            moims = moims.setParameter("title", "%" + title + "%");
        }

        return moims.getResultList();
    }
}
