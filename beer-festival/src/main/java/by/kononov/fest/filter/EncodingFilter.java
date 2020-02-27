package by.kononov.fest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
	urlPatterns = { "/*" },
	initParams = { @WebInitParam(name = "unicode", value = "UTF-8", description = "encoding parameter") }
)
public class EncodingFilter implements Filter{
	private String code;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		code = filterConfig.getInitParameter("unicode");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String codeRequest = servletRequest.getCharacterEncoding();
		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			servletRequest.setCharacterEncoding(code);
			servletResponse.setCharacterEncoding(code);
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		code = null;
	}
}