Feature: Currency converter in transferwise

  Scenario Outline: Conversion between a source and target currency is as per the conversion rates
    Given The transferwise currency converter <url> is launched in <browser_type>
    When The <source_currency> and <target_currency> is entered
    And The <amount> to be converted is entered
    And convert button is clicked
    Then The converted-to value is as per the given <conversion_rate>

    Examples: 
      | url                                            | browser_type | source_currency | target_currency | amount | conversion_rate |
      | https://transferwise.com/gb/currency-converter | FIREFOX      | GBP             | EUR             |      1 |         1.15841 |
      | https://transferwise.com/gb/currency-converter | CHROME       | GBP             | EUR             |      1 |         1.15841 |
      | https://transferwise.com/gb/currency-converter | IE           | GBP             | EUR             |      1 |         1.15841 |
      | https://transferwise.com/gb/currency-converter | FIREFOX      | EUR             | GBP             |      1 |         0.86325 |
      | https://transferwise.com/gb/currency-converter | CHROME       | EUR             | GBP             |      1 |         0.86325 |
      | https://transferwise.com/gb/currency-converter | IE           | EUR             | GBP             |      1 |         0.86325 |
