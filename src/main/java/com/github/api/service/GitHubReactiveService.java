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
                .onItem().transformToUni(response-> processRepos(username))
                .onFailure().recoverWithItem(handleError(new WebApplicationException("User not found",404),username));
    }

    private Uni<Response> processRepos(String username){
        return Uni.createFrom().item(Response.ok().build());
    }

    private Response handleError(Throwable e,String username) {
        if (e instanceof WebApplicationException wae) {
            if(wae.getResponse().getStatus() == 404){
                return buildError(404,"User: " + username + " not found");
            }
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
