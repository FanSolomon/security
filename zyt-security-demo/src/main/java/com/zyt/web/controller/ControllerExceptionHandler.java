/**
 * 
 */
package com.zyt.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zyt.exception.UserNotExistException;

/**
 * @author Colin
 *
 */
@ControllerAdvice		//用于表示此类中的方法都是用来处理其他的controller所抛出的异常  其本身不处理http请求
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)		//其他任何controller抛出UserNotExistException异常，都会到此方法中处理
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)	//设置返回的http状态码
	public Map<String, Object> handleUserNotExistException(UserNotExistException userNotExistException) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", userNotExistException.getId());
		result.put("message", userNotExistException.getMessage());
		return result;
	}
}
