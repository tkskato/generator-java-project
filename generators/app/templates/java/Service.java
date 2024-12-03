package <%= pkgName %>;

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
public class <%= entityName %>Service {

  private final <%= entityName %>Repository repository;
  private final EntityManager em;

  @Transactional
  public <%= entityName %>Entity save(<%= entityName %>Entity entity) {
    return repository.save(entity);
  }

  public <%= entityName %>Entity find(int id) {
    return findByIdAsResource(repository, id);
  }

  public SearchResultVo<<%= entityName %>Entity> search(SearchConditionVo condition) {
    return SearchUtils.search(em, condition);
  }
}
