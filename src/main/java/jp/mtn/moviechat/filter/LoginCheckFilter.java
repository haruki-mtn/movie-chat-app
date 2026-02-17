package jp.mtn.moviechat.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.mtn.moviechat.model.User;

@WebFilter("/app/*")
public class LoginCheckFilter extends HttpFilter {
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // セッションスコープからログインユーザー（Userオブジェクトのインスタンス）を取得
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        // loginUserが存在しないならばLoginServletにリダイレクト
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

		chain.doFilter(request, response);
	}
}
