package com.test.lotte;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.test.lotte.config.MybatisConfiguration;
@Import(MybatisConfiguration.class)
@SpringBootApplication
public class SonseongjunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonseongjunApplication.class, args);
	}
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : responseBodyConverter
		 * @Desc   : 클라이언트 단으로 보낼 때 UTF-8 설정
		 * ----------------------------------- 
		 * @return : HttpMessageConverter<String>
		 *
	 */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
    /**
     * 
    	 *
    	 * @Auth   : 손성준
    	 * @Since  : 2018. 6. 7.
    	 * @Method : characterEncodingFilter
    	 * @Desc   : 서버 단으로 받을 때 UTF-8 설정
    	 * ----------------------------------- 
    	 * @return : Filter
    	 *
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
