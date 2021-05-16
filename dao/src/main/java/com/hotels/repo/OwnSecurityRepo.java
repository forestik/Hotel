package com.hotels.repo;

import com.hotels.entity.OwnSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link OwnSecurity}.
 *
 */
@Repository
public interface OwnSecurityRepo extends JpaRepository<OwnSecurity, Long> {
    /**
     * Updates a password.
     *
     * @param password {@link String}
     * @param id       {@link Long}
     */
    @Modifying
    @Query("UPDATE OwnSecurity o SET o.password = :password WHERE o.user.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);
}
