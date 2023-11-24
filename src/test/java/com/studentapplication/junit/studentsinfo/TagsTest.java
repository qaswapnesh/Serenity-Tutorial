package com.studentapplication.junit.studentsinfo;

import org.junit.Test;

import com.studentapplication.testbase.TestBase;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

public class TagsTest extends TestBase {
	
	@WithTag("studentfeature:NEGATIVE")
	@Title("Verify if incorrect method type is requesting the server, Server throws valid error")
	@Test
	public void inValidMehod() {
		
		SerenityRest.rest()
		.given()
		.when()
		.post("/list")
		.then()
		.statusCode(405).log().all();
	}
	
	@WithTags(
			{
				@WithTag("studentfeature:SMOKE"),
				@WithTag("studentfeature:POSITIVE")
			}
			)
	@Title("Verify if correct method type is requesting the server, Server provides valid response")
	@Test
	public void verifyIfStatuscodeIs200() {
		
		SerenityRest.rest()
		.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	@WithTags(
			{
				@WithTag("studentfeature:SMOKE"),
				@WithTag("studentfeature:NEGATIVE")
			}
			)
	@Title("Verify if incorrect resource has been called, Server throws valid error")
	@Test
	public void incorrectResource() {
		
		SerenityRest.rest()
		.given()
		.when()
		.get("/list223")
		.then()
		.log()
		.all()
		.statusCode(400);
	}

}
