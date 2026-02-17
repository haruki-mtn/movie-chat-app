package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.mtn.moviechat.model.Register;
import jp.mtn.moviechat.model.UserLogic;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープにflashMsgが存在するならば取得してリクエストスコープに保存
		HttpSession session = request.getSession();
		if (session.getAttribute("flashMsg") != null) {
			request.setAttribute("flashMsg", session.getAttribute("flashMsg"));
			request.setAttribute("flashType", session.getAttribute("flashType"));

			// セッションスコープからflashを削除
			session.removeAttribute("flashMsg");
			session.removeAttribute("flashType");
		}
		// register.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメーターを取得
		String name = request.getParameter("name").trim();
		String mail = request.getParameter("mail").trim();
		String pass = request.getParameter("pass").trim();

		HttpSession session = request.getSession();

		// 入力チェック
		if (name == null || name.isEmpty() || mail == null || mail.isEmpty() || pass == null || pass.isEmpty()) {
			// セッションスコープにフラッシュメッセージを保存
			session.setAttribute("flashMsg", "未入力の項目があります");
			session.setAttribute("flashType", "error");
			// RegisterServletにリダイレクト
			response.sendRedirect(request.getContextPath() + "/register");
			return;
		}

		// 登録処理の実行
		Register register = new Register(name, mail, pass);
		UserLogic bo = new UserLogic();
		boolean result = bo.register(register);

		// 登録処理の成否によって処理を分岐
		if (result) {
			// セッションスコープにフラッシュメッセージを保存
			session.setAttribute("flashMsg", "登録が完了しました");
			session.setAttribute("flashType", "success");
			// LoginServletにリダイレクト
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			// セッションスコープにフラッシュメッセージを保存
			session.setAttribute("flashMsg", "登録に失敗しました");
			session.setAttribute("flashType", "error");
			// RegisterServletにリダイレクト
			response.sendRedirect(request.getContextPath() + "/register");
			return;
		}
	}
}
