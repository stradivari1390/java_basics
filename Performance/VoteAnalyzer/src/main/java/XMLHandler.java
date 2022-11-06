import jakarta.persistence.PersistenceException;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
public class XMLHandler extends DefaultHandler {

    private final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    private Voter voter;

    Session session = null;

    Transaction transaction = null;

    @Override
    public void startDocument() {
        session = LoaderSession.startSession(session);
     //   transaction = session.beginTransaction();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter") && voter == null) {
            Date birthday;
            String name = attributes.getValue("name");
            try {
                birthday = birthDayFormat.parse(attributes.getValue("birthDay"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            try {
                transaction = session.beginTransaction();
                voter = new Voter(new VoterKey(name, birthday), name, birthday, 1);
                session.persist(voter);
                transaction.commit();
            } catch (PersistenceException exception) {
                if(!transaction.isActive()) {
                    transaction = session.beginTransaction();
                }
                voter = session.get(Voter.class, new VoterKey(name, birthday));
                voter.setVoteCount(voter.getVoteCount() + 1);
                session.persist(voter);
                transaction.commit();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    @Override
    public void endDocument() {
        LoaderSession.closeLoaderSession(session);
    }
}
