/**
 * 
 */
package com.zyt.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Colin
 *
 */
public class MockServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		//指定端口号
		configureFor(8066);
		//清空以前的配置
		removeAllMappings();
		
		mock("/order/1", "01");
	}

	private static void mock(String url, String file) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/respons/" + file + ".txt");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
		//urlPathEqualTo用于完整路径
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
	}

}
