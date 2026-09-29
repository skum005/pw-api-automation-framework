@hr_api
Feature: As a HR I should be able to interact with Human Resources API

  Scenario Outline: As a HR I should be able to create employees
    Given HR has required access to HR API
    When a request is submitted to create a HR
      | NAME   | JOB   |
      | <NAME> | <JOB> |
    Then employee should be created successfully

    Examples:
      | NAME  | JOB             |
      | John  | DevOps Engineer |
      | Carol | Scrum Master    |