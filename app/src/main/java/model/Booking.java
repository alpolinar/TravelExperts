package model;

public class Booking {
    private String bookindDate;
    private String bookingNo;
    private String packageName;

    public Booking() {}

    public Booking( String packageName,String bookindDate, String bookingNo) {
        this.bookindDate = bookindDate;
        this.bookingNo = bookingNo;
        this.packageName = packageName;
    }

    public String getBookindDate() {
        return bookindDate;
    }

    public void setBookindDate(String bookindDate) {
        this.bookindDate = bookindDate;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
