package jdbc.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class pw {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//        java.util.Date birth = sdf.parse(birthDay);
        LocalDate now = LocalDate.now();
        LocalDate ageCheck = now.plusYears(-12);






        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);




//        int result = birth.compareTo(date);

//        System.out.println(result);


//        Date formatDate = (Date) sdf.parse(birthDay);
//
//        sdf.parse(birthDay);
//


//
//        System.out.println(now);
//        System.out.println(ageCheck);
//        System.out.println(formatDate);


    }
}