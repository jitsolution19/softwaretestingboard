Feature: Validate user is able to purchase the product successfully 
  Background: 
    Given User information is extracted from the API
  
  Scenario: Validate user is able to purchase jacket successfully 
    Given user launch the test store website 
     When user register in the application with the information received from API 
      And user navigate to jacket section page select the jacket 
      And user enter the billing address and proceed to place order
      And user click on place order button 
     Then user validate that the jacket order is placed successfully and order number is generated