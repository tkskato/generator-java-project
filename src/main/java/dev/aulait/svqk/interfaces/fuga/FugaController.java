package dev.aulait.svqk.interfaces.fuga;

import dev.aulait.svqk.arch.search.SearchConditionVo;
import dev.aulait.svqk.arch.search.SearchResultDto;
import dev.aulait.svqk.arch.search.SearchResultVo;
import dev.aulait.svqk.arch.util.BeanUtils;
import dev.aulait.svqk.arch.web.ApiPath;
import dev.aulait.svqk.arch.web.IdDto;
import dev.aulait.svqk.domain.fuga.IssueEntity;
import dev.aulait.svqk.domain.fuga.IssueService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;

@Path(FugaController.ISSUES_PATH)
@RequiredArgsConstructor
public class FugaController {

  private final FugaService service;

  private final FugaFactory factory;

  public static class FugaSearchResultDto extends SearchResultDto<FugaDto> {}

  @POST
  public IdDto save(@Valid IssueDto dto) {
    FugaEntity entity = BeanUtils.map(dto, FugaEntity.class);
    FugaEntity savedEntity = service.save(entity);

    return BeanUtils.map(savedEntity, IdDto.class);
  }

  @GET
  @Path(ISSUES_GET_PATH)
  public FugaDto get(@PathParam("issueId") int id) {
    FugaEntity entity = service.find(id);

    return BeanUtils.map(entity, FugaDto.class);
  }

  @POST
  @Path(ISSUES_SEARCH_PATH)
  public FugaSearchResultDto search(FugaSearchConditionDto dto) { // <.>
    SearchConditionVo vo = factory.build(dto);
    SearchResultVo<FugaEntity> result = service.search(vo);

    return BeanUtils.map(result, FugaSearchResultDto.class);
  }
}
