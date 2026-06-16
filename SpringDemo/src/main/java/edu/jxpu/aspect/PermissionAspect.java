package edu.jxpu.aspect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class PermissionAspect {

    @Around("@annotation(requireRole)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, RequireRole requireRole) throws Throwable {
        // 解析允许访问的角色
        String allowedRolesStr = requireRole.value();
        Set<String> allowedRoles = new HashSet<>(Arrays.asList(allowedRolesStr.split(",")));

        // 获取请求和Session
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("无法获取请求上下文");
        }
        
        String account = (String) attributes.getRequest().getSession().getAttribute("account");
        if (account == null) {
            // 未登录，返回401错误
            return returnJsonError(attributes.getResponse(), 401, "未登录，请先登录");
        }

        // 获取当前用户角色
        String userRole = (String) attributes.getRequest().getSession().getAttribute("role");
        if (userRole == null) {
            userRole = "user"; // 默认普通用户
        }

        // 检查权限
        if (allowedRoles.contains(userRole)) {
            // 有权限，继续执行
            return joinPoint.proceed();
        } else {
            // 无权限，返回403错误
            return returnJsonError(attributes.getResponse(), 403, "您没有权限访问此功能");
        }
    }
    
    // 工具方法：返回JSON格式的错误信息
    private Object returnJsonError(HttpServletResponse response, int code, String message) throws Exception {
        response.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);
        
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(result));
        response.getWriter().flush();
        return null;
    }
}