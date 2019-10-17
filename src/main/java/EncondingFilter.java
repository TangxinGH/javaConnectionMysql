import java.io.IOException;
import javax.servlet.*;
public class EncondingFilter implements Filter {
protected String encoding = null;
protected FilterConfig config;
public void init(FilterConfig filterConfig) {
this.config = filterConfig;

this.encoding = filterConfig.getInitParameter("Encoding");
}
public void doFilter(
ServletRequest request,
ServletResponse response,
FilterChain chain)
throws IOException, ServletException {
if (request.getCharacterEncoding() == null) {

String encode = getEncoding();
if (encode != null) {

request.setCharacterEncoding(encode);
response.setCharacterEncoding(encode);
}
}
chain.doFilter(request, response);
}
protected String getEncoding() {
return encoding;
}
public void destroy() {
}
}