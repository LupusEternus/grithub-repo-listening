package com.github.api.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/users")
@RegisterRestClient(baseUri = "https://api.github.com")
public interface GitHubReactiveClient {

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Response> getUser(@PathParam("username") String username);


}
