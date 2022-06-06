package com.todo.controller;

import com.todo.entity.UserEntity;
import com.todo.model.GeneralResponse;
import com.todo.service.UserService;
import com.todo.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<GeneralResponse<UserEntity>> singUp(@RequestBody UserEntity user) {
        GeneralResponse<UserEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        UserEntity data = null;

        System.out.println("Cuerpo: " + user);

        try {
            user.setUser_password(bCryptPasswordEncoder.encode(user.getUser_password()));
            data = userService.saveUser(user);

            response.setMessage("Usuario creado correctamente");
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.OK;

        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage("Algo ha salido mal. Error: " + e.getLocalizedMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<UserEntity>> login(@RequestBody UserEntity user) {
        GeneralResponse<UserEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        String messageResult = "";

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getUser_password()));
            user.setJwt(jwtUtils.generateToken(user.getUsername()));
            user.setUser_password(null);
            messageResult = "Inicio se sesión correcto para el usuario: " + user.getUsername();

            response.setToken(user.getJwt());
            response.setMessageResult(messageResult);
            response.setMessage("Éxito");
            response.setErrorCode(1);
            response.setSuccess(true);
            response.setData(user);
            status = HttpStatus.CREATED;
        } catch (AuthenticationException authex) {
            String message = "Incorrect user or password.";
            status = HttpStatus.FORBIDDEN;
            response.setMessage(message);
            response.setSuccess(false);
            response.setErrorCode(0);
        } catch (Exception e) {
            String message = "Something went wrong. Please contact support.";
            status = HttpStatus.FORBIDDEN;
            response.setMessage(message);
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<List<UserEntity>>> getUser() {

        GeneralResponse<List<UserEntity>> response = new GeneralResponse<>();
        HttpStatus status = null;
        List<UserEntity> data = null;

        try {

            data = userService.getLoggedUser();
            String msg = "It found " + data.size() + " users.";

            response.setMessage(msg);
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.OK;

        } catch (Exception e) {

            String msg = "Something has failed. Please contact suuport.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage(msg);
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponse<UserEntity>> getUser(@PathVariable("id") Integer id) {
        GeneralResponse<UserEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        UserEntity data = null;
        String message = "";

        try {
            data = userService.getUserById(id);

            if (data == null) {
                response.setErrorCode(1);
                response.setMessageResult("No se encontaron resultados.");
            } else {
                response.setErrorCode(0);
                response.setMessageResult("El usuario fue econtrado");
            }

            message = "Transacción correcta";
            response.setMessage(message);
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.OK;

        } catch (Exception e) {
            message = "Something has failed. Please contact suuport.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage(message);
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<GeneralResponse<UserEntity>> updateUser(@RequestBody UserEntity user, @PathVariable("id") Integer id) {
        GeneralResponse<UserEntity> response = new GeneralResponse<>();
        HttpStatus status = null;
        UserEntity data = null;
        String message = "";

        try {
            data = userService.getUserById(id);
            data.setUser_fullname(user.getUser_fullname());
            data.setUsername(user.getUsername());
            data.setUser_avatar(user.getUser_avatar());
            user.setUser_password(bCryptPasswordEncoder.encode(user.getUser_password()));
            data.setUser_password(user.getUser_password());

            userService.saveUser(data);
            message = "Usuario actualizado correctamente";

            response.setMessage(message);
            response.setSuccess(true);
            response.setData(data);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            message = "Something has failed. Please contact suuport.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage(message);
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GeneralResponse<Integer>> deleteUser(@PathVariable("id") Integer id) {
        GeneralResponse<Integer> response = new GeneralResponse<>();
        HttpStatus status = null;

        try {

            userService.deleteUserById(id);
            response.setErrorCode(0);
            response.setMessageResult("Usuario eliminado correctamente");

            response.setMessage("Successful transaction");
            response.setSuccess(true);
            response.setData(id);
            status = HttpStatus.OK;

        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setMessage("Something has failed. Please contact suuport.");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

}
