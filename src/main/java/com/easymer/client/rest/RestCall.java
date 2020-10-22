package com.easymer.client.rest;

import com.easymer.client.TemaResponse;
import com.easymer.client.dto.TemaMerDto;
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

    public TemaResponse postTemaTeorico(TemaTeoricoDto temaTeoricoDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(temaTeoricoDto);

        System.out.println("WILL POST => " + jsonStr);

        Response response = client.target(baseUrl).path("crear").request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonStr, MediaType.APPLICATION_JSON));

        String body = response.readEntity(String.class);

        TemaResponse temaResponse = objectMapper.readValue(body, TemaResponse.class);

        System.out.println("Response => " + body);

        return temaResponse;
    }

    public TemaResponse postTemaMer(TemaMerDto temaMerDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(temaMerDto);

        System.out.println("WILL POST => " + jsonStr);

        Response response = client.target(baseUrl).path("crear").request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonStr, MediaType.APPLICATION_JSON));

        String body = response.readEntity(String.class);

        TemaResponse temaResponse = objectMapper.readValue(body, TemaResponse.class);

        System.out.println("Response => " + body);

        return temaResponse;
    }

    public TemaResponse postTemaMultipleOpcion(TemaMultipleOpcionDto temaMultipleOpcionDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(temaMultipleOpcionDto);

        System.out.println("WILL POST => " + jsonStr);

        Response response = client.target(baseUrl).path("crear").request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonStr, MediaType.APPLICATION_JSON));

        String body = response.readEntity(String.class);

        System.out.println("Response => " + body);

        TemaResponse temaResponse = objectMapper.readValue(body, TemaResponse.class);

        return temaResponse;
    }

    public TemaResponse borrarTema(String id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Response response = client.target(baseUrl).path("borrar").path(id).request(MediaType.APPLICATION_JSON).delete();

        String body = response.readEntity(String.class);

        System.out.println("Response => " + body);

        TemaResponse temaResponse = objectMapper.readValue(body, TemaResponse.class);

        return temaResponse;
    }
}
