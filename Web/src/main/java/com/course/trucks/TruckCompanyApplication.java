package com.course.trucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class TruckCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TruckCompanyApplication.class);
    }

}
