package com.studentapplication.junit.studentsinfo;

import java.util.ArrayList;

import java.util.HashMap;

import org.hamcrest.MatcherAssert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.serenityrestassured.model.StudentClass;
import com.studentapplication.cucumber.serenity.StudentSerenitySteps;
import com.studentapplication.testbase.TestBase;
import com.studentapplication.utils.ReusableSpecifications;
import com.studentapplication.utils.TestUtils;

import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {
	
	static String firstName = "Swapnesh"+TestUtils.getRandomValue();
	static String lastName = "Kunjir"+TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue()+"swapnesh@gmail.com";
	static int studentId;
	
	@Steps 
	StudentSerenitySteps steps;
	
	@Title("This test will create a new student")
	@Test
	public void test001() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201)
		.spec(ReusableSpecifications.getGenericResponseSpec());
	
	}

	@Title("Verify if the student was added to the application")
	@Test
	public void test002() {

		HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
		MatcherAssert.assertThat(value,hasValue(firstName));
		studentId = (int) value.get("id");
	}
	
	@Title("Update the user information and verify updated information")
	@Test
	public void test003() {
		
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		firstName = firstName + "_Updated";
steps.updateStudent(studentId, firstName, lastName, email, programme, courses);
		
		HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
				MatcherAssert.assertThat(value,hasValue(firstName));
	}
	
	@Title("Delete the student and verify if student is there")
	@Test
	public void test004() {
		
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).statusCode(404);
	}
}
