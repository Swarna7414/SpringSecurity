package com.Securtiy.Repository;

import com.Securtiy.Model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomRepository extends JpaRepository<CustomUser,Long> {

    Optional<CustomUser> findByEmail(String email);
}
