@laptop
Feature:Shopping Laptops

  Scenario: Shopping
    Given User goes to the URL.
    When User types Laptops in the searchbox and search it.
    Then User adds the non-discounted products in stock on the first page of the search results to the cart.
    Then User goes to cart and check if the products are the right.
    And Close the application.







