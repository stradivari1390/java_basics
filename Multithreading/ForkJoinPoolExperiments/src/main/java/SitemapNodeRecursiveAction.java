import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class SitemapNodeRecursiveAction extends RecursiveAction {

    private SitemapNode node;

    public SitemapNodeRecursiveAction(SitemapNode node) {
        this.node = node;
    }

    @Override
    protected void compute() {
        try {
            sleep(150);
            Connection connection = Jsoup.connect(node.getUrl()).timeout(6000);
            Document page = connection.get();
            Elements links = page.select("body").select("a");
            for (Element link : links) {
                String childUrl = link.absUrl("href");
                if (isCorrectLink(childUrl)) {
                    childUrl = childUrl.replaceAll("\\?.+","");
                    node.addChild(new SitemapNode(childUrl));
                }
            }
        } catch (IOException | InterruptedException exception) {
//            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }

        for (SitemapNode child : node.getChildren()) {
            SitemapNodeRecursiveAction task = new SitemapNodeRecursiveAction(child);
            task.compute();
        }
    }

    private boolean isCorrectLink(String url) {
        Pattern root = Pattern.compile("^" + node.getUrl());
        Pattern file = Pattern.compile("(\\.(?i)(jpg|bmp|png|gif|pdf|doc|xls|ppt))$");
        Pattern pageElement = Pattern.compile("#");

        return root.matcher(url).lookingAt() &&
                !file.matcher(url).find() &&
                !pageElement.matcher(url).find();
    }
}