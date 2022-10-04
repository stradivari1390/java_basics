package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


@RestController
public class DefaultController {

    @RequestMapping("/")
    public String info() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        return "Maybe I will create Jason file or database with tasks on " +
                new SimpleDateFormat("yyyy~MM~dd").format(calendar.getTime()) +
                " and improve my REST API skills =)";
    }
}