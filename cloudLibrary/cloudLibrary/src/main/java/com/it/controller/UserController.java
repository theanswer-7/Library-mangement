package com.it.controller;

import com.it.pojo.User;
import com.it.service.UserService;
import com.it.utils.PageResult;
import com.it.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(User user, HttpServletRequest request) {
        System.out.println("您的用户名：" + user.getEmail());
        System.out.println("您的密码：" + user.getPassword());
        User user1 = userService.login(user);
        if (user1 != null) {
            request.getSession().setAttribute("USER_SESSION", user1);
            return "redirect:/admin/main.jsp";
        } else {
            request.setAttribute("msg", "您的用户名或者密码错误!");
            return "forward:/admin/login.jsp";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "forward:/admin/login.jsp";
    }

    @GetMapping("/main")
    public String toMain() {
        return "main";
    }

    // 查询用户列表（分页 + 模糊搜索）
    @RequestMapping("/search")
    public ModelAndView search(User user,
                               @RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        PageResult pageResult = userService.search(user, pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("gourl", "/user/search");
        modelAndView.addObject("search", user);
        return modelAndView;
    }

    // 根据id查询用户（编辑时回填）
    @GetMapping("/findUserById")
    @ResponseBody
    public Result<User> findUserById(int id) {
        User user = userService.findById(id);
        if (user != null) {
            return new Result<>(true, "查询成功", user);
        } else {
            return new Result<>(false, "查询失败", null);
        }
    }

    // 新增用户（管理员权限 + 邮箱唯一性校验）
    @PostMapping("/addUser")
    @ResponseBody
    public Result addUser(User user, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(currentUser.getRole())) {
            return new Result(false, "非管理员无权新增用户", null);
        }
        if (userService.checkEmail(user.getEmail()) > 0) {
            return new Result(false, "该邮箱已被注册", null);
        }
        int count = userService.addUser(user);
        if (count > 0) {
            return new Result(true, "新增用户成功", null);
        } else {
            return new Result(false, "新增用户失败", null);
        }
    }

    // 编辑用户（管理员可编辑所有人，普通用户只能编辑自己）
    @PostMapping("/editUser")
    @ResponseBody
    public Result editUser(User user, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(currentUser.getRole()) && !currentUser.getId().equals(user.getId())) {
            return new Result(false, "无权修改他人数据，只能修改自己的信息", null);
        }
        int count = userService.editUser(user);
        if (count > 0) {
            return new Result(true, "修改成功", null);
        } else {
            return new Result(false, "修改失败", null);
        }
    }

    // 离职办理（管理员权限，软删除）
    @GetMapping("/delUser")
    @ResponseBody
    public Result delUser(int id, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(currentUser.getRole())) {
            return new Result(false, "非管理员无权办理离职", null);
        }
        User user = new User();
        user.setId(id);
        int count = userService.delUser(user);
        if (count > 0) {
            return new Result(true, "离职办理成功", null);
        } else {
            return new Result(false, "离职办理失败", null);
        }
    }

    // 永久删除用户（管理员权限）
    @GetMapping("/removeUserById")
    @ResponseBody
    public Result removeUserById(int id, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(currentUser.getRole())) {
            return new Result(false, "非管理员无权删除用户", null);
        }
        int count = userService.removeUserById(id);
        if (count > 0) {
            return new Result(true, "删除成功", null);
        } else {
            return new Result(false, "删除失败", null);
        }
    }

    // 校验用户名是否已存在
    @PostMapping("/checkName")
    @ResponseBody
    public Result checkName(String name) {
        int count = userService.checkName(name);
        if (count > 0) {
            return new Result(false, "该用户名已存在", null);
        } else {
            return new Result(true, "用户名可用", null);
        }
    }

    // 校验邮箱是否已存在
    @PostMapping("/checkEmail")
    @ResponseBody
    public Result checkEmail(String email) {
        int count = userService.checkEmail(email);
        if (count > 0) {
            return new Result(false, "该邮箱已被注册", null);
        } else {
            return new Result(true, "邮箱可用", null);
        }
    }

    // 跳转注册页面
    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    // 注册（只能注册普通用户）
    @PostMapping("/register")
    public String register(User user, HttpServletRequest request) {
        if (userService.checkEmail(user.getEmail()) > 0) {
            request.setAttribute("msg", "该邮箱已被注册");
            return "forward:/admin/register.jsp";
        }
        int count = userService.register(user);
        if (count > 0) {
            request.setAttribute("msg", "注册成功，请登录");
            return "forward:/admin/login.jsp";
        } else {
            request.setAttribute("msg", "注册失败");
            return "forward:/admin/register.jsp";
        }
    }

    // 跳转个人资料页面
    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("USER_SESSION");
        User user = userService.findById(sessionUser.getId());
        request.setAttribute("profileUser", user);
        return "profile";
    }

    // 修改密码
    @PostMapping("/updatePassword")
    @ResponseBody
    public Result updatePassword(@RequestParam String oldPwd,
                                  @RequestParam String newPwd,
                                  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        int count = userService.updatePassword(user.getId(), oldPwd, newPwd);
        if (count == -1) {
            return new Result(false, "原密码错误", null);
        } else if (count > 0) {
            return new Result(true, "密码修改成功", null);
        } else {
            return new Result(false, "密码修改失败", null);
        }
    }

    // 加入黑名单（管理员）
    @RequestMapping("/banUser")
    @ResponseBody
    public Result banUser(String name, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(currentUser.getRole())) {
            return new Result(false, "非管理员无权操作", null);
        }
        int count = userService.banUser(name);
        if (count > 0) {
            return new Result(true, "已将 " + name + " 加入黑名单", null);
        } else {
            return new Result(false, "操作失败", null);
        }
    }

    // 移出黑名单（管理员）
    @RequestMapping("/unbanUser")
    @ResponseBody
    public Result unbanUser(String name, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(currentUser.getRole())) {
            return new Result(false, "非管理员无权操作", null);
        }
        int count = userService.unbanUser(name);
        if (count > 0) {
            return new Result(true, "已将 " + name + " 移出黑名单", null);
        } else {
            return new Result(false, "操作失败", null);
        }
    }
}
