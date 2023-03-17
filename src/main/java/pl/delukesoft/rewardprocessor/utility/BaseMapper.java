package pl.delukesoft.rewardprocessor.utility;

import java.util.List;

public abstract class BaseMapper<T,D> {

  List<D> mapListToDomain(List<T> dtos) {
    return dtos.stream()
        .map(this::mapToDomain)
        .toList();
  }

  List<T> mapListToTransfer(List<D> entities) {
    return entities.stream()
        .map(this::mapToTransfer)
        .toList();
  }

  public abstract D mapToDomain(T dto);
  public abstract T mapToTransfer(D entity);


}
