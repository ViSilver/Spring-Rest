package com.example.springtestexample.entity.serialization;


import com.example.springrestexample.entity.Address;
import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class AddressJsonSerializationTest {

    @Test
    public void whenUseJsonViewToSerialize_thenCorrect() throws JsonProcessingException {
        Address address = new Address("London", "Baker Street", "221 B");

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        String publicResult = mapper
                .writerWithView(Views.AddressPublicView.class)
                .writeValueAsString(address);

        assertThat(publicResult, containsString("London"));
        assertThat(publicResult, not(containsString("Baker Street")));
        assertThat(publicResult, not(containsString("221 B")));

        String internalResult = mapper
                .writerWithView(Views.AddressPrivateView.class)
                .writeValueAsString(address);

        assertThat(internalResult, containsString("London"));
        assertThat(internalResult, containsString("Baker Street"));
        assertThat(internalResult, containsString("221 B"));
    }

    @Test
    public void whenUsePublicJsonViewToDeserialize_thenCorrect() throws IOException {
        String json = "{\"city\": \"London\"}";

        ObjectMapper mapper = new ObjectMapper();
        Address address = (Address) mapper
                .readerWithView(Views.AddressPublicView.class).withType(Address.class).readValue(json);

        assertEquals("London", address.getCity());
        assertNull(address.getNumber());
        assertNull(address.getStreet());
    }

    @Test
    public void whenUsePrivateJsonViewToDeserialize_thenCorrect() throws IOException {
        String json = "{\"city\": \"London\", \"street\": \"Baker Street\", \"number\": \"221 B\"}";

        ObjectMapper mapper = new ObjectMapper();
        Address address = (Address) mapper
                .readerWithView(Views.AddressPrivateView.class).withType(Address.class).readValue(json);

        assertEquals("London", address.getCity());
        assertEquals("Baker Street", address.getStreet());
        assertEquals("221 B", address.getNumber());
    }
}
