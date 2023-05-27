package com.publicapi.test.domain.user.controller;

import com.publicapi.test.domain.community.entity.Post;
import com.publicapi.test.domain.community.service.PostService;
import com.publicapi.test.domain.user.entity.UserEntity;
import com.publicapi.test.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final String tabName = "mypage";

    private final UserService userService;
    private final PostService postService;

    @GetMapping("article")
    public String getArticles(HttpServletRequest request,
                              Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page) {
        String id = (String) request.getSession().getAttribute("kakaoId");
        UserEntity user = userService.getLoginUser(id);
        Page<Post> postPage = postService.findByUserId(user, page);

        model.addAttribute("user", user);
        model.addAttribute("postPage", postPage);
        model.addAttribute("tab", tabName);
        model.addAttribute("mypageTab", "article");
        return "user/mypage_article";
    }
}
