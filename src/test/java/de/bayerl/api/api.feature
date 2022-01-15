Feature: API Tests

  Background:
    * url localUrl
    * def pathRequests = '/requests'
    * configure headers = { 'Content-Type': 'application/json' }

  Scenario: Catch a post and verify

    # initially empty
    Given path pathRequests
    When method get
    Then status 200
    And match response == '#[0]'

    # Catch a post
    * def randomPath = '/random/catch/me/ABC'
    Given path randomPath
    And request 'bodyContent'
    When method post
    Then status 200
    * def responseBody = read('postCatch.json')
    * set responseBody[0].url = localUrl + randomPath
    * set responseBody[0].headers.host = host

    # Verify post in detail
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
    Given path '/random/catch/me'
    When method get
    Then status 200

    Given path '/random/catch/me'
    When method post
    Then status 200

    Given path '/random/catch/me'
    When method put
    Then status 200

    Given path '/random/catch/me'
    When method delete
    Then status 200

    Given path '/random/catch/me'
    When method patch
    Then status 200

    Given path '/random/catch/me'
    When method head
    Then status 200

    # count catch
    Given path pathRequests
    When method get
    Then status 200
    * print response
    And match response == '#[6]'
