package soma.iot.sympathyhome.util;

/**
 * Created by seojunkyo on 15. 8. 17..
 */
public class ReminderDate {

    public String date;
    public String schedule;

    public ReminderDate(String date, String schedule){
        this.date = date;
        this.schedule = schedule;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDate() {
        return date;
    }

    public String getSchedule() { return schedule; }
}
