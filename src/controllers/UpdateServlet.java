package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasklist;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())) {
		    EntityManager em = DBUtil.createEntityManager();

		    //インスタンスからタスクIDを取得する
		    //該当IDのレコードをDBから取得する
		    Tasklist t = em.find(Tasklist.class,(Integer)(request.getSession().getAttribute("task_id")));

		    //変更したデータをプロパティに更新する
		    String title = request.getParameter("title");
		    t.setTitle(title);

		    String content = request.getParameter("content");
		    t.setContent(content);

		    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		    t.setUpdated_at(currentTime);

		    //DBを更新
		    em.getTransaction().begin();
		    em.getTransaction().commit();
		    em.close();

		    //セッションスコープのインスタンスを削除する。
		    request.getSession().removeAttribute("task_id");

		    //一覧画面にリダイレクト
		    response.sendRedirect(request.getContextPath()+"/index");

		}
	}

}
