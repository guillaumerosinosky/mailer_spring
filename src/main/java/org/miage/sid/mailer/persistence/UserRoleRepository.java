package org.miage.sid.mailer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("select ur.role from UserRole ur join ur.user u where u.username = ?1")
    public List<String> findRoleByUsername(String username);

}
