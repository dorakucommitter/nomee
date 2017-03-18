package com.dorakucommitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config
{
    @Value("${config.gurunavi.accesskey}")
    private String accessKey;

    public String getGnaviAccessKey()
    {
        return(this.accessKey);
    }

    public void setGnaviAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }
}
