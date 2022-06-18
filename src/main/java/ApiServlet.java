
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @WebServlet(urlPatterns = "/hello")
public class ApiServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   public ApiServlet() {

   }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      out.println("./api works. Try ./api/getMessage");
   }
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   }
}