package galaxy.application.usecase.declareSymbol;

import galaxy.application.model.SymbolMapping;
import galaxy.application.repository.Repository;
import galaxy.application.usecase.UseCaseRequest;

public class DeclareSymbol implements UseCaseRequest<SymbolMapping>
{

  private final Repository<String,SymbolMapping> repository;

  public DeclareSymbol(Repository<String,SymbolMapping> repository)
  {
    this.repository = repository;
  }

  public void execute(SymbolMapping mapping)
  {
    repository.add(mapping);
  }

}
