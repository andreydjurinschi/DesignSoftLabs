package lab01.user_mask_tests;

import lab01.softlab.entities.Role;
import lab01.softlab.entities.User;
import lab01.softlab.mask.UserFieldMask;
import lab01.softlab.repo.UserRepository;
import lab01.softlab.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Map;
import java.util.logging.Logger;


import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserMaskTests {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    private static final Logger logger = Logger.getLogger(UserMaskTests.class.getName());

    @Test
    void getAll(){

        UserFieldMask mask = new UserFieldMask();
        mask.setAge(true);
        mask.setName(true);
        mask.setId(false);
        mask.setRating(false);
        mask.setRole(true);

        User user1 = new User();
        user1.setAge(10);
        user1.setName("test1");
        user1.setRating(1.5F);
        user1.setRole(Role.ADMINISTRATOR);

        User user2 = new User();
        user2.setAge(11);
        user2.setName("test2");
        user2.setRating(5.5F);
        user2.setRole(Role.TEACHER);

        Mockito.when(repo.findAll()).thenReturn(List.of(user1, user2));
        List<Object> users = service.getAllRefToMask(mask);

        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());

        Map<String, Object> u1 = (Map<String, Object>) users.get(0);
        Assertions.assertEquals("test1", u1.get("name"));
        Assertions.assertEquals(10, u1.get("age"));
        Assertions.assertEquals(Role.ADMINISTRATOR, u1.get("role"));
        Assertions.assertFalse(u1.containsKey("id"));
        Assertions.assertFalse(u1.containsKey("rating"));

        Map<String, Object> u2 = (Map<String, Object>) users.get(1);
        Assertions.assertEquals("test2", u2.get("name"));
        Assertions.assertEquals(11, u2.get("age"));
        Assertions.assertEquals(Role.TEACHER, u2.get("role"));
    }

    @Test
    void fetFieldByMask(){
        byte mask =FieldMaskTestClass.createMask(FieldMaskTestClass.ID, FieldMaskTestClass.NAME);

        boolean hasName = ((mask & FieldMaskTestClass.NAME) != 0);
        boolean hasRating = ((mask & FieldMaskTestClass.RATING) != 0);
        boolean hasId = ((mask & FieldMaskTestClass.ID) != 0);

        Assertions.assertTrue(hasName);
        Assertions.assertFalse(hasRating);
        Assertions.assertTrue(hasId);
    }
}
