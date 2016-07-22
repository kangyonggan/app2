package com.kangyonggan.test;

import com.kangyonggan.util.FenCi;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 测试分词
 *
 * @author kangyonggan
 * @since 16/7/22
 */
@Log4j2
public class FenCiTest {

    @Test
    public void testDemo() {
        String str = FenCi.process("每日一句");
        log.info(str);
    }

    @Test
    public void testTransArticleToIndex() throws Exception {
        log.info("start...\n========================================================");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://121.40.66.176:3306/app?user=root&password=123456&useUnicode=true&characterEncoding=UTF8");

        PreparedStatement ps = conn.prepareStatement("SELECT id, title, summary, body, category_name FROM article WHERE id >= 50");
        ResultSet rs = ps.executeQuery();

        StringBuilder sql = new StringBuilder("insert into article_index \n\t(article_id, title, summary, body, category_name, is_deleted, created_time, updated_time) \nvalues ");
        while (rs.next()) {
            sql.append("\n\t('").append(FenCi.process(rs.getString(1))).append("','").append(FenCi.process(rs.getString(2))).append("','").append(FenCi.process(rs.getString(3))).append("','").append(FenCi.process(rs.getString(4))).append("','").append(FenCi.process(rs.getString(5))).append("',0,now(),now()),");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(";");

        log.info("sql:\n" + sql.toString());
        PreparedStatement statement = conn.prepareStatement(sql.toString());
        int res = statement.executeUpdate();
        log.info("===================================\nres:" + res);

        rs.close();
        ps.close();
        conn.close();
        log.info("finished");
    }

}
