package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.mtn.moviechat.model.User;
import jp.mtn.moviechat.model.UserLogic;

@WebServlet("/app/account/edit/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションスコープからログインユーザー（Userオブジェクトのインスタンス）を取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");

        // アカウント削除処理の実行
        UserLogic bo = new UserLogic();
        boolean result = bo.delete(user);

		// 登録処理の成否によって処理を分岐
        if (result) {
            // セッションスコープを破棄
            session.invalidate();

            // フラッシュメッセージをセッションスコープに保存
            session = request.getSession();
            session.setAttribute("flashMsg", "アカウントを削除しました");
            session.setAttribute("flashType", "success");

            // LoginServletにリダイレクト
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } else {
            // セッションスコープにフラッシュメッセージを保存
			session.setAttribute("flashMsg", "アカウントの削除に失敗しました");
			session.setAttribute("flashType", "error");
			// AccountEditServletにリダイレクト
			response.sendRedirect(request.getContextPath() + "/app/account/edit");
			return;
        }
	}
}
