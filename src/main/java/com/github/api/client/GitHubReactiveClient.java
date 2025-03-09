package com.github.api.client;

import dto.GitHubRepo;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/users")
@RegisterRestClient(baseUri = "https://api.github.com")
public interface GitHubReactiveClient {

    @GET
    @Path("/{username}")
    Uni<Response> getUser(@PathParam("username") String username);

    @GET
    @Path("/{username}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<GitHubRepo>> getUserRepos(@PathParam("username") String username);
    }
