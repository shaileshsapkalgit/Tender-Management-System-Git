package com.fresco.tenderManagement.repository;

import com.fresco.tenderManagement.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
