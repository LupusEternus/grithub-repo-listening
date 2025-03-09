package com.github.api.service;


import com.github.api.client.GitHubReactiveClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GitHubReactiveService {

    @RestClient
    @Inject
    GitHubReactiveClient gitHubClient;

    public Response getUser(String username){
        return Response.ok()
                .entity(gitHubClient.getUser(username))
                .build();
    }

}
