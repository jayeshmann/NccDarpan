package com.tsa.nccapp.utils;

import com.tsa.nccapp.R;
import com.tsa.nccapp.models.CadetDetailsModels;
import com.tsa.nccapp.models.LoginModel;
import com.tsa.nccapp.models.OtherExamResultModel;
import com.tsa.nccapp.models.PasswordModel;
import com.tsa.nccapp.models.ReportModel;
import com.tsa.nccapp.models.UserModel;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Akhil Tripathi on 28-12-2017.
 */

public class GLOBAL {
    public final static String baseURL="http://internationalskills.co.in/nccdarpan/API/";

    public static UserModel globalUserModel=null;
    public static LoginModel globalLoginModel=null;
    public static PasswordModel globalPasswordModel=null;


    public static String url="";
    public static String cert="A";

    public static int xD=0;
    public static int yD=0;

    public static final int GMAILLOGIN=2;
    public static final int GUESTLOGIN=1;
    public static final int USERLOGIN=0;

    public static String NCC;
    public static  final int OTHER = 2;
    public static String user_type ="";


    public static final int CADET=2;
    public static final int ANO=1;
    public static final int STAFF=3;

    public static int loginType=USERLOGIN;
    public static String password;
    public static String username;

    public static int textColor= R.color.black;
    public static int BGColor= R.color.light_green;
    public static int textSize= 14;

    public static int scrollAmount=0;
    public static ArrayList<ReportModel> reportModels;

    public static Stack<Integer> lastVisitedIndex=new Stack<>();

    public static String photoURL="https://icons8.com/A.png";

    public static ArrayList<OtherExamResultModel> otherExamResultModels=new ArrayList<>();
    public static CadetDetailsModels cadetFormModel;
}
