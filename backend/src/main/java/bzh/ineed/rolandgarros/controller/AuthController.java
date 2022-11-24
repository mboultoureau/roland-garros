package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.Role;
import bzh.ineed.rolandgarros.model.User;
import bzh.ineed.rolandgarros.security.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    @Operation(summary = "Login")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Logged",
            content = {
                @Content(
                    schemaProperties = {
                        @SchemaProperty(name = "success", schema = @Schema(type="boolean", example = "true")),
                        @SchemaProperty(name = "username", schema = @Schema(type="string")),
                        @SchemaProperty(name = "email", schema = @Schema(type="string")),
                        @SchemaProperty(name = "accessToken", schema = @Schema(type="string")),
                        @SchemaProperty(name = "roles", array = @ArraySchema(schema = @Schema(type = "string"))),
                    }
                )
            }),
        @ApiResponse(
            responseCode = "401",
            description = "Bad credentials",
            content = {
                @Content(
                    schemaProperties = {
                        @SchemaProperty(name = "success", schema = @Schema(type="boolean", example = "false")),
                        @SchemaProperty(name = "error", schema = @Schema(type="string", example = "Bad credentials"))
                    }
                )
            }
        )
    })
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("accessToken", accessToken);

            ArrayList<String> roles = new ArrayList<String>();
            for (Role role: user.getRoles()) {
                roles.add(role.toString());
            }
            response.put("roles", roles);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", "Bad credentials");

            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
