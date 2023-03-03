package com.demo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SqlFileParse {
    public static void main(String[] args) throws Exception {
        List list = loadSql("src/main/resources/data.sql");
        for (Object o : list) {
            System.out.println(o);
            System.out.println("--***********");
        }

    }
    /**
     * 读取SQL文件,获取SQL语句,返回所有SQL语句的List
     */
    public static List loadSql(String sqlFile) throws Exception  {

        ArrayList sqlList = new ArrayList();
        try {
            InputStream sqlFileIn = new FileInputStream(sqlFile);
            StringBuilder sqlSb = new StringBuilder();
            byte[] buff = new byte[1024];
            int byteRead = 0;
            while ((byteRead = sqlFileIn.read(buff)) != -1) {
                sqlSb.append(new String(buff, 0, byteRead));
            }//Windows 下换行是 \r\n, Linux 下是 \n
           // System.out.println(sqlSb);
            //System.out.println("================================");
            String[] sqlArr = sqlSb.toString()
                    .split("(;\\s*\\S*\\r\\n)|(;\\s*\\S*\\n)");
            for (String s : sqlArr) {
                String sql = s.replaceAll("(--.*)|(\\r\\n)|(\\n)", " ").trim();
                if (!sql.equals("")) {
                    sqlList.add(sql);
                }
            }
            return sqlList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
