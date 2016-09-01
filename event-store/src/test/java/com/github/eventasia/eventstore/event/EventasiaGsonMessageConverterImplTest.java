package com.github.eventasia.eventstore.event;

import com.github.eventasia.framework.Event;
import org.junit.Assert;
import org.junit.Test;


public class EventasiaGsonMessageConverterImplTest {

    private EventasiaGsonMessageConverterImpl messageConverter = new EventasiaGsonMessageConverterImpl();

    @Test
    public void serialize() throws Exception {
        MyEvent event = new MyEvent();
        event.setId("12345");

        EventasiaMessage message = new EventasiaMessage(event);
        byte[] result = messageConverter.serialize(message);

        Assert.assertEquals( "{\"event\":{\"class\":\"com.github.eventasia.eventstore.event.EventasiaGsonMessageConverterImplTest$MyEvent\",\"event\":{\"id\":\"12345\"}}}" , new String(result) );
    }

    @Test
    public void deserialize() throws Exception {

        EventasiaMessage message = messageConverter.deserialize("{\"event\":{\"class\":\"com.github.eventasia.eventstore.event.EventasiaGsonMessageConverterImplTest$MyEvent\",\"event\":{\"id\":\"12345\"}}}".getBytes());
        Assert.assertEquals(message.getEvent().getClass() , MyEvent.class );
    }


    class MyEvent implements Event{
        String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}