package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/app/account/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープを破棄
        HttpSession session = request.getSession();
        session.invalidate();

        // フラッシュメッセージをセッションスコープに保存
        session = request.getSession();
        session.setAttribute("flashMsg", "ログアウトしました");
        session.setAttribute("flashType", "success");

        // LoginServletにリダイレクト
        response.sendRedirect(request.getContextPath() + "/login");
        return;
	}
}
