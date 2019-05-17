package galaxy.application.service;

import org.junit.Test;

import galaxy.application.model.RomanNumber;
import galaxy.application.service.InvalidRomanNumberException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RomanToDecimalConverterTest extends RomanToDecimalConverterTestBase
{
	
	@Test
	public void simpleSymbolConvertedWell()  {
		assertThat(romanToDecimalConverter.convert(new RomanNumber("I")),is("1"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("V")),is("5"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("X")),is("10"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("L")),is("50"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("C")),is("100"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("D")),is("500"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("M")),is("1000"));
	}

	@Test
	public void doubleSameSymbolConvertedWell()  {
		assertThat(romanToDecimalConverter.convert(new RomanNumber("II")),is("2"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XX")),is("20"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("CC")),is("200"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("MM")),is("2000"));
	}

	@Test
	public void longerValueConvertedWell()  {
		assertThat(romanToDecimalConverter.convert(new RomanNumber("III")),is("3"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XXX")),is("30"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("CCC")),is("300"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("MMM")),is("3000"));
	}

	@Test
	public void mixedValueConvertedWell()  {
		assertThat(romanToDecimalConverter.convert(new RomanNumber("VI")),is("6"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XVI")),is("16"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("CXXVII")),is("127"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("MMCCCXVIII")),is("2318"));
	}

	@Test
	public void checkSetOfValueWithLowerSymbolBeforeBiggerSymbol()  {
		assertThat(romanToDecimalConverter.convert(new RomanNumber("IV")),is("4"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("IX")),is("9"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XL")),is("40"));
	}

	@Test
	public void checkAListOfDifficultNumberToConvert()  {
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XIV")),is("14"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XC")),is("90"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XCI")),is("91"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XCII")),is("92"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XCIII")),is("93"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XCIV")),is("94"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("XCV")),is("95"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("MCMIII")),is("1903"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("MCMXIII")),is("1913"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("MCMXIX")),is("1919"));
		assertThat(romanToDecimalConverter.convert(new RomanNumber("MCMXLIV")),is("1944"));
	}

	@Test(expected=InvalidRomanNumberException.class)
	public void charOutOfRomanNumerals()  {
		assertThat(romanToDecimalConverter.convert(new RomanNumber("Y")),is("1"));
	}
}
