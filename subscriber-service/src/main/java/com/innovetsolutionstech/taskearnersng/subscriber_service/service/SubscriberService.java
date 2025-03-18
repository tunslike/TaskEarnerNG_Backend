package com.innovetsolutionstech.taskearnersng.subscriber_service.service;

import com.innovetsolutionstech.taskearnersng.subscriber_service.data.SubscriberAccessDataRespository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.data.SubscriberDataRepository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.data.SubscriberProfileDataRepository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.ManageAccess;
import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.Subscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.SubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.exceptions.SubscriberException;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.LoginSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.SubscriberBankDetails;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.LoginResponse;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.SubscriberProfileDto;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.mapper.SubscriberMapper;
import com.innovetsolutionstech.taskearnersng.subscriber_service.repository.SubscriberRepository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.utilities.JwtUtil;
import com.innovetsolutionstech.taskearnersng.subscriber_service.utilities.Utilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriberService implements SubscriberRepository<Subscriber> {

    // database access
    private final SubscriberDataRepository subscriberRepo;
    private final SubscriberAccessDataRespository subscriberAccessRepo;
    private final SubscriberProfileDataRepository subscriberProfileRepo;
    private final ManageAccessService manageAccessService;
    private final JwtUtil jwtUtil;

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

                    String token = jwtUtil.generateAccessToken(subscriber.getSubscriberId());
                    ManageAccess manageAccess = manageAccessService.createRefreshToken(subscriber.getSubscriberId());


                  return new LoginResponse(
                          subscriber.getSubscriberId(),
                          subscriber.getUsername(),
                          subscriber.getFirstname(),
                          subscriber.getLastname(),
                          subscriber.getAuthType().toString(),
                          token,
                          manageAccess.getToken(), 1

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

    @Transactional
    public SubscriberProfileDto updateSubscriberProfile(NewSubscriberProfile profile) {

        try {

            Optional<SubscriberProfile> subscriberProfile =
                    Optional.ofNullable(subscriberProfileRepo.findBySubscriberId(profile.subscriberId()));

            if(subscriberProfile.isPresent())  {

                SubscriberProfile newProfile = subscriberProfile.get();

                // update fields
                newProfile.setFirstName(profile.firstName());
                newProfile.setLastName(profile.lastName());
                newProfile.setBirthday(profile.birthday());
                newProfile.setGender(profile.gender());
                newProfile.setEmailAddress(profile.emailAddress());
                newProfile.setMobileNumber(profile.mobileNumber());
                newProfile.setLocation(profile.location());
                newProfile.setReligion(profile.religion());
                newProfile.setBank_name(profile.firstName());
                newProfile.setAccount_name(profile.firstName());
                newProfile.setAccount_number(profile.mobileNumber());
                newProfile.setDateUpdated(LocalDateTime.now());

                subscriberProfileRepo.save(newProfile);

                SubscriberProfile subscriber_new_profile = subscriberProfileRepo.findAllBySubscriberId(profile.subscriberId());

                return new SubscriberProfileDto(
                        subscriber_new_profile.getSubscriberId(),
                        subscriber_new_profile.getFirstName(),
                        subscriber_new_profile.getLastName(),
                        subscriber_new_profile.getMobileNumber(),
                        subscriber_new_profile.getEmailAddress(),
                        subscriber_new_profile.getGender(),
                        subscriber_new_profile.getBirthday(),
                        subscriber_new_profile.getReligion(),
                        subscriber_new_profile.getLocation(),
                        subscriber_new_profile.getBank_name(),
                        subscriber_new_profile.getAccount_number(),
                        subscriber_new_profile.getAccount_name()
                );
            }

        }catch (Exception e) {
            throw new SubscriberException("Error updating subscriber profile : " + e.getMessage());
        }

        return null;
    }

    @Override
    public SubscriberProfileDto fetchSubriberProfile(String subscriberId) {
        try {

            SubscriberProfile profile = subscriberProfileRepo.findAllBySubscriberId(subscriberId);

            return new SubscriberProfileDto(
                    profile.getSubscriberId(),
                    profile.getFirstName(),
                    profile.getLastName(),
                    profile.getMobileNumber(),
                    profile.getEmailAddress(),
                    profile.getGender(),
                    profile.getBirthday(),
                    profile.getReligion(),
                    profile.getLocation(),
                    profile.getBank_name(),
                    profile.getAccount_number(),
                    profile.getAccount_name()
            );

        }catch (Exception e){
            throw new SubscriberException("Error fetching subscriber profile: " + e.getMessage());
        }
    }

    @Override
    public String updateAccountDetails(SubscriberBankDetails details) {

        try {

            Optional<SubscriberProfile> profile =
                    Optional.ofNullable(subscriberProfileRepo.findBySubscriberId(details.subscriberId()));

            if(profile.isPresent()) {

                var response = subscriberProfileRepo.
                        updateAccountDetails(
                                details.bankName(),
                                details.accountNumber(),
                                details.accountName(),
                                LocalDateTime.now(),
                                details.subscriberId()
                                );

                return details.subscriberId();
            }

        }catch(Exception e) {
            throw new SubscriberException("Error updating subscriber bank details: " + e.getMessage());
        }

        return null;
    }
}
