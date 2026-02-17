package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.mtn.moviechat.model.Login;
import jp.mtn.moviechat.model.User;
import jp.mtn.moviechat.model.UserLogic;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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

		// login.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラーメータの取得
		String mail = request.getParameter("mail").trim();
		String pass = request.getParameter("pass").trim();

		HttpSession session = request.getSession();

		// 入力チェック
		if (mail == null || mail.isEmpty() || pass == null || pass.isEmpty()) {
			// セッションスコープにフラッシュメッセージを保存
			session.setAttribute("flashMsg", "未入力の項目があります");
			session.setAttribute("flashType", "error");
			// RegisterServletにリダイレクト
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// ログイン処理の実行
		Login login = new Login(mail, pass);
		UserLogic bo = new UserLogic();
		User user = bo.login(login);

		if (user != null) {
			// セッションスコープにログインユーザー（Userオブジェクトのインスタンス）を保存
			session.setAttribute("loginUser", user);
			// セッションスコープにフラッシュメッセージを保存
			session.setAttribute("flashMsg", "ログインしました");
			session.setAttribute("flashType", "success");
			// HomeServletにリダイレクト
			response.sendRedirect(request.getContextPath() + "/app/home");
			return;
		} else {
			// セッションスコープにフラッシュメッセージを保存
			session.setAttribute("flashMsg", "ログインに失敗しました");
			session.setAttribute("flashType", "error");
			// LoginServletにリダイレクト
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	}
}
