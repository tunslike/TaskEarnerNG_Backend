package com.innovetsolutionstech.taskearnersng.subscriber_service.data;

import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriberDataRepository extends JpaRepository<Subscriber, Integer> {

    @Query(value = "SELECT count(o) FROM Subscriber o WHERE o.username = :username")
    Integer findSubscriberByUsername(String username);

    Subscriber findByUsername(String username);

}
