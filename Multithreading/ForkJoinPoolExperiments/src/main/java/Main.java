import java.io.FileWriter;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        SitemapNode sitemapRoot = new SitemapNode("https://skillbox.ru/");

        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        commonPool.invoke(new SitemapNodeRecursiveAction(sitemapRoot));

        String result = buildStringResult(sitemapRoot, 0);

        try {
            FileWriter output = new FileWriter("out/sitemap.txt");
            output.write(result);
            output.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String buildStringResult(SitemapNode node, int depth) {
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getUrl());
        node.getChildren().forEach(child -> result.append("\n").append(buildStringResult(child, depth + 1)));
        return result.toString();
    }
}