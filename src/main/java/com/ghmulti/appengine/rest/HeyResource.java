package com.ghmulti.appengine.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/hey")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class HeyResource {

    @Context
    private HttpServletRequest httpRequest;

    @GET
    public String handleRequest() {
        return ("Hey there");
    }

}