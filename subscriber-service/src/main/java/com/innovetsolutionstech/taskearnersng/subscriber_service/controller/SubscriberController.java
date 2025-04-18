package com.innovetsolutionstech.taskearnersng.subscriber_service.controller;

import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.SubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.LoginSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.SubscriberBankDetails;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.LoginResponse;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.SubscriberProfileDto;
import com.innovetsolutionstech.taskearnersng.subscriber_service.service.ManageAccessService;
import com.innovetsolutionstech.taskearnersng.subscriber_service.service.SubscriberService;
import com.innovetsolutionstech.taskearnersng.subscriber_service.utilities.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscribers")
@RequiredArgsConstructor
public class SubscriberController {

    private final JwtUtil jwtUtil;
    private final SubscriberService service;
    private final ManageAccessService manageAccessService;

    // register new subcriber
    @PostMapping("/new-subscriber")
    public ResponseEntity<String> newSubscriber(@RequestBody @Valid NewSubscriber request) {
        return ResponseEntity.ok(service.createSubscriber(request));
    }

    // fetch subscriber profile
    @GetMapping("/fetchProfile")
    public ResponseEntity<SubscriberProfileDto> fetchSubscriberProfile(@RequestParam String subscriberId) {
        return ResponseEntity.ok(service.fetchSubriberProfile(subscriberId));
    }

    // create subscriber profile
    @PostMapping("/createProfile")
    public ResponseEntity<String> createSubscriberProfile(@RequestBody @Valid NewSubscriberProfile profile) {
        return ResponseEntity.ok(service.createSubscriberProfile(profile));
    }

    // update subscriber profile
    @PostMapping("/updateProfile")
    public ResponseEntity<SubscriberProfileDto> updateSubscriberProfile(
            @RequestBody NewSubscriberProfile newProfile) {
        return ResponseEntity.ok(service.updateSubscriberProfile(newProfile));
    }

    // update subscriber bank account
    @PostMapping("/updateAccountDetails")
    public ResponseEntity<String> updateBankDetails(@RequestBody @Valid SubscriberBankDetails request) {
        return ResponseEntity.ok(service.updateAccountDetails(request));
    }

    // authenticate subscriber
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginSubscriber login) {
            return ResponseEntity.ok(service.authenticateUser(login));
    }

    // reset password
    @GetMapping("/reset-password")
    public ResponseEntity resetPassword(@PathVariable("subscriberId") String subscriberId) {
        return ResponseEntity.ok(service.resetPassword(subscriberId));
    }




}
