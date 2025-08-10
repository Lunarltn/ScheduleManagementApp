package org.example.schedulemanagementapp.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.user.dto.UserLoginResponse;
import org.example.schedulemanagementapp.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    /*public ResponseEntity<UserLoginResponse> home(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


    }*/
}
