package com.assignment.lab2.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.lab2.entity.AddressEntity;
import com.assignment.lab2.entity.*;
import com.assignment.lab2.service.EmployeeService;
import com.assignment.lab2.service.EmployerService;

@RestController
@RequestMapping("/")
//@ResponseStatus(HttpStatus.NOT_FOUND)
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	@Autowired
	EmployeeService EmployeeService;
	
	@Autowired
	EmployerService EmployerService;
	
	@RequestMapping(value="employee", method=RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> addEmployee(
			@RequestParam(value="name",required = true) String name,
			@RequestParam(value="email",required = true) String email,
			@RequestParam(value="title",required = false) String title,
			 @RequestParam(value="street",required=false) String street,
    		 @RequestParam(value="city",required=false) String city, 
    		 @RequestParam(value="state",required=false) String state,
    		 @RequestParam(value="zip", required=false) String zip,
    		 @RequestParam(required = true) long EmployerID,
    		 @RequestParam(required = false) Long ManagerID
			)
	{
		
        AddressEntity address = new AddressEntity(city,street,state,zip);
        
        EmployerEntity employer = this.EmployerService.GetEmployer(EmployerID);
        if(employer==null)
        	return new ResponseEntity<>("Not A Valid Employeer,It is not created earlier",HttpStatus.BAD_REQUEST);
        
        
		Employee employee = new Employee();
		return new ResponseEntity<Employee>(this.EmployeeService.AddEmployee(employee),HttpStatus.OK);
	}

}
