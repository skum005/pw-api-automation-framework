@cat_facts
Feature: As a user I should be able to interact with Cat Facts API

  @cat_fact
  Scenario: Get Cat Facts
    Given User has access to cat facts API
    When User queries for a cat fact with a char limit of 100
    Then a cat fact should be returned

    @cat_breed
  Scenario: Get Cat Breeds
    Given User has access to cat facts API
    When User queries for a cat breeds with a limit of 10
    Then cat breeds should be returned