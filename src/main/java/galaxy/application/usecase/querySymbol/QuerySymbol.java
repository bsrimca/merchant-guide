package galaxy.application.usecase.querySymbol;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.service.GalacticToDecimalMapping;
import galaxy.application.usecase.SymbolNotFoundException;
import galaxy.application.usecase.UseCaseRequestReply;

public class QuerySymbol implements UseCaseRequestReply<GalacticSymbol,QuerySymbolResponse>
{

  private final GalacticToDecimalMapping mapping;

  public QuerySymbol(GalacticToDecimalMapping mapping)
  {
    this.mapping = mapping;
  }

  public QuerySymbolResponse execute(GalacticSymbol request)
  {
    Integer value = mapping.map(request).orElseThrow(SymbolNotFoundException::new);
    return new QuerySymbolResponse(request, value);
  }

}
