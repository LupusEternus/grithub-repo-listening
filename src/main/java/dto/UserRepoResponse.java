package dto;

import java.util.List;

public record UserRepoResponse(String name, String ownerLogin, List<BranchInfo> branches) {
}
