package <%= packageName %>;

import static <%= archBasePkg %>.jpa.JpaRepositoryHandler.findByIdAsResource;

import <%= archBasePkg %>.jpa.SearchUtils;
import <%= archBasePkg %>.search.SearchConditionVo;
import <%= archBasePkg %>.search.SearchResultVo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class <%= domainName %>Service {

  private final <%= domainName %>Repository repository;
  private final EntityManager em;

  @Transactional
  public <%= domainName %>Entity save(<%= domainName %>Entity entity) {
    return repository.save(entity);
  }

  public <%= domainName %>Entity find(int id) {
    return findByIdAsResource(repository, id);
  }

  public SearchResultVo<<%= domainName %>Entity> search(SearchConditionVo condition) {
    return SearchUtils.search(em, condition);
  }
}
