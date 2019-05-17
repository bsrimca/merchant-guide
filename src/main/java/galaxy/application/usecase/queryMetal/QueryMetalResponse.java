package galaxy.application.usecase.queryMetal;

import java.math.BigDecimal;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.model.Metal;

public class QueryMetalResponse
{

  public final Metal metal;
  public final GalacticSymbol numerosity;
  public final BigDecimal value;

  public QueryMetalResponse(Metal metal, GalacticSymbol numerosity, BigDecimal value)
  {
    this.metal = metal;
    this.numerosity = numerosity;
    this.value = value;
  }

}
