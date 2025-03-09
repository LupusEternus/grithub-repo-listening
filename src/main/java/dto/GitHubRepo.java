package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubRepo(String name, GitHubOwner owner, @JsonProperty("fork") boolean isFork) {
}
