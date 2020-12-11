package temp.jersey.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/helloWorld")
public class HelloWorldResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWrold1() {
        System.out.println("/restapi/helloWorld");
        return "Hello, World!";
    }

    @GET
    @Path("/v2")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWrold2() {
        return "/restapi/helloWorld/v2";
    }

    @GET
    @Path("/v3")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWrold3(@QueryParam("id") String id) {
        return "/restapi/helloWorld/v3 => id: " + id;
    }

    @GET
    @Path("/v4")
    public Response helloWrold4() {
        System.out.println("/restapi/helloWorld/v4");
        try {
            URI uri = new URI("https://www.naver.com");
            return Response.seeOther(uri).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
