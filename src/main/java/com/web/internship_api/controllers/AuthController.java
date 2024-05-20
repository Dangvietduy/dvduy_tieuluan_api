package com.web.internship_api.controllers;

import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.internship_api.config.JwtUtil;
import com.web.internship_api.entities.Account;
import com.web.internship_api.models.AccountModel;
import com.web.internship_api.models.AccountResponse;
import com.web.internship_api.models.ResponseObject;
import com.web.internship_api.services.AccountService;
import com.web.internship_api.services.EmailService;
import com.web.internship_api.services.ReportService;
import com.web.internship_api.services.StudentService;
import com.web.internship_api.services.impl.UserDetailServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AccountService accountService;
	@Autowired
	StudentService studentService;
	@Autowired
	UserDetailServiceImpl userDetailServiceimpl;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	ReportService reportService;
	@Autowired
	EmailService emailService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseObject> login(@RequestBody AccountModel accountModel,final HttpServletRequest request){
		UserDetails userDetails = userDetailServiceimpl.loadUserByUsername(accountModel.getUsername());
		if(passwordEncoder.matches(accountModel.getPassword(), userDetails.getPassword())) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!"anonymousUser".equalsIgnoreCase(authentication.getName())) {
				String cookie = jwtUtil.generateToken(userDetails);
				Account account = accountService.findAccountByUserName(userDetails.getUsername()).get();
				AccountResponse res =new AccountResponse(
						account.getId(),
						account.getUsername(),
						cookie,
						account.getAccountsRoles().get(0).getRole().getName());
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Login successfully", res));
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(400, "Wrong username or password",""));
	}
	@PostMapping("/password")
	public ResponseEntity<ResponseObject> chargePassword(@RequestBody AccountModel accountModel){
		Account accout= accountService.updateAccount(accountModel);
		if(accout != null )
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Successfully", accout));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found account", ""));
	}
	
	@PostMapping("/forgetpassword")
	public ResponseEntity<ResponseObject> forgetPassword(@RequestBody AccountModel accountModel){
		Optional<Account> account = accountService.findAccountByUserName(accountModel.getUsername());
		if(account.isPresent()) {
			String password = randomPassword();
			String subject = "Send Email And Password";
			String body = "Email: "+ account.get().getUsername() + ". Password: "+password;
			try {
				emailService.sendMail(account.get().getUsername(), subject , body);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			account.get().setPassword(passwordEncoder.encode(password));
			accountService.forgetPassword(account.get());
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Successfully",""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Not found account", ""));
	}
	
	private String randomPassword() {
		int length = 8;
	    String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
	}
}
