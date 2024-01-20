package com.studywithme.controller.web;

import com.studywithme.model.User;
import com.studywithme.service.impl.FriendshipService;
import com.studywithme.service.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/replyFriend")
public class Reply extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idUser = request.getParameter("idRequester");
        User user = UserService.getInstance().findById(Integer.parseInt(idUser));
        if (action.equals("reply")) {
            String reply = request.getParameter("reply");
            String idFriendship = request.getParameter("idFriendship");
            FriendshipService.getInstance().reply(idFriendship, reply);
        }
        response.sendRedirect("/PBL3/profile?id=" +user.getId());
    }
}
