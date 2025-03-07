Feature: Test Hooks

Scenario: This scenario is to test hooks functionality
	Given this is the first step
	When this is the second step
	Then this is the third step
	
	@First 
Scenario: This is First Scenario
	Given this is the first step
	When this is the second step
	Then this is the third step

@Second	
Scenario: This is Second Scenario
	Given this is the first step
	When this is the second step
	Then this is the third step

@Third	
Scenario: This is Third Scenario
	Given this is the first step
	When this is the second step
	Then this is the third step