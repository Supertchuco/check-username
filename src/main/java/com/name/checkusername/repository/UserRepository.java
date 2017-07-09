package com.name.checkusername.repository;

import com.name.checkusername.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rafae on 08/07/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
        List<User> findByUserName(String userName);
}
