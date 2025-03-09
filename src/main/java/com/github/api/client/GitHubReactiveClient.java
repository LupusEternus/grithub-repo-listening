package com.github.api.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/users")
@RegisterRestClient(baseUri = "https://api.github.com")
public interface GitHubReactiveClient {

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<String> getUser(@PathParam("username") String username);

}
