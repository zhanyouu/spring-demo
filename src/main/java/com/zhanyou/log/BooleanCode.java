package com.zhanyou.log;


public enum BooleanCode {

    TRUE("Y", true), FALSE("N", false);

    public final String code;
    public final boolean value;

     BooleanCode(String code, boolean value) {
        this.code = code;
        this.value = value;
    }


    public static BooleanCode getByCode(String code){

        for(BooleanCode e : values()){
            if(e.code.equals(code)){
                return e;
            }
        }

        return null;

    }

    public static BooleanCode getByValue(boolean value){
        return value ? TRUE : FALSE;
    }


}
