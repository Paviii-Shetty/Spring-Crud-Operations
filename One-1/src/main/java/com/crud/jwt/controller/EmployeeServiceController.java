package com.crud.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crud.jwt.exception.EmployeeNotFoundException;
import com.crud.jwt.model.Employee;
import com.crud.jwt.model.JwtRequest;
import com.crud.jwt.model.JwtResponse;
import com.crud.jwt.service.EmployeeService;
import com.crud.jwt.service.UserService;
import com.crud.jwt.utility.JWTUtility;

@RestController
@Controller
public class EmployeeServiceController {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;


	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/welcome")
	public String home() {
		return "Private Page can't be accessed without JWT";
	}


	@PostMapping("/auth")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUsername(), 
							jwtRequest.getPassword()
							)
					);
			
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS",e);
		}
		
		
		
		
		final UserDetails userDetails=
				userService.loadUserByUsername(jwtRequest.getUsername());
		
		final String token =
				jwtUtility.generateToken(userDetails);
		return new JwtResponse(token);
	}





	//CREATING DATA
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Object>createEmployee(@RequestBody Employee employee){
		employee=employeeService.createEmployee(employee);
		return new ResponseEntity<>("Employee is created Successfully with id = "+employee.getId(),HttpStatus.CREATED);
	}

	//UPDATING DATA
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object>updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
		boolean isEmployeeExist = employeeService.isEmployee(id);
		if(isEmployeeExist) {
			employee.setId(id);
			employeeService.updateEmployee(employee);
			return new ResponseEntity<>("Employee is updated successfully",HttpStatus.OK);

		}
		else {
			throw new EmployeeNotFoundException();
		}
	}


	//GET EMPLOYEE BY ID
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object>getEmployee(@PathVariable("id") int id){
		boolean isEmployeeExist = employeeService.isEmployee(id);
		if(isEmployeeExist) {

			Employee employee=employeeService.getEmployee(id);
			return new ResponseEntity<>(employee,HttpStatus.OK);

		}
		else {
			throw new EmployeeNotFoundException();
		}
	}

	//GET ALL THE EMPLOYEES
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<Object>getEmployees(){
		List<Employee> employeeList=employeeService.getEmployees();
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}


	//DELETE THE EMPLOYEE
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object>deleteEmployee(@PathVariable("id") int id){
		boolean isEmployeeExist = employeeService.isEmployee(id);
		if(isEmployeeExist) {
			employeeService.deleteEmployee(id);

			return new ResponseEntity<>("Employee is deleted successfully",HttpStatus.OK);

		}
		else {
			throw new EmployeeNotFoundException();
		}
	}


}
