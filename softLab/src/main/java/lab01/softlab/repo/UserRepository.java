package lab01.softlab.repo;

import lab01.softlab.entities.Role;
import lab01.softlab.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User repository interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.name = :name")
    List<User> findByName(String name);

    @Query("select u from User u where u.role = :role and u.age > 60")
    List<User> findRetiredUsersByRole(Role role);
}
