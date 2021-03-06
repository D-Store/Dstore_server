package gg.dstore.admin.domain.dto.user.response;

import gg.dstore.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.dstore.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListResponse extends Response {
    private List<ASelectUserDto> users;
    private int totalPages;
}
