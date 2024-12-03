package dev.aulait.svqk.domain.hello;

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
public class HelloService {

  private final HelloRepository repository;
  private final EntityManager em;

  @Transactional
  public HelloEntity save(HelloEntity entity) {
    return repository.save(entity);
  }

  public HelloEntity find(int id) {
    return findByIdAsResource(repository, id);
  }

  public SearchResultVo<HelloEntity> search(SearchConditionVo condition) {
    return SearchUtils.search(em, condition);
  }
}
