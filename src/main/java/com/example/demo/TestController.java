package com.example.demo;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Employee.Employee;
import com.example.demo.Employee.EmployeeRepository;
import com.example.demo.Manager.Manager;
import com.example.demo.Manager.ManagerRepository;

@RestController

public class TestController {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ManagerRepository managerRepository;

	@RequestMapping(value="/primary", method=RequestMethod.GET)
	public List<Employee> getPrimaryDatabaseData() {
		List<Employee> list = employeeRepository.findAll();
		return list;
	}
	
	@RequestMapping(value="/secondary", method=RequestMethod.GET)
	public List<Manager> getSecondaryDatabaseData() {
		List<Manager> list = managerRepository.findAll();
		return list;
	}
	@RequestMapping(value="/primarysecondary", method=RequestMethod.GET)
	public List<Manager> saveAll() {
		List<Employee> list=employeeRepository.findAll();
		List<Manager> managers=new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			Manager m=new Manager();
			m.setId(list.get(i).getId());
			m.setName(list.get(i).getName());
			m.setSalary(list.get(i).getSalary());
			managers.add(m);
		}
			managerRepository.saveAll(managers);
			return null;
	}
	
	
}
	
