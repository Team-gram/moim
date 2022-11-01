package ajou.gram.moim.repository;

import ajou.gram.moim.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements UserInterface {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> user = em.createQuery("select m from TblUser m where m._name = :name", User.class)
                .setParameter("name", name)
                .getResultList();

        return user.stream().findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> user = em.createQuery("select m from TblUser m where m._email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return user.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select m from TblUser m", User.class)
                .getResultList();
    }
}
