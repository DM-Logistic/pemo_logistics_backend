package com.course.trucks.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private String number;
    private String mark;
    private String trailerType;
    private String loadCapacity;
    private String volume;
    @OneToOne(mappedBy = "car")
    private User user;

    public Car(long id, String model, String number, String mark, String trailerType, String loadCapacity, String volume) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.mark = mark;
        this.trailerType = trailerType;
        this.loadCapacity = loadCapacity;
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (!Objects.equals(model, car.model)) return false;
        if (!Objects.equals(number, car.number)) return false;
        if (!Objects.equals(mark, car.mark)) return false;
        if (!Objects.equals(trailerType, car.trailerType)) return false;
        if (!Objects.equals(loadCapacity, car.loadCapacity)) return false;
        return Objects.equals(volume, car.volume);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (trailerType != null ? trailerType.hashCode() : 0);
        result = 31 * result + (loadCapacity != null ? loadCapacity.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }

}
