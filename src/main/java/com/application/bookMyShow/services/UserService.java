package com.application.bookMyShow.services;

import com.application.bookMyShow.dtos.userDtos.*;
import com.application.bookMyShow.models.JwtSession;
import com.application.bookMyShow.models.User;
import com.application.bookMyShow.models.enums.Role;
import com.application.bookMyShow.models.enums.TokenStatus;
import com.application.bookMyShow.repositories.JwtSessionRepository;
import com.application.bookMyShow.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ott.InvalidOneTimeTokenException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtSessionRepository jwtSessionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SecretKey secretKey;

    public ResponseEntity<CreateUserResponseDto> saveUser(CreateUserRequestDto request){
        User newUser=CreateUserRequestDto.convertToUser(request.getUser());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setCreated_at(new Date());
        newUser.setUpdated_at(new Date());
        newUser.setIsDeleted(false);

        User savedUser= userRepository.save(newUser);
        CreateUserResponseDto response=new CreateUserResponseDto();
        response.setUser(CreateUserResponseDto.convertToUserResponseDto(savedUser));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<UpdateUserResponseDto> updateUser(UpdateUserRequestDto request,Long id){
        Optional<User> savedUser=userRepository.findById(id);
        UpdateUserResponseDto response=new UpdateUserResponseDto();
        if(savedUser.isPresent()){
            User user=savedUser.get();
            if(StringUtils.hasLength(request.getUser().getName())){
                user.setName(request.getUser().getName());
            }
            if(StringUtils.hasLength(request.getUser().getEmail())){
                user.setEmail(request.getUser().getEmail());
            }
            user.setUpdated_at(new Date());
            user= userRepository.save(user);
            response.setUser(UpdateUserResponseDto.convertTpUserResponseDto(user));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<GetUserResponseDto> getUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        GetUserResponseDto response=new GetUserResponseDto();
        if(user.isPresent()){
            response.setUser(GetUserResponseDto.convertToUserResponseDto(user.get()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<GetUserResponseDtos> getAllUser() {
        List<User> users=userRepository.findAll();
        GetUserResponseDtos response=new GetUserResponseDtos();
        response.setUsers(new ArrayList<>());
        users.forEach(user -> {
            response.getUsers().add(GetUserResponseDtos.convertToUserResponseDto(user));
        });
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    public ResponseEntity<DeletedUserResponseDto> deleteUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        DeletedUserResponseDto response=new DeletedUserResponseDto();
        if(user.isPresent()){
            userRepository.deleteById(id);
            response.setUser(DeletedUserResponseDto.convertToUserResponseDto(user.get()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.PRECONDITION_FAILED);
    }

    public ResponseEntity<CreateUserResponseDto> login(String email, String password) {
        if(email==null||password==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        CreateUserResponseDto response=new CreateUserResponseDto();
        response.setUser(CreateUserResponseDto.convertToUserResponseDto(user.get()));
        if(passwordEncoder.matches(password,user.get().getPassword())){
            //generate token
            String token=generateJwtToken(user.get());

            //Store token into database
            JwtSession jwtSession=new JwtSession();
            jwtSession.setStatus(TokenStatus.ACTIVE);
            jwtSession.setToken(token);
            jwtSession.setUser(user.get());

            jwtSessionRepository.save(jwtSession);

            //Create header
            MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
            headers.add("Set-Cookie",token);
            return new ResponseEntity<>(response,headers,HttpStatus.OK);

//            ResponseCookie cookie = ResponseCookie.from("jwt", token)
//                    .httpOnly(true)          // 🔒 JS cannot access
//                    .secure(false)           // ❗ true only for HTTPS (prod)
//                    .sameSite("Lax")         // best for localhost
//                    .path("/")
//                    .maxAge(Duration.ofDays(1))
//                    .build();
//            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(response);
        }else{
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    private String generateJwtToken(User user) {

        Map<String, Object> claims=new HashMap<>();
        claims.put("userName",user.getName());
        claims.put("userId",user.getId());
        claims.put("expiry",new Date(System.currentTimeMillis()+1000*60*60));
        claims.put("roles",user.getRole());

        return Jwts.builder()
                .addClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) throws InvalidOneTimeTokenException{
        Map<String, Object> claims=extractClaims(token);
        Date date=new Date(System.currentTimeMillis());
        if(date.after((Date) claims.get("expiry"))){
            Optional<JwtSession> session=jwtSessionRepository.findByToken(token);
            session.get().setStatus(TokenStatus.IN_ACTIVE);
            jwtSessionRepository.save(session.get());
            return false;
        }
        return true;
    }

    private Claims extractClaims(String token) throws InvalidOneTimeTokenException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public ResponseEntity<String> logout() {
//        Optional<JwtSession> session=jwtSessionRepository.findByToken(token);
//        if(session.isEmpty()){
//            throw new InvalidOneTimeTokenException("Invalid token");
//        }
//        session.get().setStatus(TokenStatus.IN_ACTIVE);
//        jwtSessionRepository.save(session.get());

        ResponseCookie deleteCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)          // true in prod
                .sameSite("Lax")
                .path("/")
                .maxAge(0)              // 🔥 expires immediately
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString(),"Logout Successfully")
                .build();
    }
}
