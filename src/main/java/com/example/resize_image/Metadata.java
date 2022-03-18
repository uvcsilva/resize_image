package com.example.resize_image;

import lombok.AllArgsConstructor;
import lombok.Value;

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
