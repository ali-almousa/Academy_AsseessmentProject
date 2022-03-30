package controler;

import dao.ClassDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import model.Class;
import model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "controler.courseServlet", value = "/course")
public class courseServlet extends HttpServlet {
    private CourseDAO courseDAO;
    private ClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        courseDAO = new CourseDAO();
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

    private void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        Set<Course> courses = courseDAO.getAllCourse();
        request.setAttribute("courses", courses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/course/course-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showAssignCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,SQLException{
        Set<Class> classes = classDAO.getAllCourse();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("classes", classes);
        request.setAttribute("courseId", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/course/assign-form.jsp");
        dispatcher.forward(request, response);
    }

    private void assignCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classId = Integer.parseInt(request.getParameter("id"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        courseDAO.assignCourse(classId, courseId);
        response.sendRedirect("course?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/course/course-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course existingCourse = courseDAO.getCourse(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/course/course-form.jsp");
        request.setAttribute("course", existingCourse);
        dispatcher.forward(request, response);
    }

    private void insertCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        Course newCourse = new Course(title);
        courseDAO.saveCourse(newCourse);
        response.sendRedirect("course?action=list");
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
//        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String title = request.getParameter("title");
        Course course = new Course(id, title);


        courseDAO.updateCourse(course);
        response.sendRedirect("course?action=list");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        courseDAO.deleteCourse(id);
        response.sendRedirect("course?action=list");
    }
}
