package com.yaojinwei.study.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * @author Yao.Jinwei
 * @date 2016/12/13 12:50
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Client {
    public static void main(String[] args) {

        File xmlFile = new File(Client.class.getClassLoader().getResource("article.xml").getFile()) ;
        DocumentBuilder builder = null;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try{
            builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            Element root = document.getDocumentElement();

            Element child = document.createElement("lalal");
            root.appendChild(child);
            //                node.appendChild()


            System.out.println("根元素："+root.getNodeName());
            NodeList childNodes = root.getChildNodes();
            for(int i =0;i<childNodes.getLength();i++){
                Node node =childNodes.item(i);


                System.out.println("base uri:" + node.getBaseURI());
                System.out.println("getAttributes:" + node.getAttributes());
                System.out.println("getChildNodes:" + node.getChildNodes());
//                System.out.println("getFeature:" + node.getFeature());

                System.out.println("getFirstChild:" + node.getFirstChild());
                System.out.println("getLastChild:" + node.getLastChild());
                System.out.println("getLocalName:" + node.getLocalName());
                System.out.println("getNamespaceURI:" + node.getNamespaceURI());
                System.out.println("getNextSibling:" + node.getNextSibling());
                System.out.println("getNodeName:" + node.getNodeName());
                System.out.println("getNodeType:" + node.getNodeType());
                System.out.println("getNodeValue:" + node.getNodeValue());
                System.out.println("getOwnerDocument:" + node.getOwnerDocument());
                System.out.println("getParentNode:" + node.getParentNode());
                System.out.println("getPrefix:" + node.getPrefix());
                System.out.println("getPreviousSibling:" + node.getPreviousSibling());
                System.out.println("getTextContent:" + node.getTextContent());
//                System.out.println("getUserData:" + node.getUserData());

                if("article".equals(node.getNodeName())){
                    System.out.println("文章所属分类："+node.getAttributes().getNamedItem("category").getNodeValue());
                    NodeList nodeDetail = node.getChildNodes();
                    for(int j=0;j< nodeDetail.getLength();j++){
                        Node detail = nodeDetail.item(j);
                        if("title".equals(detail.getNodeName()))
                            System.out.println("标题："+detail.getTextContent());
                        else if("author".equals(detail.getNodeName()))
                        System.out.println("作者："+detail.getTextContent());
                        else if("email".equals(detail.getNodeName()))
                        System.out.println("电子邮件："+detail.getTextContent());
                        else if("date".equals(detail.getNodeName()))
                        System.out.println("日期："+detail.getTextContent());
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
