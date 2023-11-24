package com.studentapplication.junit.studentsinfo;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.*;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8085/student";
	}
	
	@Test
	public void getAllStudents() {
		SerenityRest.given()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	
	@Test
	public void thisIsAFailingTest() {
		SerenityRest.given()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(500);
	}
	
	@Pending
	@Test
	public void thisIsAPendingTest() {
		
	}
	
	@Ignore
	@Test
	public void thisIsASkippedTest() {}
	
	@Test
	public void thisIsATestWithError() {
		System.out.println("This is Error" + (5/0));
	}
	
	@Test
	public void fileDoesNotExists() throws FileNotFoundException  {
		File file= new File("E://file1.txt");
		FileReader fr = new FileReader(file);
	}
	
	@Manual
	@Test
	public void thisIsAManualTest() {
		
	}
	
	@Title("This test will bring information of all students from the students app")
	@Test
	public void test01() {
		SerenityRest.given()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}

}
