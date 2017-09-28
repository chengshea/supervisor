package com.cs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.cs.controller"))
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.build()/*.enable(false)*/;
	}

	private ApiInfo apiInfo() {
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo("Electronic Health Record(EHR) Platform API", // 大标题
				"EHR Platform's REST API, for system administrator", // 小标题
				"1.0", // 版本
				"NO terms of service", "作者邮箱", // 作者
				"网站链接显示文字", // 链接显示文字
				"http://www.apache.org/licenses/LICENSE-2.0.html"// 网站链接
		);

		return apiInfo;
	}


}