package com.github.api.client;

import dto.GitHubBranch;
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


@RegisterRestClient(baseUri = "https://api.github.com")
public interface GitHubReactiveClient {

    @GET
    @Path("/users/{username}")
    Uni<Response> getUser(@PathParam("username") String username);

    @GET
    @Path("/users/{username}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<GitHubRepo>> getUserRepos(@PathParam("username") String username);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    Uni<List<GitHubBranch>> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}
