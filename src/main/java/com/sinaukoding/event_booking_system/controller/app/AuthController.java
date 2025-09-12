package com.sinaukoding.event_booking_system.controller.app;

import com.sinaukoding.event_booking_system.config.UserLoggedInConfig;
import com.sinaukoding.event_booking_system.model.request.LoginRequestRecord;
import com.sinaukoding.event_booking_system.model.request.RegisterRequestRecord;
import com.sinaukoding.event_booking_system.model.response.BaseResponse;
import com.sinaukoding.event_booking_system.service.app.AuthService;
import com.sinaukoding.event_booking_system.service.managementuser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("login")
    public BaseResponse<?> login(@RequestBody LoginRequestRecord request) {
        return BaseResponse.ok(null, authService.login(request));
    }

    @GetMapping("logout")
    public BaseResponse<?> logout(@AuthenticationPrincipal UserLoggedInConfig userLoggedInConfig) {
        var userLoggedIn = userLoggedInConfig.getUser();
        authService.logout(userLoggedIn);
        return BaseResponse.ok("Berhasil logout", null);
    }

    @PostMapping("register")
    public BaseResponse<?> register(@RequestBody RegisterRequestRecord request) {
        userService.register(request);
        return BaseResponse.ok("Registrasi berhasil, silakan login.", null);
    }

}
