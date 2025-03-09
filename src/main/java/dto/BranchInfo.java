package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BranchInfo(String name, @JsonProperty("last commit sha") String lastCommitSha) {
}
