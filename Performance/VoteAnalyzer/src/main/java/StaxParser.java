import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

@Getter
@Setter
@AllArgsConstructor
public class StaxParser {

    private File file;
    private static Connection connection;
    public void getAllVoters() throws SQLException {
        int number = 0;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elections?user=root&password=12345678");
        connection.createStatement().execute("DROP TABLE IF EXISTS electors");
        connection.createStatement().execute("CREATE TABLE electors(" +
                        "name varchar(50) NOT NULL, birthday DATE NOT NULL, " +
                        "vote_count INT NOT NULL, CONSTRAINT voter_key PRIMARY KEY (name, birthday))");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into electors(birthday, name, vote_count) values ");
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(this.getFile()));
            while (eventReader.hasNext() && number <= 5_000_000) {
                if (stringBuilder.length() > 30_000_000) {
                    stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
                    stringBuilder.append(" on duplicate key update vote_count = vote_count + 1");
                    connection.createStatement().execute(stringBuilder.toString());
                    stringBuilder.setLength(0);
                    stringBuilder.append("insert into electors(birthday, name, vote_count) values ");
                }
                XMLEvent event = eventReader.nextEvent();
                if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    if (qName.equalsIgnoreCase("voter")) {
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute currentAt = attributes.next();
                            if (currentAt.getName().toString().equalsIgnoreCase("birthday")) {
                                stringBuilder.append("('")
                                        .append((currentAt.getValue()).replaceAll("\\.", "-"))
                                        .append("', '");
                            } else if (currentAt.getName().toString().equalsIgnoreCase("name")) {
                                stringBuilder.append(currentAt.getValue())
                                        .append("', 1), ");
                                break;
                            }
                        }
                        number++;
                    }
                } else if (event.getEventType() == XMLStreamConstants.END_DOCUMENT &&
                        stringBuilder.length() > "insert into electors(birthday, name, vote_count) values ".length()) {
                    stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
                    stringBuilder.append(" on duplicate key update vote_count = vote_count + 1");
                    connection.createStatement().execute(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        connection.close();
    }
}