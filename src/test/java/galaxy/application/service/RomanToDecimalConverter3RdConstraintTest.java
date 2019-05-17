package galaxy.application.service;

import org.junit.Test;

import galaxy.application.model.RomanNumber;
import galaxy.application.service.InvalidRomanNumberException;

import static org.junit.Assert.fail;

/**
 * Only one small-value word may be subtracted from any large-value word
 */
public class RomanToDecimalConverter3RdConstraintTest extends RomanToDecimalConverterTestBase
{

  @Test
  public void checkIsubtractedTooManyTimesFromV()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("IIV"),
        new RomanNumber("IIIV"),
        new RomanNumber("IIIIIIIIIV")
      };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkIsubtractedTooManyTimesFromX()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("IIX"),
        new RomanNumber("IIIX"),
        new RomanNumber("IIIIIIIIIX")
      };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkXsubtractedTooManyTimesFromL()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("XXL"),
        new RomanNumber("XXXL"),
        new RomanNumber("XXXXXXXXXXXL")
      };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkXsubtractedTooManyTimesFromC()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("XXC"),
        new RomanNumber("XXXC"),
        new RomanNumber("XXXXXXXXXXXC")
      };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkCsubtractedTooManyTimesFromD()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("CCD"),
        new RomanNumber("CCCD"),
        new RomanNumber("CCCCCCCCCCCD")
      };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }

  @Test
  public void checkCsubtractedTooManyTimesFromM()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("CCM"),
        new RomanNumber("CCCM"),
        new RomanNumber("CCCCCCCCCCCM")
      };
    for(int i=0; i<list.length; i++)
    {
      try
      {
        romanToDecimalConverter.convert(list[i]);
        fail();
      }
      catch (InvalidRomanNumberException e)
      {}
    }
  }
}
