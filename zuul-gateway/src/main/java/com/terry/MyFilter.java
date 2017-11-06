package com.terry;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/***
 * *
 * 名称：     MyFilter.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/7/5.
 * 说明：     
 * *
 ***/

@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // pre：路由之前
        // routing：路由之时
        // post： 路由之后
        // error：发送错误调用
        return "post";
    }

    @Override
    public int filterOrder() {
        //过滤的顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否要过滤
        return true;
    }

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("IP:{}  方法:{}  地址:{}", request.getRemoteAddr(), request.getMethod(), request.getRequestURL().toString());
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.setResponseBody("{\n" +
                "    \"code\":200,\n" +
                "    \"message\":\"This is from zuul gateway\",\n" +
                "    \"data\":{\n" +
                "        \"id\":\"6a2561b8-a47a-4108-a93c-7654ff50e983\",\n" +
                "        \"name\":\"Merry li\",\n" +
                "        \"img\":\"http://up.qqjia.com/z/16/tu17317_45.png\",\n" +
                "        \"sex\":\"girl\",\n" +
                "        \"qianmin\":\"The future is already here. It’s just not evenly distributed yet\",\n" +
                "        \"age\":19\n" +
                "    }\n" +
                "}");
        return null;
    }

}
