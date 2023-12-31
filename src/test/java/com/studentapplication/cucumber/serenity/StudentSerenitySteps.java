package com.studentapplication.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.serenityrestassured.model.StudentClass;
import com.studentapplication.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {
	
	@Step("Creating student with FirstName: {0}, LastName: {1}, Email: {2}, Programme: {3}, Courses: {4}")
	public ValidatableResponse createStudent(String firstName,String lastName,String email,String programme,
			List<String>courses) {
		
StudentClass student = new StudentClass();
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
	return	SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
		
	}
	
	@Step("Getting the student information by firstname: {0}")
	public HashMap<String,Object> getStudentInfoByFirstName(String firstName){
		
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		return 
				SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path(p1+firstName+p2);
	}
	
	@Step("Updating student information with StudentID:{0}, FirstName: {1}, LastName: {2}, Email: {3}, Programme: {4}, Courses: {5}")
	public ValidatableResponse updateStudent(int studentId,String firstName,String lastName,String email,String programme,
			List<String>courses) {
		
StudentClass student = new StudentClass();
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
	return	SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.put("/"+studentId)
		.then();
		
	}
	
	@Step("Deleting the Student StudentId: {0}")
	public void deleteStudent(int studentId) {
		SerenityRest.rest()
		.given()
		.when()
		.delete("/" + studentId);
	}
	
	@Step("Getting information of the student with ID: {0}")
	public ValidatableResponse getStudentById(int studentId) {
		return SerenityRest.rest()
				.given()
				.when()
				.get("/"+studentId)
				.then();
	}

}
