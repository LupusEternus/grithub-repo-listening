package com.github.api.service;


import com.github.api.client.GitHubReactiveClient;
import dto.*;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class GitHubReactiveService {

    @RestClient
    @Inject
    GitHubReactiveClient gitHubClient;

    public Uni<Response> getUserRepos(String username) {
        return gitHubClient.getUser(username)
                .onItem().transformToUni(response -> processRepos(username))
                .onFailure().recoverWithUni(e -> Uni.createFrom().item(handleError(e, username)));
    }

    private Uni<Response> processRepos(String username) {
        return gitHubClient.getUserRepos(username)
                .onItem().transformToMulti(repos -> Multi.createFrom().iterable(repos))
                .filter(repo -> !repo.isFork())
                .onItem().transformToUniAndMerge(repo ->
                        gitHubClient.getBranches(repo.owner().login(), repo.name())
                                .onItem().transform(branches -> mapToResponse(repo, branches))
                )
                .collect().asList()
                .onItem().transform(list -> Response.ok(list).build());
    }

    private UserRepoResponse mapToResponse(GitHubRepo repo, List<GitHubBranch> branches) {
        return new UserRepoResponse(
                repo.name(),
                repo.owner().login(),
                branches.stream()
                        .map(b -> new BranchInfo(b.name(), b.commit().sha()))
                        .toList()
        );
    }

    private Response handleError(Throwable e, String username) {
        if (e instanceof WebApplicationException wae) {
            if (wae.getResponse().getStatus() == 404) {
                return buildError(404, "User: " + username + " not found");
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
