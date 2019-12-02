/**
 * 
 */
package com.zyt.web.async;

import java.util.Random;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Colin
 *
 */
@Slf4j
@RestController
public class AsyncController {
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@RequestMapping("/order")
	public DeferredResult<String> order() throws InterruptedException{
		log.info("Main thread begin!");
		
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result = new DeferredResult<String>();
		deferredResultHolder.getMap().put(orderNumber, result);

//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				log.info("副线程开始！");
//				Thread.sleep(1000);
//				log.info("副线程返回！");
//				return "success";
//			}
//		};
		
		log.info("Main thread end!");
		return result;
	}
}
