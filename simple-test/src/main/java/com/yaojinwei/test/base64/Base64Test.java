package com.yaojinwei.test.base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
public class Base64Test   
{  
    public static void main(String[] args)  
    {
        //String strImg = GetImageStr();
        //System.out.println(strImg);
        GenerateImage("data:image/png;base64,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");
        //GenerateImage("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");
    }  
    //图片转化成base64字符串  
    public static String GetImageStr()  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        String imgFile = "D:\\tupian\\a.jpg";//待处理的图片  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    //base64字符串转化成图片  
    public static boolean GenerateImage(String imgStr)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
        { return false; }
        int index = 0;
        if((index = imgStr.indexOf(",") )> 0){
            imgStr = imgStr.substring(index+1, imgStr.length());
        }
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = "D:\\workers\\随访平台\\测试\\new.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
} 