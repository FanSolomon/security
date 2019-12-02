/**
 * 
 */
package com.zyt.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zyt.dto.FileInfo;

/**
 * @author Colin
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	
	private String folder = "D:\\EclipseProjects\\security\\zyt-security-demo\\src\\main\\java\\com\\zyt\\web\\controller";

	@PostMapping
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		
		System.out.println("file.getName()"+file.getName());
		System.out.println("file.getOriginalFilename()"+file.getOriginalFilename());
		System.out.println("file.getSize()"+file.getSize());
		
		//将文件重命名为当前时间戳
		File localFile = new File(folder, new Date().getTime() + ".txt");
		
		//将接收到的文件 写入本地的文件
		file.transferTo(localFile);
		
		//文件内容是否为空
		System.out.println("file.isEmpty()---"+file.isEmpty());
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		
		//try的()中声明了输入输出流 这是JDK7的新语法，省去了自己关闭流的过程
		try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
			OutputStream outputStream = response.getOutputStream();){
			
			response.setContentType("application/x-download");
			//设置下载时的文件名
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			
			//将文件的输入流copy到输出流
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
