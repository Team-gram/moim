package ajou.gram.moim.service;

import ajou.gram.moim.domain.User;
import ajou.gram.moim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateDuplicateUser(long id) {
        return userRepository.findById(id).orElse(new User(0));
    }
}
