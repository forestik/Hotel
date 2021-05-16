package com.hotels.controller;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.OwnSignInDto;
import com.hotels.dto.OwnSignUpDto;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.exceptions.UserAlreadyRegisteredException;
import com.hotels.genericresponse.GenericResponseDto;
import com.hotels.service.OwnSecurityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.hotels.genericresponse.GenericResponseDto.buildGenericResponseDto;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final OwnSecurityService ownSecurityService;
    private final String appName;

    public HomeController(OwnSecurityService ownSecurityService, @Value("${spring.application.name}")String appName) {
        this.ownSecurityService = ownSecurityService;
        this.appName = appName;
    }


    @GetMapping()
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "core/index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new OwnSignUpDto());
        return "core/registration";
    }

    @PostMapping("/signUp")
    public String singUp(@Valid @RequestBody @ModelAttribute("userForm") OwnSignUpDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "core/registration";
        }
        try {
            ownSecurityService.signUp(dto);
        }
        catch (UserAlreadyRegisteredException e){
            Errors error = bindingResult;
            error.rejectValue("email", "already.registred");
            return "core/registration";
        }
        model.addAttribute("email", dto.getEmail());
        return "core/verify";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userForm", new OwnSignInDto());
        return "core/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userForm") OwnSignInDto dto, Model model, String error, String logout) {
        ownSecurityService.signIn(dto);

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "core/login";
    }

    @PostMapping("/signIn")
    public SuccessSignInDto singIn(@Valid @RequestBody OwnSignInDto dto) {
        return ownSecurityService.signIn(dto);
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "core/welcome";
    }
}
