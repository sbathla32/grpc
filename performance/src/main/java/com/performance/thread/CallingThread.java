package com.performance.thread;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;



public class CallingThread implements Runnable {
	private int id;
	private HashMap<Long, Long> histogram;
	private static final Logger logger = LogManager.getLogger(CallingThread.class.getName());
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public void run() {
		long start_time = System.currentTimeMillis();
			try {
				restCall(id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		long difference = (System.currentTimeMillis() - start_time);

		Long count = histogram.get(difference);
		if (count != null) {
			count++;
			histogram.put(Long.valueOf(difference), count);
		} else {
			histogram.put(Long.valueOf(difference), Long.valueOf(1L));
		}

		//logger.info(histogram.toString());
	}

	public CallingThread(int id) {
		this.id = id;
	}

	private void restCall(int threadId) throws InterruptedException {
		String response = restTemplate.getForObject("http://localhost:8080/getEmployee/1",String.class);
		logger.info("Thread number -> "+threadId+" Greeting: -> "+ response);
	}

}
