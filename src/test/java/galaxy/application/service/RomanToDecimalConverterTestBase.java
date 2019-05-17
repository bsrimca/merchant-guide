package galaxy.application.service;

import org.junit.Before;

import galaxy.application.model.RomanNumber;
import galaxy.application.service.RomanToDecimalConverter;

public class RomanToDecimalConverterTestBase
{

  protected RomanToDecimalConverter romanToDecimalConverter;

  @Before
  public void setUp()
  {
    romanToDecimalConverter = new RomanToDecimalConverter();
  }
}
