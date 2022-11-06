import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fileName = "res/data-1572M.xml";

        StaxParser staxParser = new StaxParser(new File(fileName));

//        SAXParserFactory factory = SAXParserFactory.newInstance();

//        SAXParser saxParser = factory.newSAXParser();

//        XMLHandler handler = new XMLHandler();

//        DOMParser domParser = new DOMParser();

        long start = System.currentTimeMillis();

        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

//        saxParser.parse(new File(fileName), handler);

//        domParser.parseFile(fileName);

        try {
            staxParser.getAllVoters();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.println("\nMemory used: " + usage + "\n");

        System.out.println("Data uploaded to DB within " + (System.currentTimeMillis() - start) + " milliseconds");

//        printHibernateTableData();
    }

    public static void printHibernateTableData() {
        Session session = null;
        session = LoaderSession.startSession(session);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Voter> query = builder.createQuery(Voter.class);
        Root<Voter> root = query.from(Voter.class);
        query.select(root).where(builder.greaterThan(root.get("voteCount"), 1)).orderBy(builder.asc(root.get("name")));
        List<Voter> voterList = session.createQuery(query).getResultList();
        voterList.forEach(v -> System.out.println(v + " voted " + v.getVoteCount() + " times. Cheater."));

        LoaderSession.closeLoaderSession(session);
    }
}