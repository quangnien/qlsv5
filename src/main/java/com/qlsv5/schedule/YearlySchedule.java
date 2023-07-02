package com.qlsv5.schedule;

import com.qlsv5.entity.TuanEntity;
import com.qlsv5.repository.TuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

/**
 * @author Admin
 * @created 5/1/2023 3:26 PM
 * @project qlsv5
 */
@Component
@EnableScheduling
public class YearlySchedule {

    @Autowired
    private TuanRepository tuanRepository;

    // Hàm này sẽ được gọi mỗi năm một lần để tạo record tuần và lưu vào MongoDB
//    @Scheduled(cron = "0 * * * * ?")
//    @Scheduled(cron = "0 */3 * * * ?")
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void createTuanForYear() {

        int year = LocalDate.now().getYear();

        LocalDate startDate = LocalDate.of(year, Month.JANUARY, 1);

        while (startDate.getYear() == year) {

            TuanEntity tuan = new TuanEntity();
            tuan.setTuan(startDate.get(WeekFields.ISO.weekOfYear())); // Lấy số tuần bằng WeekFields.ISO.weekOfYear()
            tuan.setNgaybd(startDate);
            tuan.setNgaykt(startDate.with(DayOfWeek.SUNDAY).plusDays(6));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            tuan.setDescription("Tuần " + tuan.getTuan() + " ["
                                + tuan.getNgaybd().format(formatter) + " -> "
                                + tuan.getNgaykt().format(formatter) + "] ");

            tuan.setNam(startDate.getYear());

            tuanRepository.save(tuan);
            startDate = startDate.plusWeeks(1);
        }
    }
}
