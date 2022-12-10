package com.course.trucks.dao;

import com.course.trucks.model.Car;
import com.course.trucks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    void deleteById(long id);

    @Modifying
    @Query(value = "UPDATE users SET isActive = :isActive WHERE id = :userId", nativeQuery = true)
    void updateIsActiveById(@Param("userId") long userId, @Param("isActive") boolean isActive);

    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.surname = :surname, u.phone = :phone, u.drivingLicense = :drivingLicenseCategory, u.citizenship = :citizenship, u.seriesPassportNumber = :seriesAndNumberOfPassport, u.visas = :visas, u.role = :role, u.isActive = :isActive, u.avatarImagePath = :avatarImagePath, u.car = :car WHERE u.id = :id")
    void updateUser(@Param("id") long userId, @Param("phone") String phone,
                    @Param("drivingLicenseCategory") String drivingLicenseCategory,
                    @Param("citizenship") String citizenship,
                    @Param("seriesAndNumberOfPassport") String seriesAndNumberOfPassport,
                    @Param("visas") String visas, @Param("role") String role, @Param("isActive") boolean isActive,
                    @Param("car") Car car, @Param("avatarImagePath") String avatarImagePath, @Param("name") String name,
                    @Param("surname") String surname);

    @Modifying
    @Query(value = "DELETE FROM users_tasks WHERE task_Id = :taskId", nativeQuery = true)
    void reAssignTask(@Param("taskId") long userId);

    @Modifying
    @Query(value = "INSERT INTO users_tasks(user_id, task_id) VALUES (:user_id, :task_id);", nativeQuery = true)
    void assignTask(@Param("user_id") long userId, @Param("task_id") long task_id);

    User findUserByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM users_tasks WHERE user_id = :userId", nativeQuery = true)
    void deleteUserFromLinkTableById(@Param("userId") long userId);

    @Query(value = "SELECT COUNT(*) FROM users WHERE email = :email", nativeQuery = true)
    int getUsersCountByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE users SET isActive = 1 WHERE id = :userId", nativeQuery = true)
    void makeUserActiveById(@Param("userId") long userId);

    @Modifying
    @Query(value = "UPDATE users SET isActive = 0 WHERE id = :userId", nativeQuery = true)
    void makeUserNonActiveById(@Param("userId") long userId);

    @Query(value = "SELECT user_id FROM users_tasks WHERE task_id = :taskId", nativeQuery = true)
    Long getUserIdByTaskId(@Param("taskId") long taskId);
}
