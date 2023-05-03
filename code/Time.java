public class Time {
    static long startTime;

    static long endTime;

    public static void getstart(long time) {
        startTime = time;
    }

    public static void getend(long time) {
        endTime = time;
    }
    public static String getDur()  {

        long diff=(endTime-startTime)/1000;//获取两个时间相差的分钟
        System.out.println("两个时间差为：" + diff + "秒");

        long time =System.currentTimeMillis();

        startTime=time;
        return String.valueOf(diff);
    };
}