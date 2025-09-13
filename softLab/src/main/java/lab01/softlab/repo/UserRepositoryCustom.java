
package lab01.softlab.repo;

import lab01.softlab.entities.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByName();
}
