Feature: API Tests

  Background:
    * url localApi
    * def pathRequests = '/api/v1/requests'
    * def pathHealth = '/api/v1/health'
    * configure headers = { 'Content-Type': 'application/json' }

  Scenario: Catch a post and verify

    # initially empty
    Given path pathRequests
    When method get
    Then status 200
    And match response == '#[0]'

    # Catch a post
    * def randomPath = '/catch/me/ABC'
    Given path randomPath
    And request 'bodyContent'
    When method post
    Then status 200

    # Verify post in detail
    * def responseBody = read('postCatch.json')
    * set responseBody[0].url = localApi + randomPath
    * set responseBody[0].headers.host = host


    Given path pathRequests
    When method get
    Then status 200
    And match response == '#[1]'
    And match response == responseBody
    And assert responseTime < 200

  Scenario: Delete
    # one from scenario before
    Given path pathRequests
    When method get
    Then status 200
    And match response == '#[1]'

   # Delete
    Given path pathRequests
    When method delete
    Then status 200

    # empty
    Given path pathRequests
    When method get
    Then status 200
    And match response == '#[0]'


  Scenario: Catch multiple and count

    # Catch some methods
    Given path '/catch/me'
    When method get
    Then status 200

    Given path '/catch/me'
    When method post
    Then status 200

    Given path '/catch/me'
    When method put
    Then status 200

    Given path '/catch/me'
    When method delete
    Then status 200

    Given path '/catch/me'
    When method patch
    Then status 200

    Given path '/catch/me'
    When method head
    Then status 200

    # count catch
    Given path pathRequests
    When method get
    Then status 200
    And match response == '#[6]'

  Scenario: Health
    Given path pathHealth
    When method get
    Then status 200