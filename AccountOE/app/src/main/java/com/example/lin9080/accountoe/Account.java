package com.example.lin9080.accountoe;

import org.litepal.crud.DataSupport;

public class Account extends DataSupport {
    private int number;
    private String useTime;
    private String whatDo;
    private int purpose;//1:饮食2:服饰美容3:生活日用4:住房缴费5:交通出行6:通讯物流7:文教娱乐8:运动健康0:其他消费
    private String diary;

    public Account() {

    }

    public Account(int number, String useTime, String whatDo, int purpose) {
        this.number = number;
        this.useTime = useTime;
        this.whatDo = whatDo;
        this.purpose = purpose;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getWhatDo() {
        return whatDo;
    }

    public void setWhatDo(String whatDo) {
        this.whatDo = whatDo;
    }

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }
}