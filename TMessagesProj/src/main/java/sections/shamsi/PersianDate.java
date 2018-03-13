package sections.shamsi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersianDate {

    public String todayShamsi() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String curentDateandTime = sdf.format(new Date());
        String year = curentDateandTime.substring(0, 4);
        String month = curentDateandTime.substring(4, 6);
        String day = curentDateandTime.substring(6, 8);
        int Y = Integer.valueOf(year);
        int M = Integer.valueOf(month);
        int D = Integer.valueOf(day);
        return Shamsi(Y, M, D);
    }



    public String todayMiladi() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curentDateandTime = sdf.format(new Date());
        return curentDateandTime;

    }



    public static String Shamsi(int Y, int M, int D)
    {
        if (Y == 0)
            Y = 2000;
        if (Y < 100)
            Y = Y + 1900;
        if (Y == 2000)
        {
            if (M > 2)
            {
                SimpleDateFormat temp = new SimpleDateFormat("yyyyMMdd");
                String curentDateandTime = temp.format(new Date());
                String year = curentDateandTime.substring(0, 4);
                String month = curentDateandTime.substring(4, 6);
                String day = curentDateandTime.substring(6, 8);
                Y = Integer.valueOf(year);
                M = Integer.valueOf(month);
                D = Integer.valueOf(day);
            }
        }
        if (M < 3 || (M == 3 && D < 21))
            Y = Y - 622;
        else
            Y = Y - 621;
        switch (M)
        {
            case 1:
                if (D < 21)
                {
                    M = 10;
                    D = D + 10;
                }
                else
                {
                    M = 11;
                    D = D - 20;
                }
                break;
            case 2:
                if (D < 20)
                {
                    M = 11;
                    D = D + 11;
                }
                else
                {
                    M = 12;
                    D = D - 19;
                }
                break;
            case 3:
                if (D < 21)
                {
                    M = 12;
                    D = D + 9;
                }
                else
                {
                    M = 1;
                    D = D - 20;
                }
                break;
            case 4:
                if (D < 21)
                {
                    M = 1;
                    D = D + 11;
                }
                else
                {
                    M = 2;
                    D = D - 20;
                }
                break;
            case 5:
                if (D < 22)
                {
                    M = M - 3;
                    D = D + 10;
                }
                else
                {
                    M = M - 2;
                    D = D - 21;
                }
                break;
            case 6:
                if (D < 22)
                {
                    M = M - 3;
                    D = D + 10;
                }
                else
                {
                    M = M - 2;
                    D = D - 21;
                }
                break;
            case 7:
                if (D < 23)
                {
                    M = M - 3;
                    D = D + 9;
                }
                else
                {
                    M = M - 2;
                    D = D - 22;
                }
                break;
            case 8:
                if (D < 23)
                {
                    M = M - 3;
                    D = D + 9;
                }
                else
                {
                    M = M - 2;
                    D = D - 22;
                }
                break;
            case 9:
                if (D < 23)
                {
                    M = M - 3;
                    D = D + 9;
                }
                else
                {
                    M = M - 2;
                    D = D - 22;
                }
                break;
            case 10:
                if (D < 23)
                {
                    M = 7;
                    D = D + 8;
                }
                else
                {
                    M = 8;
                    D = D - 22;
                }
                break;
            case 11:
                if (D < 22)
                {
                    M = M - 3;
                    D = D + 9;
                }
                else
                {
                    M = M - 2;
                    D = D - 21;
                }
                break;
            case 12:
                if (D < 22)
                {
                    M = M - 3;
                    D = D + 9;
                }
                else
                {
                    M = M - 2;
                    D = D - 21;
                }
                break;
        }
        String month = "00";
        String day = "00";
        D = Integer.valueOf(D) + 1;
        if (M < 10)
        {
            month = "0" + M;
        }
        else
        {
            month = String.valueOf(M);
        }
        if (D < 10)
        {
            day = "0" + D;
        }
        else
        {
            day = String.valueOf(D);
        }
        return String.valueOf(Y) + "/" + month + "/" + day;
    }

}
