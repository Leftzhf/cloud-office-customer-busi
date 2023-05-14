import com.cloud.office.customer.busi.utils.DateToolUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/13 23:16
 */


public class TestGetWeekStringByDate {

    @Test
    public void testGetWeekStringByDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2022-12-25");
        Date date2 = dateFormat.parse("2023-02-03");
        Date date3 = dateFormat.parse("2023-05-16");
//        Date date4 = new Date("2023-01-30T16:00:00.000Z");

//        assertEquals("周日", DateToolUtil.getWeekStringByDate());
        assertEquals("星期五", DateToolUtil.getWeekStringByDate(date2));
//        assertEquals("周二", DateToolUtil.getWeekStringByDate(date3));
//        assertEquals("周一", DateToolUtil.getWeekStringByDate(date4));
    }
}
