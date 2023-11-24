package com.studentapplication.utils;

import java.util.concurrent.TimeUnit;

import org.checkerframework.checker.index.qual.LessThan;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestSpecificaiton;
	
	public static ResponseSpecBuilder respec;
	public static ResponseSpecification responseSpecificaiton;
	
	public static RequestSpecification getGenericRequestSpec() {
		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON);
		requestSpecificaiton = rspec.build();
		return requestSpecificaiton;
	}
	
	public static ResponseSpecification getGenericResponseSpec() {
		respec = new ResponseSpecBuilder();
		respec.expectHeader("Content-Type", "application/json;charset=UTF-8");
		respec.expectHeader("Transfer-Encoding", "chunked");
		responseSpecificaiton = respec.build();
		return responseSpecificaiton;
	}

}
