package com.example.mkdata.dao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlDAO {
    public Document getDocument(String url) {
        Document doc;

        try {
            doc = (Document) Jsoup.connect(url).get();
//            System.out.println(doc.html());
            return doc;
        } catch (IOException ex) {
            Logger.getLogger(HtmlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
