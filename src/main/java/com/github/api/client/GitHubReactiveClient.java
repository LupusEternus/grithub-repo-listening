package com.github.api.client;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.Set;

@Path("/users")
@RegisterRestClient(baseUri = "https://api.github.com")
public interface GitHubReactiveClient {

    @GET
    @Path("/{username}")
    Response getUser(@PathParam("username") String username);

}
