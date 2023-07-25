package com.atguigu.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author ��
 * @version 1.0
 * 2022/4/17   17:27
 */
public class Dom4jTest {
    @Test
    public void test1() throws DocumentException {
        //����һ��SAXReader������,��ȡxml�����ļ�,����document����
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("E:\\java\\java_base\\xml\\src\\books.xml");
        System.out.println(document);
        //org.dom4j.tree.DefaultDocument@1d76aeea [Document: name E:\java\java_base\xml\src\books.xml]
    }
    @Test
    //��ȡbooks.xml����book��
    public void test2() throws DocumentException {
        //1.��ȡbooks.xml�ļ�
        SAXReader saxReader = new SAXReader();
        //��junit��,���·������Ŀ��ʼ
        Document document = saxReader.read("src//books.xml");
        //2.ͨ��dom�Ի�ȡ��Ԫ��
        Element rootElement = document.getRootElement();
        //System.out.println(rootElement);
        //org.dom4j.tree.DefaultElement@6e75aa0d [Element: <books attributes: []/>]
        //3.ͨ����Ԫ�ػ�ȡbook��ǩ����
        //element()������elements()����ͨ����ǩ��������Ԫ��
        List<Element> books = rootElement.elements("book");
        //4.����,����ÿ��book��ǩת��ΪBook��
        for (Element book : books){
            // asXML()�ѱ�ǩ����ת��Ϊ��ǩ�ַ���;ȡ��xml�ı�
//            System.out.println(book.asXML());
            Element name = book.element("name");
            String text = name.getText();//��ȡ��ǩ�ı�����
            System.out.println(text);
            //ֱ�ӻ�ȡ��ǩ�ı�����
            String author = book.elementText("author");
            System.out.println(author);
            //��ȡ����ֵ����
            String sn = book.attributeValue("sn");

        }
    }
}
