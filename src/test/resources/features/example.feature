Feature: Open Application and Navigate Between Sections

  @NavigationIOS
  Scenario: Navigate to Brands and Categories on iOS
    Given Wait 5 seconds
    Then Element with xpath "Xpath" is clicked
    Then Wait 2 seconds
    Then Element with xpath "Xpath" is clicked
    Then Wait 2 seconds



  @NavigationAndroid
  Scenario: Navigate to Brands, Categories, and Campaigns on Android
    Given Wait 5 seconds
    Then Element with id "Xpath" is clicked
    Then Wait 2 seconds
    Then Element with id "Xpath" is clicked
    Then Wait 2 seconds
    Then Element with id "Xpath" is clicked
    Then Wait 2 seconds

