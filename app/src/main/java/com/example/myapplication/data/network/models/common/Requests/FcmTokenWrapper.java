package com.example.myapplication.data.network.models.common.Requests;

public class FcmTokenWrapper {
    private String reg_id;

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public FcmTokenWrapper(String reg_id) {
        this.reg_id = reg_id;
    }
}
