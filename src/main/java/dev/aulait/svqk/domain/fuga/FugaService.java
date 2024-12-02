package dev.aulait.svqk.domain.fuga;

import static dev.aulait.svqk.arch.jpa.JpaRepositoryHandler.findByIdAsResource;

import dev.aulait.svqk.arch.jpa.SearchUtils;
import dev.aulait.svqk.arch.search.SearchConditionVo;
import dev.aulait.svqk.arch.search.SearchResultVo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class FugaService {

  private final FugaRepository repository;
  private final EntityManager em;

  @Transactional
  public FugaEntity save(FugaEntity entity) {
    return repository.save(entity);
  }

  public FugaEntity find(int id) {
    return findByIdAsResource(repository, id);
  }

  public SearchResultVo<FugaEntity> search(SearchConditionVo condition) {
    return SearchUtils.search(em, condition);
  }
}
