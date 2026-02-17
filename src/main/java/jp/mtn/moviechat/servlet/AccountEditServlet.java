package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/app/account/edit")
public class AccountEditServlet extends HttpServlet {
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

		// リクエストスコープにアクティブなナビゲーションを保存
		request.setAttribute("activeNav", "account");

		// リクエストスコープにモーダルメッセージとモーダルラベルを保存
		request.setAttribute("modalMsg", "本当にアカウントを削除しますか？");
		request.setAttribute("modalLabel", "削除");

		// accountEdit.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountEdit.jsp");
        dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
	}
}
