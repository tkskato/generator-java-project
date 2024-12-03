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

@Path(TrackerController.ISSUES_PATH)
@RequiredArgsConstructor
public class TrackerController {

  private final TrackerService service;

  private final TrackerFactory factory;

  public static class TrackerSearchResultDto extends SearchResultDto<TrackerDto> {}

  @POST
  public IdDto save(@Valid IssueDto dto) {
    TrackerEntity entity = BeanUtils.map(dto, TrackerEntity.class);
    TrackerEntity savedEntity = service.save(entity);

    return BeanUtils.map(savedEntity, IdDto.class);
  }

  @GET
  @Path(ISSUES_GET_PATH)
  public TrackerDto get(@PathParam("issueId") int id) {
    TrackerEntity entity = service.find(id);

    return BeanUtils.map(entity, TrackerDto.class);
  }

  @POST
  @Path(ISSUES_SEARCH_PATH)
  public TrackerSearchResultDto search(TrackerSearchConditionDto dto) { // <.>
    SearchConditionVo vo = factory.build(dto);
    SearchResultVo<TrackerEntity> result = service.search(vo);

    return BeanUtils.map(result, TrackerSearchResultDto.class);
  }
}
