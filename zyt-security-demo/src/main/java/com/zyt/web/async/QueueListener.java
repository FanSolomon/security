/**
 * 
 */
package com.zyt.web.async;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Colin
 *
 */
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		new Thread(() -> {
			while (true) {
				if (StringUtils.isNoneBlank(mockQueue.getCompleteOrder())) {
					
					String orderNumber = mockQueue.getCompleteOrder();
					log.info("返回订单处理结果："+ orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success!");
					mockQueue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();;

	}
	
}
