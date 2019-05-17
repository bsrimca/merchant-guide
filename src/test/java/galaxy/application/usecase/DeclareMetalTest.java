package galaxy.application.usecase;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.model.Metal;
import galaxy.application.model.MetalValue;
import galaxy.application.repository.Repository;
import galaxy.application.service.GalacticToDecimalMapping;
import galaxy.application.service.RomanToDecimalConverter;
import galaxy.application.usecase.SymbolNotFoundException;
import galaxy.application.usecase.declareMetal.DeclareMetal;
import galaxy.application.usecase.declareMetal.MetalDeclaration;
import galaxy.util.JUnitRuleClassMockery;

import java.math.BigDecimal;
import java.util.Optional;

public class DeclareMetalTest
{
  private DeclareMetal underTest;

  @Rule
  public JUnitRuleMockery mockery = new JUnitRuleClassMockery();

  @Mock
  private Repository<Metal,MetalValue> repositoryMetal;
  @Mock
  private GalacticToDecimalMapping mapping;
  @Mock
  private RomanToDecimalConverter converter;

  @Before
  public void setup()
  {
    underTest = new DeclareMetal(repositoryMetal,
      mapping);
  }

  @Test(expected = SymbolNotFoundException.class)
  public void whenASymbolIsMissing_throwException()
  {
    GalacticSymbol symbol = new GalacticSymbol(new String[] {"missing"});
    MetalDeclaration request = new MetalDeclaration(symbol
      , Metal.Silver, BigDecimal.valueOf(34)
    );

    mockery.checking(new Expectations()
    {{
      oneOf(mapping).map(symbol);
      will(returnValue(Optional.empty()));
    }});
    underTest.execute(request);
  }

  @Test
  public void whenADeclarationIsPerformed_aGalacticSymbolIsNeeded()
  {
    GalacticSymbol symbol = new GalacticSymbol(new String[] {"glob", "glob"});
    MetalDeclaration request = new MetalDeclaration(symbol
      , Metal.Silver,BigDecimal.valueOf(34)
    );
    MetalValue metalValue = new MetalValue(
      Metal.Silver,BigDecimal.valueOf(34).divide(BigDecimal.valueOf(2))
    );

    mockery.checking(new Expectations()
    {{
      oneOf(mapping).map(symbol);
      will(returnValue(Optional.of(2)));
      allowing(repositoryMetal).add(with(metalValue));
    }});
    underTest.execute(request);
  }

  @Test
  public void whenADeclarationIsPerformed_metalIsAddedToTheRepo()
  {
    GalacticSymbol symbol = new GalacticSymbol(new String[] {"glob", "glob"});
    MetalDeclaration request = new MetalDeclaration(symbol
      ,Metal.Silver,BigDecimal.valueOf(34)
    );
    MetalValue metalValue = new MetalValue(
      Metal.Silver,BigDecimal.valueOf(34).divide(BigDecimal.valueOf(2))
    );

    mockery.checking(new Expectations()
    {{
      oneOf(mapping).map(symbol);
      will(returnValue(Optional.of(2)));
      oneOf(repositoryMetal).add(with(metalValue));
    }});
    underTest.execute(request);
  }

}
