/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Virginie
 */
@Named
@ApplicationScoped
public class Format {
    
    private static DateFormat dateHourFormat;

    /**
     * Creates a new instance of Format
     */
    public Format() {
        dateHourFormat = new SimpleDateFormat(Constants.DATE_HOUR_FORMAT);
    }
    
    public String dateHour(Date d){
        return dateHourFormat.format(d);
    }
    
    public String overview(String s){
        if(s.length() > 100){
            return String.format("%s...", s.substring(0, 100));
        }
        return s;
    }
    
}
