package com.devgraph.userservice.repository;
import com.devgraph.userservice.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
    //Optional<Role> findById(Long id);not neccessary cause jpaRepository already provides findById()   
}