package mylib;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author andrealottarini
 */
public class Calendario implements WebComponent{
    
    public Calendar selectedDay;

    private static final String header = "<td valign=\"top\">\n" +
            "<table bordercolordark=\"#000000\" bordercolorlight=\"#FFFFFF\" border=\"1\" cellpadding=\"5\" width=\"100%\" <tbody><tr>\n" +
            "<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\"> Sun </font></th>" +
            "<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\"> Mon </font></th>" +
            "<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\"> Tus </font></th>" +
            "<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\"> Wed </font></th>" +
            "<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\"> Thu </font></th>" +
            "<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\"> Fri </font></th>" +
            "<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\"> Sat </font></th>" +           
            "</tr>";

    private static final String table_entry="<td align=\"left\" valign=\"top\" width=\"14%\">";

    private static final String table_closure="</td>";

    private static final int DAY_OF_THE_WEEK=7;

    private static final String footer = "</tr></tbody></table> </td> </tr> ";

    public Calendario(Calendar c){
        this.selectedDay = c;
    }

    public Calendario(){
        
    }

    @Override
    public void preRender(List<String> headtext) {
        
    }

    @Override
    public void render(PrintStream output,List<String> headtext) {
        System.out.println("ANNO: "+selectedDay.get(Calendar.YEAR)+" MESE: "+selectedDay.get(Calendar.MONTH));
        System.out.println("GIORNO DELLA SETTIMANA: "+selectedDay.get(Calendar.DAY_OF_WEEK));
        Calendar first = new GregorianCalendar(selectedDay.get(Calendar.YEAR),selectedDay.get(Calendar.MONTH),1);
        System.out.println("ANNO: "+first.get(Calendar.YEAR)+" MESE: "+first.get(Calendar.MONTH));
        System.out.println("GIORNO DELLA SETTIMANA: "+first.get(Calendar.DAY_OF_WEEK));
//        System.out.println("First day of week: "+first.getFirstDayOfWeek());
//        first.setFirstDayOfWeek(Calendar.WEDNESDAY);
//        System.out.println("First day of week: "+first.getFirstDayOfWeek());
        int offset = first.get(Calendar.DAY_OF_WEEK);
        System.out.println("offset: "+offset);
        int days = first.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("Ci sono " +days +" giorni.");
        output.println(header);
        int i=1;
        for(;i<offset + days;i++){
            if((i-1)%DAY_OF_THE_WEEK == 0){
                output.println("</tr><tr>");
            }
            output.print(table_entry);
            if (i>=offset) output.print(i-offset+1);
            output.println(table_closure);
        }
        while((i-1)%DAY_OF_THE_WEEK != 0){
            output.println(table_entry + table_closure);
            i++;
        }
        output.println(footer);


    }
}
