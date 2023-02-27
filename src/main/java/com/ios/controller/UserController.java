package com.ios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/")
	public String getAllUsers() {
		
	//	ResponseEntity<String> response= restTemplate.exchan("https://gorest.co.in/public/v2/users",HttpMethod.GET ,String.class);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity =new HttpEntity<>(headers);
		
		
		ResponseEntity<String> response = restTemplate.exchange("https://gorest.co.in/public/v2/users", HttpMethod.GET, requestEntity,String.class);
		System.out.println(response);
		 return "Welcome...!";
	}
}
