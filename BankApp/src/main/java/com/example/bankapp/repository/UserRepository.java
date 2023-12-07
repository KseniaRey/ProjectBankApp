package com.example.bankapp.repository;

import com.example.bankapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.email = :email") // двоеточие означает ссылку - значение возьмет из строки снизу
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :uuid AND u.role = 'MANAGER'") // проверка на то, что наш юзер имеет статус менеджера
    Optional<User> findManagerById(UUID uuid);
}
