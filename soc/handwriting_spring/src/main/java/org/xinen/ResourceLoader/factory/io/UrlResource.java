package org.xinen.ResourceLoader.factory.io;

import org.junit.Assert;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource{

    private URL url;

    public UrlResource(URL url){
        Assert.assertNotNull("url must not be null",url);
        this.url = url;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection com = this.url.openConnection();
        try{
            return com.getInputStream();
        }catch(IOException e){
            if(com instanceof HttpsURLConnection){
                ((HttpsURLConnection) com).disconnect();
            }
            throw e;
        }
    }
}
