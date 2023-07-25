package com.atguigu.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/4/17   17:27
 */
public class Dom4jTest {
    @Test
    public void test1() throws DocumentException {
        //创建一个SAXReader输入流,读取xml配置文件,生成document对象
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("E:\\java\\java_base\\xml\\src\\books.xml");
        System.out.println(document);
        //org.dom4j.tree.DefaultDocument@1d76aeea [Document: name E:\java\java_base\xml\src\books.xml]
    }
    @Test
    //读取books.xml生成book类
    public void test2() throws DocumentException {
        //1.读取books.xml文件
        SAXReader saxReader = new SAXReader();
        //在junit中,相对路径从项目开始
        Document document = saxReader.read("src//books.xml");
        //2.通过dom对获取根元素
        Element rootElement = document.getRootElement();
        //System.out.println(rootElement);
        //org.dom4j.tree.DefaultElement@6e75aa0d [Element: <books attributes: []/>]
        //3.通过根元素获取book标签对象
        //element()方法和elements()都是通过标签名查找子元素
        List<Element> books = rootElement.elements("book");
        //4.遍历,处理每个book标签转换为Book类
        for (Element book : books){
            // asXML()把标签对象转换为标签字符串;取出xml文本
//            System.out.println(book.asXML());
            Element name = book.element("name");
            String text = name.getText();//获取标签文本内容
            System.out.println(text);
            //直接获取标签文本内容
            String author = book.elementText("author");
            System.out.println(author);
            //获取属性值内容
            String sn = book.attributeValue("sn");

        }
    }
}
