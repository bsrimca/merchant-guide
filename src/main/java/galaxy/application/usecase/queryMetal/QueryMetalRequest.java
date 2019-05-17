package galaxy.application.usecase.queryMetal;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.model.Metal;

public class QueryMetalRequest
{
  public final Metal metal;
  public final GalacticSymbol numerosity;

  public QueryMetalRequest(Metal metal, GalacticSymbol numerosity)
  {
    this.metal = metal;
    this.numerosity = numerosity;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QueryMetalRequest request = (QueryMetalRequest) o;

    if (metal != request.metal) return false;
    return numerosity.equals(request.numerosity);
  }

  @Override
  public int hashCode()
  {
    int result = metal.hashCode();
    result = 31 * result + numerosity.hashCode();
    return result;
  }
}
