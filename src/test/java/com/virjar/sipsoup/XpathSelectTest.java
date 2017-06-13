package com.virjar.sipsoup;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;

import com.google.common.io.Files;
import com.virjar.dungproxy.client.httpclient.HttpInvoker;
import com.virjar.sipsoup.parse.XpathParser;
import com.virjar.sipsoup.util.PathResolver;

/**
 * Created by virjar on 17/6/11.
 */
public class XpathSelectTest {

    private static void handleURL(String seed) {
        String s = HttpInvoker.get(seed);
        if (s == null) {
            return;
        }
        List<String> strings = XpathParser.compileNoError("//css('.ad-thumb-list .inner')::*//a/@href")
                .evaluateToString(Jsoup.parse(s));
        for (String s1 : strings) {
            try {
                Files.write(HttpInvoker.getEntity(s1), // 文件根据网站,路径,base自动计算
                        new File(PathResolver.onlySource("~/Desktop/testpic", s1)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        handleURL("http://www.1905.com/newgallery/hdpic/846385.shtml?fr=wwwmdb_stargener_picture_20141010");
        handleURL("http://www.1905.com/newgallery/hdpic/898930.shtml?fr=wwwmdb_stargener_picture_20141010");
        handleURL("http://www.1905.com/newgallery/hdpic/817834.shtml?fr=wwwmdb_stargener_picture_20141010");
        System.out.println("图片下载完成");
    }
}
