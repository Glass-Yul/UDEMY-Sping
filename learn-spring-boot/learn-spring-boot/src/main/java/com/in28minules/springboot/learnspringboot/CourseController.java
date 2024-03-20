package com.in28minules.springboot.learnspringboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	
	@RequestMapping("/courses")
	public List<Coures> retrieveAllCourses(){
		return Arrays.asList(
				new Coures(1, "Learn AWS", "in28minutes"),
				new Coures(2, "Learn DevOps", "in28minutes"),
				new Coures(3, "Learn Azure", "in28minutes"),  
				new Coures(4, "Learn WAS", "in28minutes")
				);
	}
	
}
