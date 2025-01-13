package com.innovetsolutionstech.taskearnersng.subscriber_service.data;

import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.SubscriberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberProfileDataRepository extends JpaRepository<SubscriberProfile, Integer> {
}
