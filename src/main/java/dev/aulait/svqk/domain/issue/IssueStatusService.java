package dev.aulait.svqk.domain.issue;

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
public class IssueStatusService {

  private final IssueStatusRepository repository;
  private final EntityManager em;

  @Transactional
  public IssueStatusEntity save(IssueStatusEntity entity) {
    return repository.save(entity);
  }

  public IssueStatusEntity find(int id) {
    return findByIdAsResource(repository, id);
  }

  public SearchResultVo<IssueStatusEntity> search(SearchConditionVo condition) {
    return SearchUtils.search(em, condition);
  }
}
