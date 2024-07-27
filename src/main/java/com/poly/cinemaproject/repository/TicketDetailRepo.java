package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.dto.TicketDetailDTO;
import com.poly.cinemaproject.model.entity.TicketDetail;
import com.poly.cinemaproject.model.report.TicketDetailByTicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketDetailRepo extends JpaRepository<TicketDetail, Integer> {
    @Query("SELECT new TicketDetailByTicketId(tkdt.mavechitiet,tk.mave,tkdt.soluong,tkdt.dongia, tkdt.tongtien ,st.ngaychieu,st.giochieu,s.loaighe,st.room.tenphongchieu)"
            + " FROM Ticket tk JOIN tk.ls_Detail tkdt JOIN tkdt.seat s JOIN tk.showTime st "
            + " WHERE tk.mave = ?1"
            + " GROUP BY tkdt.mavechitiet,tk.mave,tkdt.soluong,tkdt.dongia, tkdt.tongtien ,st.ngaychieu,st.giochieu,s.loaighe,st.room.tenphongchieu")
    List<TicketDetailByTicketId> getTicketDetailByTicketId(String mave);

}
