package org.techtown.numbertouch;

public class Rank {
    int rank;
    String time;
    String type;
    String date;

    public Rank(int rank, String time, String type, String date) {
        this.rank = rank;
        this.time = time;
        this.type = type;
        this.date = date;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
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
