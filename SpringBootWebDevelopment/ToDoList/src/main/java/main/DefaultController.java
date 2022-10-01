package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class DefaultController {
    @RequestMapping("/")
    public String info() {
        return new SimpleDateFormat("dd MMM yyyy GG").format(new Date());
    }
}
