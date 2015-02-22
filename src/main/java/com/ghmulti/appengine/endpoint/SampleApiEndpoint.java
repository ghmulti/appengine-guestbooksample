package com.ghmulti.appengine.endpoint;

import com.ghmulti.appengine.dto.ApiResponse;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

@Api(name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(ownerDomain = "helloworld.example.com",
                ownerName = "helloworld.example.com",
                packagePath=""))
public class SampleApiEndpoint {

    @ApiMethod(name = "sayHi")
    public ApiResponse sayHi(@Named("name") String name) {
        return new ApiResponse("Hello " + name);
    }

}
