package com.easymer.client.rest;

import com.easymer.client.dto.TemaTeoricoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestCall {

    private static final String baseUrl = "http://localhost:8080";
    private WebTarget webTarget;

    public RestCall(){

    }

    public boolean postTemaTeorico(TemaTeoricoDto temaTeoricoDto) throws JsonProcessingException {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        this.webTarget = client.target(baseUrl).path("temas").path("crear");

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(temaTeoricoDto);



        //Invocation.Builder invocationBuilder = webTarget.request().header("Content-Type", MediaType.APPLICATION_JSON);

        //Response response = invocationBuilder.accept(MediaType.APPLICATION_JSON).post(Entity.entity(jsonStr, MediaType.APPLICATION_JSON));

        Response response = client.target("http://localhost:8080/temas/crear").request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonStr, MediaType.APPLICATION_JSON));

        int status = response.getStatus();

        String body = response.readEntity(String.class);

        System.out.println("Response =>" + body);

        if(status != 201){
            return false;
        }

        return true;
    }
}
