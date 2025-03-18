package com.innovetsolutionstech.taskearnersng.subscriber_service.data;

import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.SubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.SubscriberProfileDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface SubscriberProfileDataRepository extends JpaRepository<SubscriberProfile, Integer> {

    SubscriberProfile findAllBySubscriberId(String subscriberId);

    SubscriberProfile findBySubscriberId(String subscriberId);

    @Modifying
    @Transactional
    @Query("UPDATE SubscriberProfile t SET t.bank_name = :bankName, t.account_number = :accountNumber, " +
            "t.account_name = :accountName, t.accountDateUpdate = :dateUpdated WHERE t.subscriberId = :subscriberId")
    int updateAccountDetails(String bankName, String accountNumber, String accountName, LocalDateTime dateUpdated, String subscriberId);

}
