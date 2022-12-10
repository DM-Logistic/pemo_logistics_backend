package com.course.trucks.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String drivingLicense;
    private String citizenship;
    private String seriesPassportNumber;
    private String visas;
    private String role;
    private boolean isActive;
    private String avatarImagePath;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_tasks",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    private Set<Task> tasks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isActive != user.isActive) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(surname, user.surname)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(phone, user.phone)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(drivingLicense, user.drivingLicense))
            return false;
        if (!Objects.equals(citizenship, user.citizenship)) return false;
        if (!Objects.equals(seriesPassportNumber, user.seriesPassportNumber))
            return false;
        if (!Objects.equals(visas, user.visas)) return false;
        if (!Objects.equals(role, user.role)) return false;
        return Objects.equals(avatarImagePath, user.avatarImagePath);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (drivingLicense != null ? drivingLicense.hashCode() : 0);
        result = 31 * result + (citizenship != null ? citizenship.hashCode() : 0);
        result = 31 * result + (seriesPassportNumber != null ? seriesPassportNumber.hashCode() : 0);
        result = 31 * result + (visas != null ? visas.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (avatarImagePath != null ? avatarImagePath.hashCode() : 0);
        return result;
    }

}
