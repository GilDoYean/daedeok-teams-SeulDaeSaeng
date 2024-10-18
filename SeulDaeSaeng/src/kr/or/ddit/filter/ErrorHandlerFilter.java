package kr.or.ddit.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class ErrorHandlerFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ErrorHandlerFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 코드 (필요시 구현)
        logger.info("ErrorHandlerFilter 초기화 완료");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper);

        if (responseWrapper.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            logger.warning("404 Not Found: " + httpRequest.getRequestURL());
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // 필터 종료 코드 (필요시 구현)
        logger.info("ErrorHandlerFilter 종료");
    }

    private class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {
        private int httpStatus;

        public CustomHttpServletResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public void sendError(int sc) throws IOException {
            super.sendError(sc);
            this.httpStatus = sc;
        }

        @Override
        public void setStatus(int sc) {
            super.setStatus(sc);
            this.httpStatus = sc;
        }

        @Override
        public void setStatus(int sc, String sm) {
            super.setStatus(sc, sm);
            this.httpStatus = sc;
        }

        public int getStatus() {
            return this.httpStatus;
        }
    }
}
