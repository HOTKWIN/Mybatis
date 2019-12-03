package com.itheima.mybatis.io;

import java.io.InputStream;

/**
 * @author kwin
 * @create 2019-12-02 15:10
 */
public class Resources {
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
