package com.kangyonggan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author kangyonggan
 * @since 16/7/21
 */
public class FullText {

    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://121.40.66.176:3306/app?user=root&password=123456&useUnicode=true&characterEncoding=UTF8");

        PreparedStatement ps = conn.prepareStatement("SELECT title, summary, body, category_name, id FROM article");
        ResultSet rs = ps.executeQuery();

        int count = 0;
        while (rs.next()) {
            String title_py = StringUtil.converterToSpellWithMuti(rs.getString(1));
            String summary_py = StringUtil.converterToSpellWithMuti(rs.getString(2));
            String body_py = StringUtil.converterToSpell(rs.getString(3));
            String category_name_py = StringUtil.converterToSpellWithMuti(rs.getString(4));
            Long id = rs.getLong(5);

            String sql = "update article set title_py = ?, summary_py = ?, body_py = ?, category_name_py = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title_py);
            preparedStatement.setString(2, summary_py);
            preparedStatement.setString(3, body_py);
            preparedStatement.setString(4, category_name_py);
            preparedStatement.setLong(5, id);

            int res = preparedStatement.executeUpdate();
            System.out.println("update " + id + (res > 0 ? "成功" : "失败"));

            System.out.println("count: " + count++);
        }

        rs.close();
        ps.close();
        conn.close();
        System.out.println("success");
    }

}
