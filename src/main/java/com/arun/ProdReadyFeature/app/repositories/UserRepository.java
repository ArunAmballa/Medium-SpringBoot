package com.arun.ProdReadyFeature.app.repositories;

import com.arun.ProdReadyFeature.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
