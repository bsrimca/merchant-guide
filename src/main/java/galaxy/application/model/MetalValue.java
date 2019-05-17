package galaxy.application.model;

import java.math.BigDecimal;

public class MetalValue
{
  public Metal metal;
  public BigDecimal value;

  public MetalValue(Metal metal, BigDecimal value)
  {
    this.metal = metal;
    this.value = value;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MetalValue that = (MetalValue) o;

    if (!metal.equals(that.metal)) return false;
    return value.equals(that.value);
  }

  @Override
  public int hashCode()
  {
    int result = metal.hashCode();
    result = 31 * result + value.hashCode();
    return result;
  }
}
