package <%= packageName %>;

import <%= archBasePkg %>.search.SearchConditionVo;
import <%= archBasePkg %>.search.SearchResultDto;
import <%= archBasePkg %>.search.SearchResultVo;
import <%= archBasePkg %>.util.BeanUtils;
import <%= archBasePkg %>.web.ApiPath;
import <%= archBasePkg %>.web.IdDto;
import <%= targetPkgDomain %>.IssueEntity;
import <%= targetPkgDomain %>.IssueService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;

@Path(<%= domainName %>Controller.ISSUES_PATH)
@RequiredArgsConstructor
public class <%= domainName %>Controller {

  private final <%= domainName %>Service service;

  private final <%= domainName %>Factory factory;

  public static class <%= domainName %>SearchResultDto extends SearchResultDto<<%= domainName %>Dto> {}

  @POST
  public IdDto save(@Valid IssueDto dto) {
    <%= domainName %>Entity entity = BeanUtils.map(dto, <%= domainName %>Entity.class);
    <%= domainName %>Entity savedEntity = service.save(entity);

    return BeanUtils.map(savedEntity, IdDto.class);
  }

  @GET
  @Path(ISSUES_GET_PATH)
  public <%= domainName %>Dto get(@PathParam("issueId") int id) {
    <%= domainName %>Entity entity = service.find(id);

    return BeanUtils.map(entity, <%= domainName %>Dto.class);
  }

  @POST
  @Path(ISSUES_SEARCH_PATH)
  public <%= domainName %>SearchResultDto search(<%= domainName %>SearchConditionDto dto) { // <.>
    SearchConditionVo vo = factory.build(dto);
    SearchResultVo<<%= domainName %>Entity> result = service.search(vo);

    return BeanUtils.map(result, <%= domainName %>SearchResultDto.class);
  }
}
