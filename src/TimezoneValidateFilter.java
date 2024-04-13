import javax.servlet.*;
import java.io.IOException;
import java.time.ZoneId;

public class TimezoneValidateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String timezone = request.getParameter("timezone");
        if (timezone != null && !isValidTimeZone(timezone)) {
            response.setContentType("text/html");
            response.getWriter().println("<html><body><h1>Invalid timezone</h1></body></html>");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isValidTimeZone(String timezone) {
        try {
            ZoneId.of(timezone, ZoneId.SHORT_IDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
