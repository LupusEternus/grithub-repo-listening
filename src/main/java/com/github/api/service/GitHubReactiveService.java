package com.github.api.service;


import com.github.api.client.GitHubReactiveClient;
import dto.ErrorResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GitHubReactiveService {

    @RestClient
    @Inject
    GitHubReactiveClient gitHubClient;

    public Uni<Response> getUserRepos(String username) {
        return gitHubClient.getUser(username)
                .onItem().transformToUni(response -> gitHubClient.getUser(username))
                .onFailure().recoverWithItem(this::handleError);

    }

    private Response handleError(Throwable e) {
        if (e instanceof WebApplicationException wae) {
            return buildError(wae.getResponse().getStatus(), wae.getMessage());
        }
        return buildError(500, "Internal server error");
    }

    private Response buildError(int status, String msg) {
        return Response.status(status)
                .entity(new ErrorResponse(status, msg))
                .build();
    }

}
