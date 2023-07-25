package com.local.boot.messageConverters;

import com.local.boot.pojo.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/6/17   17:26
 */
/*
* 自定义Converter*/
public class DhfMessageConverter implements HttpMessageConverter<Person> {

    @Override
    //支持读
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        /*
        * public Person xxx(@RequestBody Person person){
        *   //将前端发送的json转换成Person对象
        * }*/
        return false;
    }

    @Override
    //支持写
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }

    @Override
    // 获取所有支持的MediaType
    // 服务器统计所有MessageConverter能写出什么类型
    // application/dhf
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-dhf");
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    // 自定义类型协议写出
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String str = person.getName() + ";" + person.getAge() + ";" + person.getBirthday()+";";
        //写出
        outputMessage.getBody().write(str.getBytes());
    }
}
