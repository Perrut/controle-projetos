package perrut.matheus.controleprojetos.mapper;

import org.mapstruct.Mapper;
import perrut.matheus.controleprojetos.domain.Member;
import perrut.matheus.controleprojetos.dto.MemberDTO;

@Mapper(componentModel = "spring")
public interface MemberMapper {

  MemberDTO memberToDTO(Member member);

  Member dtoToMember(MemberDTO memberDTO);
}
