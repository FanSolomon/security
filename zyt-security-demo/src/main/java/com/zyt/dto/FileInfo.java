/**
 * 
 */
package com.zyt.dto;

import lombok.Data;

/**
 * @author Colin
 *
 */
@Data
public class FileInfo {
	
	public FileInfo(String path) {
		this.path = path;
	}

	private String path;
}
