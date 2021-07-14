package com.hotels.controller;

import com.hotels.constant.HttpStatuses;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.service.GoogleSecurityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

import static com.hotels.constant.ErrorMessage.BAD_GOOGLE_TOKEN;

/**
 * Controller that provide google security logic.
 *
 */
@RestController
@RequestMapping("/googleSecurity")
@Validated
@AllArgsConstructor
public class GoogleSecurityController {
    private final GoogleSecurityService googleSecurityService;

    /**
     * Method that provide authenticate with google token.
     *
     * @param idToken {@link String} - google idToken
     * @return {@link ResponseEntity} of {@link SuccessSignInDto} if token valid
     */
    @ApiOperation("Make authentication by Google")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = SuccessSignInDto.class),
        @ApiResponse(code = 400, message = BAD_GOOGLE_TOKEN)
    })
    @GetMapping("/{idToken}")
    public ResponseEntity<SuccessSignInDto> authenticate(@PathVariable @NotBlank String idToken) {
        return ResponseEntity.status(HttpStatus.OK).body(googleSecurityService.authenticate(idToken));
    }
}
