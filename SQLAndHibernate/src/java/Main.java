import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;


public class Main {
    public static void main(String[] args) {
        printCoursesAverageMonthSales();
        printHibernateTableData();


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
        course.getStudents().stream().map(Student::getName).forEach(System.out::println);

//        Transaction transaction = session.beginTransaction();
//        Course course = session.get(Course.class, 47);
//        course.setName("Optimization of the content of training courses from 0 to PRO");
//        session.save(course);
//        session.delete(course);
//        transaction.commit();

        SkillboxSession.closeSkillboxSession(session);
    }
}