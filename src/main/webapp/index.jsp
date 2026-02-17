<%
// loginUserが存在しないならばLoginServletにリダイレクト、存在するならHomeServletにリダイレクト
if (session.getAttribute("loginUser") == null) {
    response.sendRedirect(request.getContextPath() + "/login");
} else {
    response.sendRedirect(request.getContextPath() + "/app/home");
}
%>
