import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.text.DateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        printCoursesAverageMonthSales();
        printHibernateTableData();
        fillUpPurchaseLinkedList();

    }

    public static void printCoursesAverageMonthSales() {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "12345678";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("""
                    select course_name, COUNT(course_name)/(MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date)) + 1) average_sales
                    from skillbox.purchaselist\s
                    group by course_name
                    order by average_sales""");
            while (resultSet.next()) {
                String value = resultSet.getString("course_name") +
                        " - " +
                        Double.parseDouble(resultSet.getString("average_sales")) +
                        " average sales per month";
                System.out.println(value);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void printHibernateTableData() {
        Session session = null;
        session = SkillboxSession.startSession(session);

        Course course = session.get(Course.class, 1);
        System.out.println(course.getName() + " - [teacher:" + course.getTeacher().getName() +
                ", salary = " + course.getTeacher().getSalary() + " rub.];\n" +
                "amount of students - " + course.getStudents().size() + ":");
        //noinspection OptionalGetWithoutIsPresent
        course.getStudents().forEach(s -> System.out.println(s.getName() +
                ", subscription date: " +
                    DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(
                        s.getSubscriptions().stream().filter(subs -> subs.getCourseId() == course.getId())
                                .findFirst().get().getSubscriptionDate()
                    )
                )
        );

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root).where(builder.greaterThan(root.<Integer>get("price"), 100_000))
                .orderBy(builder.desc(root.get("price")));
        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();
        courseList.forEach(c -> System.out.println(c.getName() + " - " + c.getPrice()));

        String hql = "from " + Course.class.getSimpleName() + " where price > 120000";
        List<Course> courseList1 = session.createQuery(hql).getResultList();
        courseList1.stream().map(Course::getName).forEach(System.out::println);

        SkillboxSession.closeSkillboxSession(session);
    }

    public static void fillUpPurchaseLinkedList() {
        Session session = null;
        session = SkillboxSession.startSession(session);

        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);
        query.select(root);

        List<Purchase> purchaseList = session.createQuery(query).getResultList();
        for(Purchase purchase : purchaseList) {
            PurchaseLinkedList purchaseLinkedList = new PurchaseLinkedList();
            purchaseLinkedList.setId(new PurchaseLinkedListKey(purchase.getStudentName(), purchase.getCourseName()));
            purchaseLinkedList.setStudentName(purchase.getStudentName());
            purchaseLinkedList.setCourseName(purchase.getCourseName());
            session.save(purchaseLinkedList);
        }

        transaction.commit();

        SkillboxSession.closeSkillboxSession(session);
    }
}