package com.dity.argument.component;

import com.dity.argument.pojo.Person;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/4/23
 * 将request里的流数据读取到person
 */
public class PersonHttpMessageConverter implements HttpMessageConverter<Person> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        if(aClass==Person.class){
            if(mediaType==null){
                return true;
            }
            for(MediaType supportedMediaType:getSupportedMediaTypes()){
                if(supportedMediaType.includes(mediaType)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        if(aClass==Person.class){
            if(mediaType==null){
                return true;
            }
            for(MediaType supportMediaType:getSupportedMediaTypes()){
                if(supportMediaType.includes(mediaType)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        MediaType mediaType=new MediaType("text","person", Charset.forName("UTF-8"));
        return Collections.singletonList(mediaType);
    }

    @Override
    public Person read(Class<? extends Person> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String cotent=new String(IOUtils.toByteArray(httpInputMessage.getBody()));
        String[] split = cotent.split("\\s");
        return new Person(split[0],split[1]);
    }

    @Override
    public void write(Person person, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String content = person.getFirstName() + " " + person.getLastName();
        IOUtils.write(content,httpOutputMessage.getBody());
    }
}
