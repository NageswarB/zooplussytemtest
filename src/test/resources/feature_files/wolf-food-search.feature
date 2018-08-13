Feature: Wolf Food Product Search

  @system-test
  Scenario Outline: Verify Top Header is Highlighted and Asset Filter Selection Are Correct
    Given that I launch application in browser
    When I select country "<country>"
    When I navigate to app with asset filters "<foodCategory>" and "<foodFlavour>"
    Then I should see the top navigation "<foodCategory>" highlighted
    And I see the products matching with "<foodFlavour>" asset filters

    Examples: 
      | country     | foodCategory | foodFlavour |
      | Deutschland | Nassfutter   | Lamm        |
 
