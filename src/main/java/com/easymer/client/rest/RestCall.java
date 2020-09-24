package com.easymer.client.rest;

import com.easymer.client.dto.TemaMultipleOpcionDto;
import com.easymer.client.dto.TemaTeoricoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestCall {

    private static final String baseUrl = "http://localhost:8080/temas";
    private static Client client;

    public RestCall(){
        ClientConfig config = new ClientConfig();
        client = ClientBuilder.newClient(config);
    }

    public boolean postTemaTeorico(TemaTeoricoDto temaTeoricoDto) throws JsonProcessingException {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(temaTeoricoDto);

        System.out.println("WILL POST => " + jsonStr);

        Response response = client.target(baseUrl).path("crear").request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonStr, MediaType.APPLICATION_JSON));

        int status = response.getStatus();

        String body = response.readEntity(String.class);

        System.out.println("Response => " + body);

        if(status != 201){
            return false;
        }
        return true;
    }

    public boolean postTemaMultipleOpcion(TemaMultipleOpcionDto temaMultipleOpcionDto) throws JsonProcessingException {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(temaMultipleOpcionDto);

        System.out.println("WILL POST => " + jsonStr);

        Response response = client.target(baseUrl).path("crear").request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonStr, MediaType.APPLICATION_JSON));

        int status = response.getStatus();

        String body = response.readEntity(String.class);

        System.out.println("Response => " + body);

        if(status != 201){
            return false;
        }
        return true;
    }
}
