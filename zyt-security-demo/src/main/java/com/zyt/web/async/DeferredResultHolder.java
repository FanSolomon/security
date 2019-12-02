/**
 * 
 */
package com.zyt.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.Data;

/**
 * @author Colin
 *
 */
@Component
@Data
public class DeferredResultHolder {

	private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

}
