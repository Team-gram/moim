package ajou.gram.moim.enumeration;

import ajou.gram.moim.Service.UserService;
import ajou.gram.moim.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private EntityManager em;
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void beforeEach() {
        userRepository = new UserRepository(em);
        userService = new UserService(userRepository);
    }

    @Test
    public void joinTest() throws Exception{
        long id = 9999999999L;
        NullPointerException e = assertThrows(NullPointerException.class,
                () -> userService.validateDuplicateUser(id));
        assertThat(e.getMessage()).isEqualTo(null);
    }
}
