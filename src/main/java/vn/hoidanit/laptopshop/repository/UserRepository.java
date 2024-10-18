package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User save(User userTest);

  List<User> findOneByEmail(String email); // if data not only one, do not use findOne. Can use findFirstEmail or
                                           // findTop1ByEmail
}