package com.hy.cuis.manager.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hy.cuis.framework.config.HYCuisConfig;
import com.hy.cuis.framework.web.controller.BaseController;
import com.hy.cuis.manager.system.menu.domain.Menu;
import com.hy.cuis.manager.system.menu.service.IMenuService;
import com.hy.cuis.manager.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private HYCuisConfig hYCuisConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(Model model)
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);
        model.addAttribute("copyrightYear", hYCuisConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(Model model)
    {
        model.addAttribute("version", hYCuisConfig.getVersion());
        return "main";
    }

}