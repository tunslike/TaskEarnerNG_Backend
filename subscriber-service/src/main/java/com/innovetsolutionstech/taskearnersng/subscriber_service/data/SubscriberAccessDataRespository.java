package com.innovetsolutionstech.taskearnersng.subscriber_service.data;

import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.SubscriberAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriberAccessDataRespository extends JpaRepository<SubscriberAccess, Integer> {

    SubscriberAccess findBySubscriberId(String subscriberId);

}
