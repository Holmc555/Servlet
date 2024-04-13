import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String timezone = request.getParameter("timezone");
        ZoneId zoneId;
        try {
            zoneId = ZoneId.of(timezone, ZoneId.SHORT_IDS);
        } catch (Exception e) {
            zoneId = ZoneId.of("UTC");
        }
        ZonedDateTime zoneTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss VV");
        String timeStr = formatter.format(zoneTime);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Current Time: " + timeStr + "</h1>");
        out.println("</body></html>");
    }
}
