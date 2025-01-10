package cn.graht.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.graht.common.ErrorCode;
import cn.graht.common.ResultApi;
import cn.graht.common.ResultUtil;
import exception.ThrowUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GRAHT
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public ResultApi<SaTokenInfo> login(String username,String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        ThrowUtils.throwIf(StringUtils.isBlank(username) || StringUtils.isBlank(password), ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(StringUtils.isBlank(username) || StringUtils.isBlank(password), ErrorCode.PARAMS_ERROR);
        if ("admin".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
        } else if ("super".equals(username) && "123456".equals(password)) {
            StpUtil.login(10002);
        }
        // 第3步，返回给前端
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return ResultUtil.ok(tokenInfo);
    }
    @SaCheckLogin
    @GetMapping("/info")
    public ResultApi<String> info() {
        return ResultUtil.ok("是否登录");
    }

    @GetMapping("/user")
    @SaCheckPermission("user")
    public ResultApi<String> user() {
        return ResultUtil.ok("是否有user权限");
    }
}
