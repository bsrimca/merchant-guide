package galaxy.application.usecase.queryMetal;

import java.math.BigDecimal;

import galaxy.application.model.Metal;
import galaxy.application.model.MetalValue;
import galaxy.application.repository.Repository;
import galaxy.application.service.GalacticToDecimalMapping;
import galaxy.application.usecase.SymbolNotFoundException;
import galaxy.application.usecase.UseCaseRequestReply;

public class QueryMetal implements UseCaseRequestReply<QueryMetalRequest, QueryMetalResponse>
{
  private final Repository<Metal,MetalValue> repository;
  private final GalacticToDecimalMapping mapping;

  public QueryMetal(Repository<Metal,MetalValue> repository, GalacticToDecimalMapping mapping)
  {
    this.repository = repository;
    this.mapping = mapping;
  }

  @Override
  public QueryMetalResponse execute(QueryMetalRequest request)
  {
    MetalValue metalValue = repository.findBy(request.metal)
                                .orElseThrow(MetalNotFoundException::new);
    Integer numerosity = mapping.map(request.numerosity)
                                .orElseThrow(SymbolNotFoundException::new);
    BigDecimal calculatedValue = BigDecimal.valueOf(numerosity).multiply(metalValue.value);
    return new QueryMetalResponse(request.metal,
      request.numerosity,calculatedValue);
  }

}
