import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class CheckParamServlet extends HttpServlet{
public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws IOException {
String name = request.getParameter("name");
String pass = request.getParameter("pass");
response.setContentType("text/html;charset=gb2312");
PrintWriter out = response.getWriter();
out.println("<html><head><title>Param Test</title></head>");
out.println("<h3 align=center>用户名"+name+"</h3>");
out.println("<h3 align=center>口令"+pass+"</h3>");
out.println("</body></html>");
}
public void doPost(HttpServletRequest request,
HttpServletResponse response)
throws IOException {
doGet(request,response);
}
}