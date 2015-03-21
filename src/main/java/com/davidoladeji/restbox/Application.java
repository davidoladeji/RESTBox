package com.davidoladeji.restbox;

import com.davidoladeji.restbox.model.Distance;
import com.davidoladeji.restbox.model.Distances;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        List<MediaType> mediaType = new ArrayList<>();
        mediaType.add(MediaType.TEXT_XML);

        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
        xmlConverter.setSupportedMediaTypes(mediaType);

        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();

        /**
         * Formatting of output XML to DistanceML
         * Based on XStreamMarshaller Methods
         */


        Map<String, Class> myAliases = new HashMap<String, Class>();
        myAliases.put("Distances", Distances.class);
        myAliases.put("Distance", Distance.class);

        xstreamMarshaller.setAliases(myAliases);

        Map<Class<?>, String> myOmits = new HashMap<>();
        myOmits.put(Distances.class, "count");
        xstreamMarshaller.setOmittedFields(myOmits);


        Map<Class<?>, String> myImplicits = new HashMap<>();
        myImplicits.put(Distances.class, "distances");
        xstreamMarshaller.setImplicitCollections(myImplicits);


        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);


        converters.add(xmlConverter);
    }
}
