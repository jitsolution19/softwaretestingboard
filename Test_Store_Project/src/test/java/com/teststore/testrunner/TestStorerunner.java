package com.teststore.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = { "src/test/resources/features/End_to_End_flow.feature" }, glue = {
		"com.teststore.Stepdef" }, plugin = {})
public class TestStorerunner extends AbstractTestNGCucumberTests {

}
