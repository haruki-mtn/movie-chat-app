package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/app/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストスコープにヘッダータイトルとアクティブなナビゲーションを保存
		request.setAttribute("headerTitle", "ホーム");
		request.setAttribute("activeNav", "home");

		// セッションスコープにflashMsgが存在するならば取得してリクエストスコープに保存
		HttpSession session = request.getSession();
		if (session.getAttribute("flashMsg") != null) {
			request.setAttribute("flashMsg", session.getAttribute("flashMsg"));
			request.setAttribute("flashType", session.getAttribute("flashType"));

			// セッションスコープからflashを削除
			session.removeAttribute("flashMsg");
			session.removeAttribute("flashType");
		}

		// home.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
	}
}
