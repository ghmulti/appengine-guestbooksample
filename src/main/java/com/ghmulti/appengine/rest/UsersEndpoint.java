package com.ghmulti.appengine.rest;

import com.ghmulti.appengine.dto.AuthenticatedUserToken;
import com.ghmulti.appengine.dto.OAuth2Request;
import com.ghmulti.appengine.service.UsersService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("/hey")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UsersEndpoint {

    @Context
    private HttpServletRequest httpRequest;

    @Context
    protected UriInfo uriInfo;

    @Inject
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Inject
    private UsersService usersService;

    @GET
    public String handleRequest() {
        return ("Hey there");
    }

    @Path("login/{providerId}")
    @POST
    public Response socialLogin(@PathParam("providerId") String providerId, OAuth2Request request) {
        OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
        Connection<?> connection = connectionFactory.createConnection(new AccessGrant(request.getAccessToken()));
        AuthenticatedUserToken token = usersService.socialLogin(connection);
        return getLoginResponse(token);
    }

    private Response getLoginResponse(AuthenticatedUserToken token) {
        URI location = UriBuilder.fromPath(uriInfo.getBaseUri() + "hey/" + token.getUserId()).build();
        return Response.ok().entity(token).contentLocation(location).build();
    }


}