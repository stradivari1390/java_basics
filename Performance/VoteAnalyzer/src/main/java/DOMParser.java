import jakarta.persistence.PersistenceException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DOMParser {

    private final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    public void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        putDataIntoDB(doc);
    }

    public void putDataIntoDB(Document doc) throws ParseException {
        Session session = null;
        session = LoaderSession.startSession(session);

        Transaction transaction = session.beginTransaction();

        NodeList voters = doc.getElementsByTagName("voter");
        for (int i = 0; i < voters.getLength(); i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            Date birthday = birthDayFormat.parse(attributes.getNamedItem("birthDay").getNodeValue());
            try {
                Voter voter = new Voter(new VoterKey(name, birthday), name, birthday, 1);
                session.persist(voter);
            } catch (NonUniqueObjectException exception) {
                Voter voter = session.get(Voter.class, new VoterKey(name, birthday));
                voter.setVoteCount(voter.getVoteCount() + 1);
                session.refresh(voter);
            }
        }
        try {
            transaction.commit();
        } catch (PersistenceException exception) {
            System.out.println("Claimed data is already in Database");
        }
        LoaderSession.closeLoaderSession(session);
    }
}
