package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubOwner(@JsonProperty("login") String login) {
}
