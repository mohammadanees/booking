@tag
Feature: Hotel booking form feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Verify user is able to save the customer details successfully
    Given I am at the hotel booking form page
    And Fill "<firstName>" "<surName>" "<price>" "<deposit>" "<checkIn>" "<checkOut>" 
    When I click on save button
    Then user "<firstName>"and "<surName>" should be added successfully
    Examples:
    	| firstName | surName | price | deposit | checkIn    | checkOut   |
    	|	Aman      | Sh      | 200		| true		|	2021-12-21 | 2021-12-23 |  
    	
  @tag1
  Scenario Outline: Verify user is able to delete the customer details successfully
    Given I am at the hotel booking form page
    And Delete the user details "<firstName>"
    Then user "<firstName>" should be deleted successfully
    Examples:
    	| firstName |
    	|	Aman      |  

