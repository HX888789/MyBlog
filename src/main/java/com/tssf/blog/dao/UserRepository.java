package com.tssf.blog.dao;

import com.tssf.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author 黄旭
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);

}
