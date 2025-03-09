package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GtiHubRepo(String name, @JsonProperty("login") String owner, @JsonProperty("fork") boolean isFork) {
}
