package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.ShowTime;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilm;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilmAndDay;
import com.poly.cinemaproject.model.report.FindShowTimeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ShowTimeRepo extends JpaRepository<ShowTime, Integer> {
    // Tìm lịch chiếu phim theo mã phim
    @Query("SELECT new FindShowTimeByIdFilm(f.maphim,f.tenphim, r.tenphongchieu,st.ngaychieu,st.giochieu,count(s.maghe))"
            + " FROM ShowTime st JOIN st.room r JOIN st.film f JOIN r.seats s  "
            + " WHERE f.maphim = ?1"
            + " GROUP BY f.maphim, f.tenphim, r.tenphongchieu, st.ngaychieu, st.giochieu")
    List<FindShowTimeByIdFilm> findShowTimeByIdFilm(Integer maphim);

    // tìm lịch chiếu theo mã phim và ngày chiếu
    @Query("SELECT new FindShowTimeByIdFilmAndDay(f.maphim,f.tenphim, r.tenphongchieu, st.giochieu,st.ngaychieu)"
            + " FROM ShowTime st JOIN st.room r JOIN st.film f "
            + " WHERE f.maphim=?1 AND st.ngaychieu=?2"
            + " GROUP BY f.maphim, f.tenphim, r.tenphongchieu, st.giochieu,st.ngaychieu ")
    List<FindShowTimeByIdFilmAndDay> findShowTimeByIdFilmAndDay(Integer maphim, LocalDate ngaychieu);

    // tìm lịch chiếu cụ thể theo mã phim , ngày chiếu, giờ chiếu
    @Query("SELECT new FindShowTimeResult(f.maphim,f.tenphim, r.tenphongchieu, st.ngaychieu, st.giochieu, count(s.maghe) , r.maphongchieu, st.malichchieu)"
            +" FROM ShowTime st JOIN st.room r JOIN st.film f JOIN r.seats s"
            +" WHERE f.maphim=?1 AND st.ngaychieu= ?2 AND st.giochieu=?3"
            +" GROUP BY f.maphim, f.tenphim, r.tenphongchieu, st.ngaychieu, st.giochieu, r.maphongchieu,st.malichchieu")
    List<FindShowTimeResult> findSHowTimeResult(Integer maphim, LocalDate ngaychieu, String giochieu);
}
