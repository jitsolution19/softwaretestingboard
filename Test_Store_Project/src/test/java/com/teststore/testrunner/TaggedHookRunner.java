package com.teststore.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = { "src/test/resources/features/SingleScenario.feature" }, glue = {
		"com.teststore.Stepdef" }, plugin = {})
public class TaggedHookRunner extends AbstractTestNGCucumberTests {

}
