package com.huiminsheng.app.bean;

public class BeanMonth {

    private boolean isSelect = false;

    private int month;

    public BeanMonth( int month) {
        this.month = month;
    }



    public int getMonth() {
        return month;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


}
