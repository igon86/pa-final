import mylib.*;

import java.util.List;
import java.io.PrintStream;
import java.util.Calendar;

public class Page implements WebComponent {

	protected Label data;
	protected Calendario cal;
	protected Button but;

	public Page() {
		data = new Label();
		cal = new Calendario();
		but = new Button();
	}
	public void preRender(List<String> headtext){
		data.preRender(headtext);
		cal.preRender(headtext);
		but.preRender(headtext);
	}
	public void render(PrintStream output, List<String> headText){
		output.println("<html>");
		output.println("<head>");
		for ( String s : headText) output.println(s);
		output.println("</head>");
		output.println("<body>");
		data.text="Date:";
		data.render(output,headText);
		output.println("<p>");
		cal.selectedDay=Calendar.getInstance();
		cal.render(output,headText);
		output.println("</p>");
		but.image="http://www.cli.di.unipi.it/~lottarin/putto.jpg";
		but.render(output,headText);
		output.println("</body>");
		output.println("</html>");
	}
}
