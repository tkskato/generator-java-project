package dev.aulait.svqk.domain.hello;

import dev.aulait.svqk.arch.search.SearchConditionVo;
import dev.aulait.svqk.arch.search.SearchResultDto;
import dev.aulait.svqk.arch.search.SearchResultVo;
import dev.aulait.svqk.arch.util.BeanUtils;
import dev.aulait.svqk.arch.web.ApiPath;
import dev.aulait.svqk.arch.web.IdDto;
import dev.aulait.svqk.domain.hello.IssueEntity;
import dev.aulait.svqk.domain.hello.IssueService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;

@Path(HelloController.ISSUES_PATH)
@RequiredArgsConstructor
public class HelloController {

  private final HelloService service;

  private final HelloFactory factory;

  public static class HelloSearchResultDto extends SearchResultDto<HelloDto> {}

  @POST
  public IdDto save(@Valid IssueDto dto) {
    HelloEntity entity = BeanUtils.map(dto, HelloEntity.class);
    HelloEntity savedEntity = service.save(entity);

    return BeanUtils.map(savedEntity, IdDto.class);
  }

  @GET
  @Path(ISSUES_GET_PATH)
  public HelloDto get(@PathParam("issueId") int id) {
    HelloEntity entity = service.find(id);

    return BeanUtils.map(entity, HelloDto.class);
  }

  @POST
  @Path(ISSUES_SEARCH_PATH)
  public HelloSearchResultDto search(HelloSearchConditionDto dto) { // <.>
    SearchConditionVo vo = factory.build(dto);
    SearchResultVo<HelloEntity> result = service.search(vo);

    return BeanUtils.map(result, HelloSearchResultDto.class);
  }
}
