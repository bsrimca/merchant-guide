package galaxy.application.service;

import org.junit.Test;

import galaxy.application.model.RomanNumber;
import galaxy.application.service.InvalidRomanNumberException;

import static org.junit.Assert.fail;

/**
 * The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more.
 * (They may appear four times if the third and fourth are separated by a smaller value,
 * such as XXXIX.) "D", "L", and "V" can never be repeated
 */
public class RomanToDecimalConverter1StConstraintTest extends RomanToDecimalConverterTestBase
{

  @Test
  public void tooManyTimesInSuccessionForRomanDigits()
  {
    RomanNumber[] list = new RomanNumber[]{
      new RomanNumber("IIII"),
      new RomanNumber("XXXX"),
      new RomanNumber("CCCC"),
      new RomanNumber("MMMM")
    };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException ex)
      {}
    }
  }

  @Test
  public void repetitionOfSpecificDigits()
  {
    RomanNumber[] list = new RomanNumber[]{
      new RomanNumber("DD"),
      new RomanNumber("LL"),
      new RomanNumber("VV"),
    };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException ex)
      {}
    }
  }
}
