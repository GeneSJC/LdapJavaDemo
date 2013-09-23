package ru.mrgrechkinn.java.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ru.mrgrechkinn.java.webapp.ldap_testing.model.Group;
import ru.mrgrechkinn.java.webapp.ldap_testing.model.User;
import ru.mrgrechkinn.java.webapp.ldap_testing.service.AuthenticationService;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AuthenticationService auth = (AuthenticationService) context.getBean("authenticationService");
        List<User> users = auth.getAllUsers();
        PrintWriter out = response.getWriter();
        for (User user : users) {
            out.write("User: " + user.getUid() + " " + user.getCommonName() + " " + user.getLastName() + "\n");
            List<Group> groups = auth.getUserGroups(user.getUid());
            if (!groups.isEmpty()) {
                out.write("Groups: ");
                for (Group group : groups) {
                    out.write(group.name() + " ");
                }
                out.write("\n");
            }
        }
        out.close();
    }

    /**
     * {@inheritDoc}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
    }

}
