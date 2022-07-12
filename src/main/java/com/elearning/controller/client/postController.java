package com.elearning.controller.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.elearning.entities.NguoiDung;
import com.elearning.entities.Post;
import com.elearning.service.PostService;

import org.springframework.ui.Model;

@Controller
public class postController {
    @Autowired
    PostService postService;

    @ModelAttribute("loggedInUser")
    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/listPost")
    public String getPage(@RequestParam(defaultValue = "1") int page, Model model) {

        // defaut value lay tu ket qua dau tien
        Page<Post> list = postService.getPost(page - 1, 4);

        int totalPage = list.getTotalPages();

        List<Integer> pageList = new ArrayList<Integer>();

        // lap danh sach cac trang
        if (page == 1 || page == 2) {
            for (int i = 2; i <= 3 && i <= totalPage; i++) {
                pageList.add(i);
            }
        } else if (page == totalPage) {
            for (int i = totalPage; i >= totalPage - 2 && i > 1; i--) {
                pageList.add(i);
            }
            Collections.sort(pageList);
        } else {
            for (int i = page; i <= page + 1 && i <= totalPage; i++) {
                pageList.add(i);
            }
            for (int i = page - 1; i >= page - 1 && i > 1; i--) {
                pageList.add(i);
            }
            Collections.sort(pageList);
        }
        model.addAttribute("pageList", pageList);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("listData", list.getContent());
        model.addAttribute("currentPage", page);

        return "client/listPost";
    }

    @GetMapping("/detailPost")
    public String DetailBlog(@RequestParam(value = "idPost") int idPost, Model model) {

        Post baipost = postService.getInfor(idPost);

        model.addAttribute("baipost", baipost);
        model.addAttribute("idBaiPost", baipost.getPostId());

        return "client/PostDetail";
    }
}
