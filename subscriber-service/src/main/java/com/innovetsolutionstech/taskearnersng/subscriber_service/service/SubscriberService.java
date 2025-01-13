package com.innovetsolutionstech.taskearnersng.subscriber_service.service;

import com.innovetsolutionstech.taskearnersng.subscriber_service.data.SubscriberAccessDataRespository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.data.SubscriberDataRepository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.data.SubscriberProfileDataRepository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.Subscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.exceptions.SubscriberException;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.LoginSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.LoginResponse;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.mapper.SubscriberMapper;
import com.innovetsolutionstech.taskearnersng.subscriber_service.repository.SubscriberRepository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriberService implements SubscriberRepository<Subscriber> {

    // database access
    private final SubscriberDataRepository subscriberRepo;
    private final SubscriberAccessDataRespository subscriberAccessRepo;
    private final SubscriberProfileDataRepository subscriberProfileRepo;

    // data mappers
    private final SubscriberMapper subscriberMapper;

    private Integer checkIfSubscriberExists(String username) {
        return subscriberRepo.findSubscriberByUsername(username);
    }

    // create new subscriber
    @Override
    public String createSubscriber(NewSubscriber request) {
        log.info("Creating new subscriber with details {}", request);

        try {

            Integer count = checkIfSubscriberExists(request.username());

            if(count > 0)
                throw new SubscriberException("Duplicate record found");


            var subscriber = subscriberRepo.save(subscriberMapper.toSubscriber(request));

            if(!Objects.equals(subscriber.getSubscriberId(), "")) {
                createSubscriberAccess(subscriber.getSubscriberId(), request.authToken(), request.accessCode());
            }

            log.info("Subscriber created with ID:: " + subscriber.getSubscriberId());

            return subscriber.getSubscriberId();

        }catch(Exception e) {
            throw new SubscriberException("Unable to create new subscriber " + e.getMessage());
        }

    }

    // create subscriber password access code
    private void createSubscriberAccess(String subscriberId, String authToken, String accessCode) {
        try {

            var access = subscriberAccessRepo.save(subscriberMapper
                    .toSubscriberAccess(subscriberId, authToken, accessCode));

        }catch(Exception e){
            throw new SubscriberException("Unable to create subscriber access code " + e.getMessage());
        }
    }

    // create subscriber profile
    @Override
    public String createSubscriberProfile(NewSubscriberProfile request) {
        try {

            var profile = subscriberProfileRepo.save(subscriberMapper.toSubscriberProfile(request));

            return profile.getSubscriberId();

        }catch (Exception e) {
            throw new SubscriberException("An error occurred:" + e.getMessage());
        }
    }

    @Override
    public LoginResponse authenticateUser(LoginSubscriber login) {

        try {

            var subscriber = subscriberRepo.findByUsername(login.subscriberId());

            if(subscriber != null) {

                var accessCode = subscriberAccessRepo.findBySubscriberId(subscriber.getSubscriberId());

                if(Utilities.validateAccessCode(login.accessCode(), accessCode.getAccessCode())) {

                  return new LoginResponse(
                          subscriber.getSubscriberId(),
                          subscriber.getUsername(),
                          subscriber.getFirstname(),
                          subscriber.getLastname(),
                          subscriber.getAuthType().toString(),
                          1
                  );

                }else{
                    throw new SubscriberException("Incorrect username or password!");
                }
            }else{
                throw new SubscriberException("Subscriber not found");
            }

        }catch (Exception e) {
            throw new SubscriberException("An error occurred:" + e.getMessage());
        }
    }

    @Override
    public String resetPassword(String subscriberId) {
        return null;
    }
}
