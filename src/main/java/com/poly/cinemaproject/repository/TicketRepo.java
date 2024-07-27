package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.Ticket;
import com.poly.cinemaproject.model.report.AllTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    @Query("SELECT New AllTickets(tk.mave, u.id, u.fullname, f.tenphim,f.hinhanh,tk.tongsoghe, tk.tongtien, tk.trangthai, tk.ngaydatve)"
            +" FROM Ticket tk JOIN tk.user u JOIN tk.ls_Detail tkdt " +
            " JOIN tkdt.film f " +
            " GROUP BY  tk.mave, u.id, u.fullname, f.tenphim,f.hinhanh,tk.tongsoghe, tk.tongtien, tk.trangthai, tk.ngaydatve")
    List<AllTickets> getAllTicket();
}
