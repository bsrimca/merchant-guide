package galaxy.console;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

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

public class Main
{
  public static void main(String[] args)
  {

    Repository<String,SymbolMapping> symbolRepo = new InMemorySymbolMappingRepository(
      new HashMap<>()
    );
    Repository<Metal, MetalValue> metalRepo = new InMemoryMetalValueRepository(
      new HashMap<>()
    );
    RomanToDecimalConverter converter = new RomanToDecimalConverter();
    GalacticToDecimalMapping galacticToDecimalMapping = new GalacticToDecimalMapping(symbolRepo, converter);

    Reader reader;
    try
    {
      if(args.length==1)
      {
        reader = new FileReader(args[0]);
      } else
      {
        System.out.println("Welcome to Merchant's Guide to the Galaxy!");
        System.out.println(".. in order to exit from this program you should send a SIGINT (with Ctrl-C for example).");
        reader = new InputStreamReader(System.in);
      }

      new ProgramLoop(
        new DeclareSymbolCommand(
          new DeclareSymbol(symbolRepo)
        ),
        new DeclareMetalCommand(
          new DeclareMetal(metalRepo, galacticToDecimalMapping)
        ),
        new QuerySymbolCommand(
          new QuerySymbol(galacticToDecimalMapping),
          new DisplaySymbol(System.out)
        ),
        new QueryMetalCommand(
          new QueryMetal(metalRepo, galacticToDecimalMapping),
          new DisplayMetal(System.out)
        )
      ).run(reader,System.out);
    }
    catch (FileNotFoundException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
