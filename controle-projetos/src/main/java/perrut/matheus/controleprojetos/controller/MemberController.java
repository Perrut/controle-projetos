package perrut.matheus.controleprojetos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perrut.matheus.controleprojetos.dto.MemberDTO;
import perrut.matheus.controleprojetos.mapper.MemberMapper;
import perrut.matheus.controleprojetos.service.MemberService;

@Tag(name = "Member")
@RestController
@RequestMapping(value = "/member")
public class MemberController {

  @Autowired
  MemberMapper memberMapper;

  @Autowired
  MemberService memberService;

  @Operation(summary = "Create a new member")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Member created",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = MemberDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
          content = @Content)})
  @PostMapping
  public MemberDTO saveMember(@RequestBody MemberDTO memberDTO) {
    return memberMapper.memberToDTO(memberService.saveMember(memberMapper.dtoToMember(memberDTO)));
  }
}
