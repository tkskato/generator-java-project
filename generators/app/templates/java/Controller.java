package <%= pkgName %>;

import <%= archBasePkg %>.search.SearchConditionVo;
import <%= archBasePkg %>.search.SearchResultDto;
import <%= archBasePkg %>.search.SearchResultVo;
import <%= archBasePkg %>.util.BeanUtils;
import <%= archBasePkg %>.web.ApiPath;
import <%= archBasePkg %>.web.IdDto;
import <%= pkgName %>.IssueEntity;
import <%= pkgName %>.IssueService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;

@Path(<%= entityName %>Controller.ISSUES_PATH)
@RequiredArgsConstructor
public class <%= entityName %>Controller {

  private final <%= entityName %>Service service;

  private final <%= entityName %>Factory factory;

  public static class <%= entityName %>SearchResultDto extends SearchResultDto<<%= entityName %>Dto> {}

  @POST
  public IdDto save(@Valid IssueDto dto) {
    <%= entityName %>Entity entity = BeanUtils.map(dto, <%= entityName %>Entity.class);
    <%= entityName %>Entity savedEntity = service.save(entity);

    return BeanUtils.map(savedEntity, IdDto.class);
  }

  @GET
  @Path(ISSUES_GET_PATH)
  public <%= entityName %>Dto get(@PathParam("issueId") int id) {
    <%= entityName %>Entity entity = service.find(id);

    return BeanUtils.map(entity, <%= entityName %>Dto.class);
  }

  @POST
  @Path(ISSUES_SEARCH_PATH)
  public <%= entityName %>SearchResultDto search(<%= entityName %>SearchConditionDto dto) { // <.>
    SearchConditionVo vo = factory.build(dto);
    SearchResultVo<<%= entityName %>Entity> result = service.search(vo);

    return BeanUtils.map(result, <%= entityName %>SearchResultDto.class);
  }
}
