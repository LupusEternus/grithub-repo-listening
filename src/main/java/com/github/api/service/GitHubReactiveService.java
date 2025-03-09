package com.github.api.service;


import com.github.api.client.GitHubReactiveClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GitHubReactiveService {

    @RestClient
    @Inject
    GitHubReactiveClient gitHubClient;

    public Uni<Response> getUser(String username){
        return gitHubClient.getUser(username)
                .onItem().transform(user -> Response.ok(user).build());
    }

}
