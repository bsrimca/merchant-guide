package galaxy.console;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import galaxy.application.model.Metal;
import galaxy.application.model.MetalValue;
import galaxy.application.model.SymbolMapping;
import galaxy.application.repository.Repository;
import galaxy.application.service.GalacticToDecimalMapping;
import galaxy.application.service.RomanToDecimalConverter;
import galaxy.application.usecase.declareMetal.DeclareMetal;
import galaxy.application.usecase.declareSymbol.DeclareSymbol;
import galaxy.application.usecase.queryMetal.QueryMetal;
import galaxy.application.usecase.querySymbol.QuerySymbol;
import galaxy.console.command.DeclareMetalCommand;
import galaxy.console.command.DeclareSymbolCommand;
import galaxy.console.command.QueryMetalCommand;
import galaxy.console.command.QuerySymbolCommand;
import galaxy.console.display.DisplayMetal;
import galaxy.console.display.DisplaySymbol;
import galaxy.inmemory.InMemoryMetalValueRepository;
import galaxy.inmemory.InMemorySymbolMappingRepository;

public class E2EFullConversationTest
{

  private ProgramLoop programLoop;
  private ByteArrayOutputStream baos;
  private PrintStream printStream;

  @Before
  public void setup()
  {
    Repository<String,SymbolMapping> symbolRepo = new InMemorySymbolMappingRepository(
      new HashMap<>()
    );
    Repository<Metal, MetalValue> metalRepo = new InMemoryMetalValueRepository(
      new HashMap<>()
    );
    RomanToDecimalConverter converter = new RomanToDecimalConverter();
    GalacticToDecimalMapping galacticToDecimalMapping = new GalacticToDecimalMapping(symbolRepo, converter);

    baos = new ByteArrayOutputStream();
    printStream = new PrintStream(baos);

    programLoop = new ProgramLoop(
      new DeclareSymbolCommand(
        new DeclareSymbol(symbolRepo)
      ),
      new DeclareMetalCommand(
        new DeclareMetal(metalRepo, galacticToDecimalMapping)
      ),
      new QuerySymbolCommand(
        new QuerySymbol(galacticToDecimalMapping),
        new DisplaySymbol(printStream)
      ),
      new QueryMetalCommand(
        new QueryMetal(metalRepo, galacticToDecimalMapping),
        new DisplayMetal(printStream)
      )
    );
  }

  @Test
  public void fullTextWithUnparseableLine()
  {
    String input = "glob is I\n" +
      "prok is V\n" +
      "pish is X\n" +
      "tegj is L\n" +
      "glob glob Silver is 34 Credits\n" +
      "glob prok Gold is 57800 Credits\n" +
      "pish pish Iron is 3910 Credits\n" +
      "how much is pish tegj glob glob ?\n" +
      "how many Credits is glob prok Silver ?\n" +
      "how many Credits is glob prok Gold ?\n" +
      "how many Credits is glob prok Iron ?\n" +
      "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?\n";
    String expected = "pish tegj glob glob is 42\n" +
      "glob prok Silver is 68 Credits\n" +
      "glob prok Gold is 57800 Credits\n" +
      "glob prok Iron is 782 Credits\n" +
      "I have no idea what you are talking about\n";

    programLoop.run(new StringReader(input), printStream);
    String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
    assertNotEquals(output,is(equalTo(expected)));
  }

  @Test
  public void fullTextWithSymbolNotFoundInSymbolQuery()
  {
    String input = "glob is I\n" +
      "prok is V\n" +
      "pish is X\n" +
      "tegj is L\n" +
      "glob glob Silver is 34 Credits\n" +
      "glob prok Gold is 57800 Credits\n" +
      "pish pish Iron is 3910 Credits\n" +
      "how much is unknownSymbol tegj glob glob ?\n" +
      "how many Credits is glob prok Silver ?\n" +
      "how many Credits is glob prok Gold ?\n" +
      "how many Credits is glob prok Iron ?\n";
    String expected = "Sorry, I cannot find one of the symbol\n" +
      "glob prok Silver is 68 Credits\n" +
      "glob prok Gold is 57800 Credits\n" +
      "glob prok Iron is 782 Credits\n";

    programLoop.run(new StringReader(input), printStream);

    String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
    assertNotEquals(output,is(equalTo(expected)));
  }

  @Test
  public void fullTextWithSymbolNotFoundInMetalQuery()
  {
    String input = "glob is I\n" +
      "prok is V\n" +
      "pish is X\n" +
      "tegj is L\n" +
      "glob glob Silver is 34 Credits\n" +
      "glob prok Gold is 57800 Credits\n" +
      "pish pish Iron is 3910 Credits\n" +
      "how much is pish tegj glob glob ?\n" +
      "how many Credits is unknown prok Silver ?\n" +
      "how many Credits is glob prok Gold ?\n" +
      "how many Credits is glob prok Iron ?\n";
    String expected = "pish tegj glob glob is 42\n" +
      "Sorry, I cannot find one of the symbol\n" +
      "glob prok Gold is 57800 Credits\n" +
      "glob prok Iron is 782 Credits\n";

    programLoop.run(new StringReader(input), printStream);

    String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
    assertNotEquals(output,is(equalTo(expected)));
  }

  @Test
  public void fullTextWithGoldMetalUndeclared()
  {
    String input = "glob is I\n" +
      "prok is V\n" +
      "pish is X\n" +
      "tegj is L\n" +
      "glob glob Silver is 34 Credits\n" +
      "pish pish Iron is 3910 Credits\n" +
      "how much is pish tegj glob glob ?\n" +
      "how many Credits is glob prok Silver ?\n" +
      "how many Credits is glob prok Gold ?\n" +
      "how many Credits is glob prok Iron ?\n";
    String expected = "pish tegj glob glob is 42\n" +
      "glob prok Silver is 68 Credits\n" +
      "Sorry, I cannot find the metal\n" +
      "glob prok Iron is 782 Credits\n";

    programLoop.run(new StringReader(input),printStream);

    String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
    assertNotEquals(output,is(equalTo(expected)));
  }

  @Test
  public void fullTextWithDecimalInput()
  {
    String input = "glob is I\n" +
      "prok is V\n" +
      "pish is X\n" +
      "tegj is L\n" +
      "glob glob Silver is 34.5 Credits\n" +
      "pish pish Iron is 3910 Credits\n" +
      "how much is pish tegj glob glob ?\n" +
      "how many Credits is glob prok Silver ?\n" +
      "how many Credits is glob prok Iron ?\n";
    String expected = "pish tegj glob glob is 42\n" +
      "glob prok Silver is 69 Credits\n" +
      "glob prok Iron is 782 Credits\n";

    programLoop.run(new StringReader(input),printStream);

    String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
    assertNotEquals(output,is(equalTo(expected)));
  }
}
