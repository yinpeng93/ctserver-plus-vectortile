package com.kedacom.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/14 0014 16:39
 * @Description: 密码生成器
 */
public class PasswordGeneratorUtil {

    private static Random rand;
    static{
        rand=new Random();
    }

    /**
     * 生成密码
     * <br>生成规则：
     * 大写字母+小写字母+数字
     * @param wordNum 要生成的密码长度是多少
     * @return
     */
    public static String generatePassword(Integer wordNum){
        int total=wordNum;//密码总位数
        StringBuffer password=new StringBuffer();
        int upperNum=getRadomInt(1,total-2);//大写字母位数，保留至少两位，用来放小写和数字
        int lowerNum=getRadomInt(1, total-upperNum-1);//小写字母位数，为总数减去大写字母占用的数量，再为数字区域保留至少1
        int nnum=total-upperNum-lowerNum;//最后剩余数字的位数，为总数减去大写和小写字母位数之后剩余的位数
        //随机获取到每个类型的位置index
        Map<Integer,String> indexMap=new HashMap<Integer,String>();
        while(indexMap.size()<upperNum){
            //确定大写字母的索引号
            int rint=getRadomInt(0, total-1);
            if(indexMap.get(rint)==null){
                indexMap.put(rint, "upper");
            }
        }
        while(indexMap.size()<upperNum+lowerNum){
            //确定小写字母的索引号
            int rint=getRadomInt(0, total-1);
            if(indexMap.get(rint)==null){
                indexMap.put(rint, "lower");
            }
        }
        while(indexMap.size()<total){
            //确定数字的索引号
            int rint=getRadomInt(0, total-1);
            if(indexMap.get(rint)==null){
                indexMap.put(rint, "nnum");
            }
        }
        //组装密码
        for(int i=0;i<total;i++){
            if("upper".equals(indexMap.get(i))){
                password.append(getUpper());
            }else if("lower".equals(indexMap.get(i))){
                password.append(getLetter());
            }else{
                password.append(getNum());
            }
        }
        return password.toString();
    }

    /**
     * 随机获取一个小写字母
     * @param
     */
    public static char getLetter(){
        char c=(char)getRadomInt(97, 122);
        return c;
    }

    /**
     * 随机获取一个大写字母
     * @param
     */
    public static char getUpper(){
        char c=(char)getRadomInt(65, 90);
        return c;
    }

    /**
     * 随机获取一个0-9的数字
     * @return
     */
    public static int getNum() {
        return getRadomInt(0, 9);
    }

    /**
     * 获取一个范围内的随机数字
     * @return
     */
    public static int getRadomInt(int min,int max){
        return rand.nextInt(max-min+1)+min;
    }

    public static void main(String[] args) {
        String password = generatePassword(20);
        System.out.println(password);
    }
}
