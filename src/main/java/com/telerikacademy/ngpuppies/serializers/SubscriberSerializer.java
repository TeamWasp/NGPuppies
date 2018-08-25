package com.telerikacademy.ngpuppies.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;

import java.io.IOException;

public class SubscriberSerializer extends StdSerializer<Subscriber> {
    public SubscriberSerializer(){
        this(null);
    }
    public SubscriberSerializer(Class<Subscriber> t){
        super(t);
    }
    @Override
    public void serialize(Subscriber value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject("subscriber");
        gen.writeStringField("phoneNumber", value.getPhoneNumber());
        gen.writeStringField("firstName", value.getFirstName());
        gen.writeStringField("lastName", value.getLastName());
        gen.writeStringField("Egn", value.getEgn());
        gen.writeObjectFieldStart("address");
        gen.writeNumberField("AddressID", value.getAddress().getAddressId());
        gen.writeStringField("Country", value.getAddress().getCountry());
        gen.writeStringField("City", value.getAddress().getCity());
        gen.writeStringField("ZipCode", value.getAddress().getZipCode());
        gen.writeStringField("Street", value.getAddress().getStreet());
        gen.writeEndObject();
        gen.writeObjectFieldStart("bank");
        gen.writeNumberField("bankId", value.getBank().getUserId());
        gen.writeStringField("bankUsername", value.getBank().getUsername());
        gen.writeEndObject();
        gen.writeEndObject();
    }
}
