package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserRepoResponse(@JsonProperty("Repository Name") String name, @JsonProperty("Owner Login") String ownerLogin, List<BranchInfo> branches) {
}
