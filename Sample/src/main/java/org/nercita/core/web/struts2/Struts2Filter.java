package org.nercita.core.web.struts2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;
import org.apache.struts2.dispatcher.ng.filter.FilterHostConfig;

/**
 * 修改原过滤器bug,可以在struts.xml设置编码.
 * @author wangzz
 * @since 2009-3-28
 */
public class Struts2Filter implements StrutsStatics, Filter {
    private PrepareOperations prepare;
    private ExecuteOperations execute;

    public void init(FilterConfig filterConfig) throws ServletException {
        InitOperations init = new InitOperations();
        try {
            FilterHostConfig config = new FilterHostConfig(filterConfig);
            init.initLogging(config);
            Dispatcher dispatcher = init.initDispatcher(config);
            init.initStaticContentLoader(config, dispatcher);

            prepare = new PrepareOperations(filterConfig.getServletContext(), dispatcher);
            execute = new ExecuteOperations(filterConfig.getServletContext(), dispatcher);
        } finally {
            init.cleanup();
        }

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
        	//设置编码
        	prepare.setEncodingAndLocale(request, response);
            prepare.createActionContext(request, response);
            prepare.assignDispatcherToThread();
            request = prepare.wrapRequest(request);
            ActionMapping mapping = prepare.findActionMapping(request, response);
            if (mapping == null) {
                boolean handled = execute.executeStaticResourceRequest(request, response);
                if (!handled) {
                    chain.doFilter(request, response);
                }
            } else {
                execute.executeAction(request, response, mapping);
            }
        } finally {
            prepare.cleanupRequest(request);
        }
    }

    public void destroy() {
        prepare.cleanupDispatcher();
    }
}