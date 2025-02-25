package com.innovetsolutionstech.taskearnersng.subscriber_service.data;

import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.ManageAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManageAccessDataRepository extends JpaRepository<ManageAccess, Integer> {
    Optional<ManageAccess> findByToken(String token);
    void deleteBySubscriberId(String subscriberId);
}
