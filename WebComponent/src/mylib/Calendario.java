package mylib;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Calendario implements WebComponent {

    public Calendar selectedDay;
    private static final String DAYS[] = {"Sun","Mon","Tus","Wed","Thu","Fri","Sat"};
    private static final String header = "<td valign=\"top\">\n" +
            "<table bordercolordark=\"#000000\" bordercolorlight=\"#FFFFFF\" border=\"1\" cellpadding=\"5\" width=\"100%\" <tbody><tr>";
    private static final String table_entry = "<td align=\"left\" valign=\"top\" width=\"14%\">";
    private static final int DAYS_IN_A_WEEK = 7;

    public Calendario() {
    }

    public void preRender(List<String> headtext) {
        headtext.add("<style type=\"text/css\">\nspan.Calendario {color:red;}</style>");
    }

    public void render(PrintStream output) {

        Calendar first = new GregorianCalendar(selectedDay.get(Calendar.YEAR), selectedDay.get(Calendar.MONTH), 1);
        
        int offset = first.get(Calendar.DAY_OF_WEEK);

        int days = first.getActualMaximum(Calendar.DAY_OF_MONTH);

        output.println(header);
        for (int i=0;i<DAYS_IN_A_WEEK;i++) output.println("<th align=\"center\" bgcolor=\"#000000\" valign=\"center\" width=\"14%\"><font color=\"#ffffff\" face=\"Verdana\" size=\"1\">" + DAYS[i] + "</font></th>");
        int i=1;
        for (; i < offset + days; i++) {
            if ((i - 1) % DAYS_IN_A_WEEK == 0) {
                output.println("</tr><tr>");
            }
            output.print(table_entry);
            if (i >= offset) {
                if(i - offset + 1 == selectedDay.get(Calendar.DAY_OF_MONTH))output.print("<span class=\"Calendario\">"+(i - offset + 1)+"</span>");
                else output.print(i - offset + 1);
            }
            output.println("</td>");
        }
        while ((i - 1) % DAYS_IN_A_WEEK != 0) {
            output.println(table_entry + "</td>");
            i++;
        }
        output.println("</tr></tbody></table> </td> </tr> ");
    }
}
