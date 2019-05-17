package galaxy.application.usecase.declareMetal;

import java.math.BigDecimal;
import java.math.BigInteger;

import galaxy.application.model.Metal;
import galaxy.application.model.MetalValue;
import galaxy.application.repository.Repository;
import galaxy.application.service.GalacticToDecimalMapping;
import galaxy.application.usecase.SymbolNotFoundException;
import galaxy.application.usecase.UseCaseRequest;

public class DeclareMetal implements UseCaseRequest<MetalDeclaration>
{

  private final Repository<Metal, MetalValue> metalRepository;
  private final GalacticToDecimalMapping mapping;

  public DeclareMetal(Repository<Metal, MetalValue> metalRepository,
    GalacticToDecimalMapping mapping)
  {
    this.metalRepository = metalRepository;
    this.mapping = mapping;
  }

  @Override
  public void execute(MetalDeclaration declaration)
  {
    Integer numerosity = mapping.map(declaration.symbol)
      .orElseThrow(SymbolNotFoundException::new);
    BigDecimal singleValue = declaration.creditsValue.divide(BigDecimal.valueOf(numerosity));
    MetalValue metalValue = new MetalValue(declaration.metal,singleValue);
    metalRepository.add(metalValue);
  }

}
