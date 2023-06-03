package org.example;
//Springboot的测试环境
import org.example.domain.Book;
import org.example.mapper.BookMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

@SpringBootTest(classes = JsoupDemoDay3App.class)
@RunWith(SpringRunner.class)
public class JsoupHtmlParseApplicationTests {
    @Autowired//依赖注入mapper层 使用新增方法即可
    private BookMapper bookMapper;

    @Test
    public void contextLoads() throws IOException {
    //准备一个url
        String url = "https://search.jd.com/Search?keyword=Java";
        //解析url获取文档对象
        Document document = Jsoup.parse(new URL(url), 30000);
        //通过"J_goodsList"获取所有的商品信息
        Element jGoodsList = document.getElementById("J_goodsList");
        //因为每一个Li标签对应的就是一个商品信息，从jGoodsList去获取Li标签
        Elements elements = jGoodsList.getElementsByTag("li");//获取所有商品
        int id =0;
        //通过循环的方式将商品信息传入Book对象并保存到数据库
        for (Element el:elements ){
            Book book = new Book();
            id++;
            //京东的图片比较多，可以采用懒加载
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");//图片
            String price = el.getElementsByClass("p-price").eq(0).text();//价格
            String name = el.getElementsByClass("p-name").eq(0).text();//商品名
            //把值设置到Book对象里边
            book.setId(id);
            book.setImg(img);
            book.setName(name);
            book.setPrice(price);
            //调用新增方法
            bookMapper.insert(book);
            System.out.println(book);
        }
        System.out.println("批量插入数据成功");
    }
}
