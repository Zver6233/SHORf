package SHLK;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        URL url=new URL(req.getParameter("shorten_url"));



        req.setAttribute("name","http://localhost:8080/"+url.doShort());
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String[] s =req.getRequestURI().split("/");

        URL_MANIP shorturl=new URL_MANIP();

        if(!(s.length==0)) {
            if((shorturl.ifExistTinyURL(shorturl.getLinks(s[1]))))
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            else {
                req.setAttribute("URL", shorturl.getLinks(s[1]));
                req.getRequestDispatcher("away.jsp").forward(req, resp);
            }
        }

        else

            req.getRequestDispatcher("mypage.jsp").forward(req, resp);

    }


}
