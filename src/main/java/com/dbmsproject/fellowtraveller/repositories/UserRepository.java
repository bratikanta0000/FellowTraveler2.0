package com.dbmsproject.fellowtraveller.repositories;


import com.dbmsproject.fellowtraveller.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE name = :name", nativeQuery = true)
    Optional<User> getUserByName(@Param("name") String name);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "DELETE FROM users WHERE name = :name", nativeQuery = true)
    @Modifying
    void deleteByName(@Param("name") String name);

    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    @Modifying
    void deleteById(@Param("id") Long id);

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<User> getUserById(@Param("id") Long id);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM users WHERE id = :id) THEN TRUE ELSE FALSE END", nativeQuery = true)
    boolean existsById(@Param("id") Long id);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM users WHERE name = :name) THEN TRUE ELSE FALSE END", nativeQuery = true)
    boolean existsByName(@Param("name") String name);

//    @Query(value = "INSERT INTO users (name, email, password, phone) VALUES (#{#user.name}, #{#user.email}, #{#user.password}, #{#user.phone})", nativeQuery = true)
//    @Modifying
//    void InsertUser(@Param("user") User user);
//
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (name, email, password, phone) VALUES (:name, :email, :password, :phone)", nativeQuery = true)
    void insertUser(@Param("name") String name,
                    @Param("email") String email,
                    @Param("password") String password,
                    @Param("phone") String phone);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}