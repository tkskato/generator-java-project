package dev.aulait.svqk.domain.issue;

import dev.aulait.svqk.arch.search.SearchConditionVo;
import dev.aulait.svqk.arch.search.SearchResultDto;
import dev.aulait.svqk.arch.search.SearchResultVo;
import dev.aulait.svqk.arch.util.BeanUtils;
import dev.aulait.svqk.arch.web.ApiPath;
import dev.aulait.svqk.arch.web.IdDto;
import dev.aulait.svqk.domain.issue.IssueEntity;
import dev.aulait.svqk.domain.issue.IssueService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;

@Path(IssueStatusController.ISSUES_PATH)
@RequiredArgsConstructor
public class IssueStatusController {

  private final IssueStatusService service;

  private final IssueStatusFactory factory;

  public static class IssueStatusSearchResultDto extends SearchResultDto<IssueStatusDto> {}

  @POST
  public IdDto save(@Valid IssueDto dto) {
    IssueStatusEntity entity = BeanUtils.map(dto, IssueStatusEntity.class);
    IssueStatusEntity savedEntity = service.save(entity);

    return BeanUtils.map(savedEntity, IdDto.class);
  }

  @GET
  @Path(ISSUES_GET_PATH)
  public IssueStatusDto get(@PathParam("issueId") int id) {
    IssueStatusEntity entity = service.find(id);

    return BeanUtils.map(entity, IssueStatusDto.class);
  }

  @POST
  @Path(ISSUES_SEARCH_PATH)
  public IssueStatusSearchResultDto search(IssueStatusSearchConditionDto dto) { // <.>
    SearchConditionVo vo = factory.build(dto);
    SearchResultVo<IssueStatusEntity> result = service.search(vo);

    return BeanUtils.map(result, IssueStatusSearchResultDto.class);
  }
}
