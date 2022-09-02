import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Logger logger;
    private static final String ADD_COMMAND = "add Vasiliy Petrov " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Vasiliy Petrov\n\thelp";
    public static final String COMMAND_ERROR = "Wrong command! Commands were expected: \n" +
            COMMAND_EXAMPLES;
    private static final String HELP_TEXT = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        logger = LogManager.getRootLogger();
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            try {
                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                    logger.info("command entered: " + command);
                } else if (tokens[0].equals("list") && tokens.length == 1) {
                    executor.listCustomers();
                    logger.info("command entered: " + command);
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                    logger.info("command entered: " + command);
                } else if (tokens[0].equals("count") && tokens.length == 1) {
                    System.out.println("There are " + executor.getCount() + " customers");
                    logger.info("command entered: " + command);
                } else if (tokens[0].equals("help") && tokens.length == 1) {
                    System.out.println(HELP_TEXT);
                    logger.info("command entered: " + command);
                } else if (tokens[0].equals("exit") && tokens.length == 1) {
                    logger.info("command entered: " + command);
                    break;
                } else {
                    System.out.println(COMMAND_ERROR);
                    logger.error("wrong insertion: " + command + " - " + COMMAND_ERROR);
                }
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException exception) {
                System.out.println(COMMAND_ERROR);
                logger.error("wrong insertion: " + command + " - " + exception.getMessage());
            }
        }
    }
}