package com.yashpawar5.BookMyShow.Repositories;

import com.yashpawar5.BookMyShow.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
