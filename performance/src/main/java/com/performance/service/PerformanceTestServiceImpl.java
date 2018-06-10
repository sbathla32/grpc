 package com.performance.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import com.performance.thread.CallingThread;


@Service
public class PerformanceTestServiceImpl implements PerformanceTestService {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PerformanceTestServiceImpl.class);

	@Override
	public String testPerformance() {
		 ExecutorService service = Executors.newCachedThreadPool();
		  long start_time = System.currentTimeMillis();
		    for (int i = 0; i < 500; i++) {
		        service.submit(new CallingThread(i));
		    }
		    service.shutdown();
		    try {
		        service.awaitTermination(1, TimeUnit.HOURS);
		        long difference = (System.currentTimeMillis() - start_time);
		        String result = "Total time taken in milliseconds : " + difference;
		        logger.info(result);
		        return result;
		    } catch (InterruptedException e) {

		    }
		return "failure";
	}

}
