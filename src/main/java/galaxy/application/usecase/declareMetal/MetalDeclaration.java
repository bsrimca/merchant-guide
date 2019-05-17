package galaxy.application.usecase.declareMetal;

import java.math.BigDecimal;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.model.Metal;

public class MetalDeclaration
{
  public GalacticSymbol symbol;
  public Metal metal;
  public BigDecimal creditsValue;

  public MetalDeclaration(GalacticSymbol symbol, Metal metal, BigDecimal creditsValue)
  {
    this.symbol = symbol;
    this.metal = metal;
    this.creditsValue = creditsValue;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MetalDeclaration that = (MetalDeclaration) o;

    if (!symbol.equals(that.symbol)) return false;
    if (metal != that.metal) return false;
    return creditsValue.equals(that.creditsValue);
  }

  @Override
  public int hashCode()
  {
    int result = symbol.hashCode();
    result = 31 * result + metal.hashCode();
    result = 31 * result + creditsValue.hashCode();
    return result;
  }
}
