package org.techtown.numbertouch;

public class Rank {
    String rank;
    String time;
    String type;
    String date;

    public Rank(String rank, String time, String type, String date) {
        this.rank = rank;
        this.time = time;
        this.type = type;
        this.date = date;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
