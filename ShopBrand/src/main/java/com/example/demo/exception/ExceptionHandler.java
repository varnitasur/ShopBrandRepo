package com.example.demo.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Map<String, Object>> exceptionHandler(ControllerException e){
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("Header", "Error message");
		response.put("Error",true);
		response.put("Body", e.getMessage());
		response.put("HTTP Status", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		
	}

}
