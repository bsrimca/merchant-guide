package galaxy.application.usecase;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import galaxy.application.model.RomanDigit;
import galaxy.application.model.SymbolMapping;
import galaxy.application.repository.Repository;
import galaxy.application.usecase.declareSymbol.DeclareSymbol;

public class DeclareSymbolTest
{
  private DeclareSymbol underTest;

  @Rule
  public JUnitRuleMockery mockery = new JUnitRuleMockery();

  @Mock
  private Repository<String,SymbolMapping> repository;

  @Before
  public void setup()
  {
    underTest = new DeclareSymbol(repository);
  }

  @Test
  public void whenADeclarationIsPerformed_symbolIsAddedToTheRepo()
  {
    SymbolMapping request = new SymbolMapping("word", RomanDigit.D);

    mockery.checking(new Expectations()
    {{
      oneOf(repository).add(with(request));
    }});
    underTest.execute(request);
  }

}
