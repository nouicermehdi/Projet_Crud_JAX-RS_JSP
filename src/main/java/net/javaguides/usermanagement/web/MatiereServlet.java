package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.dao.MatiereDAO;
import net.javaguides.usermanagement.model.Matiere;

@WebServlet("/")
public class MatiereServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatiereDAO matiereDAO;

    public void init() {
        matiereDAO = new MatiereDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertMatiere(request, response);
                    break;
                case "/delete":
                    deleteMatiere(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateMatiere(request, response);
                    break;
                default:
                    listMatiere(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMatiere(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Matiere> listMatiere = matiereDAO.selectAllMatieres();
        request.setAttribute("listMatiere", listMatiere);
        RequestDispatcher dispatcher = request.getRequestDispatcher("matiere-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("matiere-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
        Long idMatiere = Long.parseLong(request.getParameter("idMatiere"));
        
        Matiere existingMatiere = matiereDAO.selectMatiere(idMatiere); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("matiere-form.jsp");
        request.setAttribute("matiere", existingMatiere); 
        dispatcher.forward(request, response);
    }

    private void insertMatiere(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String nomMatiere = request.getParameter("nomMatiere");
        Double coefficient = Double.parseDouble(request.getParameter("coefficient"));
        Matiere newMatiere = new Matiere(nomMatiere, coefficient);
        matiereDAO.insertMatiere(newMatiere);
        response.sendRedirect("list");
    }


	private void updateMatiere(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
        Long idMatiere = Long.parseLong(request.getParameter("idMatiere"));
		String nomMatiere = request.getParameter("nomMatiere");
        Double coefficient = Double.parseDouble(request.getParameter("coefficient"));

        Matiere m = new Matiere(idMatiere, nomMatiere, coefficient);
        matiereDAO.updateMatiere(m);
		response.sendRedirect("list");
	}


    private void deleteMatiere(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Long idMatiere = Long.parseLong(request.getParameter("idMatiere")); 
        matiereDAO.deleteMatiere(idMatiere);
        response.sendRedirect("list");
    }
}
