package com.studywithme.controller.web;

import com.studywithme.model.User;
import com.studywithme.service.IAddressTypeService;
import com.studywithme.service.IAppointmentService;
import com.studywithme.service.IDistrictService;
import com.studywithme.service.IFriendshipService;
import com.studywithme.service.impl.AddressTypeService;
import com.studywithme.service.impl.AppointmentService;
import com.studywithme.service.impl.DistrictService;
import com.studywithme.service.impl.FriendshipService;
import com.studywithme.sort.Sorter;
import com.studywithme.util.FormUtil;
import com.studywithme.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createAppointment")
public class CreateAppointment extends HttpServlet {
//    private IDistrictService districtService;
//    private IAppointmentService appointmentService;
//    private IAddressTypeService addressTypeService;
//    private IFriendshipService friendshipService;
    public CreateAppointment() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) SessionUtil.getInstance().getValue(request, "user");

        String listFriendStr = request.getParameter("listFriend");

        Sorter sorter = new Sorter();
        sorter = FormUtil.toModel(Sorter.class, request);
        request.setAttribute("dateMeeting", sorter.getDateMeeting());
        request.setAttribute("districts", DistrictService.getInstance().findAll());
        request.setAttribute("addressTypes", AddressTypeService.getInstance().findAll());
        request.setAttribute("appointmentJoined", AppointmentService.getInstance().findByParticipantCurrent(user));
        request.setAttribute("listFriend", FriendshipService.getInstance().listFriend(listFriendStr,user));
        request.setAttribute("requestFriend", FriendshipService.getInstance().getRequest(user));
        request.setAttribute("totalAppointment", AppointmentService.getInstance().totalItemCurrent(user));
        request.setAttribute("appointments", AppointmentService.getInstance().appointmentCurrent(user));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/CreateAppointment.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action.equals("create")){
            User user = (User) SessionUtil.getInstance().getValue(request, "user");
            String dateMeetingStr = request.getParameter("dateMeeting");
            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");
            String idWard = request.getParameter("ward");
            String address = request.getParameter("address");
            String max = request.getParameter("max");
            String idAddressType = request.getParameter("addressType");
            AppointmentService.getInstance().createAppointment(dateMeetingStr,startTimeStr,endTimeStr,max,address,idAddressType,idWard,user);
        } else if (action.equals("delete")) {
            String idAppointment = request.getParameter("idAppointment");
            AppointmentService.getInstance().delete(idAppointment);
        }
        doGet(request,response);
    }
}
