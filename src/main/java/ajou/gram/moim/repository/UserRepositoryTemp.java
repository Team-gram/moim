package ajou.gram.moim.repository;

import ajou.gram.moim.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserRepositoryTemp{

    private final EntityManager em;

    public UserRepositoryTemp(EntityManager em) {
        this.em = em;
    }

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findById(long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByName(String name) {
        List<User> user = em.createQuery("select m from TblUser m where m._name = :name", User.class)
                .setParameter("name", name)
                .getResultList();

        return user.stream().findAny();
    }
}
