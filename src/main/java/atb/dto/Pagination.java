package atb.dto;

public class Pagination {

    private int total,
     per_page,
     current_page,
     last_page,
     from,
     to;

    public Pagination(int total, int per_page, int current_page) {
        this.total = total;
        this.per_page = per_page;
        this.current_page = current_page;
        this.last_page = (int)Math.ceil(total / (double)per_page);
        this.from = current_page==1?1:current_page*per_page-per_page+1;
        int itemShowed = from + per_page-1;
        this.to = itemShowed<=total?itemShowed:total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
