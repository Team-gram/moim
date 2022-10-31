package ajou.gram.moim.repository;

import ajou.gram.moim.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    int join(User user);
    Optional<User> findById(long id);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
