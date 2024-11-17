package com.example.mkdata;

import com.example.mkdata.control.CustomerRepo;
import com.example.mkdata.control.MyCompanyRepo;
import com.example.mkdata.dao.HtmlDAO;
import com.example.mkdata.dto.MyCompany;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SpringBootApplication
public class MkdataApplication implements CommandLineRunner {
    @Autowired
    MyCompanyRepo myCompanyRepo;
    HtmlDAO dao;
    Document document;
    String url = "";
    String con_address = "Bắc Giang";
    int page = 1000;
    boolean flag = false;

    public static void main(String[] args) {

        SpringApplication.run(MkdataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Inserting the data in the mysql table.
        for (int i = 1; i <= page; i++) {
            String urlsc = url + i;
            System.out.println(i);
            if (flag == false) {
                get(urlsc);
            } else return;
        }

    }

    void get(String urlsc) {
        dao = new HtmlDAO();
        document = dao.getDocument(urlsc);

//        System.out.println(document.html());
        String cssQuery = "div.yp_noidunglistings";
        Elements es = document.select(cssQuery);
        for (Element e : es) {
            MyCompany com = new MyCompany();
            com.setTypeStore("Công ty");
            String cssTittle = "h2";
            Element et = e.select(cssTittle).first();
            String name = et.text().trim();
            com.setNameStore(name);
            System.out.println(com.getNameStore());
            String cssQ ="p:contains(" + con_address + ")";
            Elements eAddress = e.select(cssQ);
//            Elements eAddress = e.select("p > small");
            for (Element ea : eAddress) {
                String address = ea.text().trim();

                if (address.contains("Bắc Giang")) {
                    System.out.println(address);
                    com.setAddress(address);
                    break;
                }
            }
            Elements elink = e.select("a");
            for (Element link : elink) {
                String url = link.attr("href").trim();
                System.out.println(url);
                if (url.contains("mailto:")) {
                    url = url.replace("mailto:", "");
                    com.setEmail(url);
                    if (url.contains("...")) {
                        flag = true;
                        return;
                    }

                }
                if (url.contains("tel:")) {
                    url = url.replace("tel:", "").trim();
                    com.setPhone(url);
                    if (url.contains(".x.x")) {
                        flag = true;
                        return;
                    }
                }

                if (url.contains("http")) {
                    if (url.contains("zalo")) {
//                        com.setZaloLink(url);
                    } else if (url.contains("yellow")) {
//                        com.setYellowPageLink(url);
                        System.out.println(url);

                    } else {
                        com.setWebsiteLink(url);
                        if (url.contains("...")) {
                            flag = true;
                            return;
                        }
//                        System.out.println(url);
                    }
                }
            }
            if (!myCompanyRepo.existsByPhoneContainingIgnoreCase(com.getPhone()))
                myCompanyRepo.save(com);
        }

    }
}
