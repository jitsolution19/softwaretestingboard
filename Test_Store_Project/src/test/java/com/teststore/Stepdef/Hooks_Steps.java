package com.teststore.Stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Hooks_Steps {
	@Given("^this is the first step$")
	public void This_Is_The_First_Step() {
		System.out.println("This is the first step");
	}

	@When("^this is the second step$")
	public void This_Is_The_Second_Step() {
		System.out.println("This is the second step");
	}

	@Then("^this is the third step$")
	public void This_Is_The_Third_Step() {
		System.out.println("This is the third step");
	}

	@Before("@First")
	public void beforeFirst() {
		System.out.println("This will run only before the First Scenario");
	}

	@Before("@Second")
	public void beforeSecond() {
		System.out.println("This will run only before the Second Scenario");
	}

	@Before("@Third")
	public void beforeThird() {
		System.out.println("This will run only before the Third Scenario");
	}

	@After("@First")
	public void afterFirst() {
		System.out.println("This will run only after the First Scenario");
	}

	@After("@Second")
	public void afterSecond() {
		System.out.println("This will run only after the Second Scenario");
	}

	@After("@Third")
	public void afterThird() {
		System.out.println("This will run only after the Third Scenario");
	}
}
