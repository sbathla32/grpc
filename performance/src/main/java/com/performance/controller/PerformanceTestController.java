package com.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.performance.service.PerformanceTestService;


@RestController
public class PerformanceTestController {
	
	@Autowired
	private PerformanceTestService performanceTestService;

	 @GetMapping(value="/performance/analyzer")
	    public String testPerformance(){
	        return performanceTestService.testPerformance();
	    }
}
