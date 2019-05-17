package galaxy.application.service;

import org.junit.Test;

import galaxy.application.model.RomanNumber;
import galaxy.application.service.InvalidRomanNumberException;

import static org.junit.Assert.fail;

/**
 * "I" can be subtracted from "V" and "X" only.
 * "X" can be subtracted from "L" and "C" only.
 * "C" can be subtracted from "D" and "M" only.
 * "V", "L", and "D" can never be subtracted.
 */
public class RomanToDecimalConverter2ndConstraintTest extends RomanToDecimalConverterTestBase
{

  @Test
  public void checkIcanBeSubtractedFromVandXonly()
  {
    RomanNumber[] list = new RomanNumber[]
    {
      new RomanNumber("IL"),
      new RomanNumber("IC"),
      new RomanNumber("ID"),
      new RomanNumber("IM"),
      new RomanNumber("IL")
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
  public void checkXcanBeSubtractedFromLandConly()
  {
    RomanNumber[] list = new RomanNumber[]
    {
      new RomanNumber("XD"),
      new RomanNumber("XM"),
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
  public void checkVneverBeSubtracted()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("VX"),
        new RomanNumber("VL"),
        new RomanNumber("VC"),
        new RomanNumber("VD"),
        new RomanNumber("VM"),
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
  public void checkLneverBeSubtracted()
  {
    RomanNumber[] list = new RomanNumber[]
      {
        new RomanNumber("LC"),
        new RomanNumber("LD"),
        new RomanNumber("LM"),
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
  public void checkDneverBeSubtracted()
  {
    try
    {
      romanToDecimalConverter.convert(new RomanNumber("DM"));
      fail();
    }
    catch (InvalidRomanNumberException e)
    {}
  }

}
