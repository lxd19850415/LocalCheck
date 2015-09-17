package com.sandy.localcheck;


import android.content.Context;

public class Utils {
	static Context context;
//	public static String read(String fileName) {
//        try {
//            FileInputStream inStream = context.openFileInput(fileName);
//            byte[] buffer = new byte[1024];
//            int hasRead = 0;
//            StringBuilder sb = new StringBuilder();
//            while ((hasRead = inStream.read(buffer)) != -1) {
//                sb.append(new String(buffer, 0, hasRead));
//            }
//
//            inStream.close();
//            return sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } 
//        return null;
//    }
//    
//    public static void write(String fileName,String msg){
//        if(msg == null) return;
//        try {
//            FileOutputStream fos = context.openFileOutput(fileName,
//            		Context.MODE_APPEND);
//            fos.write(msg.getBytes());
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public static double len(double a,double b,double c,double d){
    	double aa=Math.abs(a-c);
    	double bb=Math.abs(b-d);
    	
    	double ret=Math.sqrt(aa*aa+bb*bb);
    	return ret;
    	
    }
    
    
}
