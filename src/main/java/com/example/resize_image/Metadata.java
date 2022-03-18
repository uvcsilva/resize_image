package com.example.resize_image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.io.Serializable;

@Value
@AllArgsConstructor
public class Metadata implements java.io.Serializable {

    String name;
    String url;
    String mime;
    Long size;
    String uri;
    byte[] bytes;

}
