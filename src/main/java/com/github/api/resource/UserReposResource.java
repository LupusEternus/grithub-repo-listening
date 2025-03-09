package com.github.api.resource;

import com.github.api.service.GitHubReactiveService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/user/{username}")
public class UserReposResource {


    @Inject
    GitHubReactiveService gitHubService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getUser(@PathParam("username") String username){
        return gitHubService.getUser(username);
    }
}
