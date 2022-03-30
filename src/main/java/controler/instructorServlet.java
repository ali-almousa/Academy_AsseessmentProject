package controler;


import dao.ClassDAO;
import dao.InstructorDAO;
import model.Class;
import model.Instructor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "controler.instructorServlet", value = "/instructor")
public class instructorServlet extends HttpServlet {
    private InstructorDAO instructorDAO;
    private ClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        instructorDAO = new InstructorDAO();
        classDAO = new ClassDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = "/" + request.getParameter("action");
//        System.out.println(action);
        String action = "/" + request.getParameter("action");
//        String action = request.getServletPath();
//        String action = "/"+request.getServletPath().split("/")[2];

        System.out.println(action);
        try {
            switch (action){
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCourse(request, response);
                    break;
                case "/delete":
                    deleteCourse(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCourse(request, response);
                    break;
                case "/assignForm":
                    showAssignCourseForm(request, response);
                    break;
                case "/assign":
                    assignCourse(request, response);
                    break;
                default:
                    listCourse(request, response);
                    break;
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void showAssignCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        Set<Class> classes = classDAO.getAllCourse();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("classes", classes);
        request.setAttribute("instructorId", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/instructor/assign-form.jsp");
        dispatcher.forward(request, response);
    }

    private void assignCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classId = Integer.parseInt(request.getParameter("classId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int instructorId = Integer.parseInt(request.getParameter("instructorId"));
        instructorDAO.assignCourse(classId, courseId, instructorId);
        response.sendRedirect("instructor?action=list");
    }

    private void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        Set<Instructor> instructors = instructorDAO.getAllCourse();
        request.setAttribute("instructors", instructors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/instructor/course-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/instructor/course-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Instructor existingInstructor = instructorDAO.getCourse(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/instructor/course-form.jsp");
        request.setAttribute("instructor", existingInstructor);
        dispatcher.forward(request, response);
    }

    private void insertCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Instructor newInstructor = new Instructor(firstName, lastName, email);
        instructorDAO.saveCourse(newInstructor);
        response.sendRedirect("instructor?action=list");
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
//        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Instructor newInstructor = new Instructor(id, firstName, lastName, email);


        instructorDAO.updateCourse(newInstructor);
        response.sendRedirect("instructor?action=list");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        instructorDAO.deleteCourse(id);
        response.sendRedirect("instructor?action=list");
    }
}
