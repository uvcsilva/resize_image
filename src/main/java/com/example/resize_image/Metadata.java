package com.example.resize_image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;

@Value
@AllArgsConstructor
public class Metadata {

    String name;
    String url;
    String mime;
    Long size;
    String uri;

}
