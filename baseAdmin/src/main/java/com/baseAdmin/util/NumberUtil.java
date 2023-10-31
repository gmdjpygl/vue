package com.baseAdmin.util;

import java.math.BigDecimal;

public class NumberUtil {

	public static Integer parseInt(Object obj) {
		Integer i = null;
		if (obj != null) {
			if (obj instanceof Double) {
				i = ((Double) obj).intValue();
			} else if (obj instanceof String) {
				i = Integer.parseInt((String) obj);
			}else {
				i = Integer.parseInt(obj+"");
			}
		}
		return i;
	}

	public static Long parseLong(Object s) {
		Long l = null;
		try {
			if(s==null) {
				return l;
			}
			if(s instanceof String) {
				l = Long.parseLong((String)s);
			}else if(s instanceof Double) {
				l = ((Double)s).longValue();
			}else {
				l = Long.parseLong(s+"");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return l; 
	}
	public static Double parseDouble(String s) {
		Double d = null;
		try {
			if(!CommonUtil.isNull(s)) {
				d = Double.parseDouble(s);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static Double parseDouble(Object obj) {
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				return parseDouble((BigDecimal) obj);
			} else {
				return parseDouble(obj.toString());
			}
		}
		return null;

	}
	public static Double parseDouble(BigDecimal obj) {
		return obj!=null?obj.doubleValue():null;
	}

	/** 
	* double 相加 
	* @param d1 
	* @param d2 
	* @return 
	*/
	public static double sum(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}

	/** 
	 * double 相减 
	 * @param d1 
	 * @param d2 
	 * @return 
	 */
	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}

	/** 
	 * double 乘法 
	 * @param d1 
	 * @param d2 
	 * @return 
	 */
	public static double mul(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}

	/** 
	 * double 除法 
	 * @param d1 
	 * @param d2 
	 * @param scale 四舍五入 小数点位数 
	 * @return 
	 */
	public static double div(double d1, double d2, int scale) {
		// 当然在此之前，你要判断分母是否为0，
		// 为0你可以根据实际需求做相应的处理

		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 格式化小数
	 * @param d  
	 * @param decimals  小数位
	 * @return
	 */
	public static Double format(Double d, int decimals) {
		if(d!=null) {
			BigDecimal b = new BigDecimal(d);
			double f1 = b.setScale(decimals, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1;
		}else {
			return null;
		}
		
	}
	public static String  formatStr(double d, String pattern) {
		java.text.DecimalFormat   df=new   java.text.DecimalFormat(pattern);
		return df.format(d);
	}
	public static double formatNoRound(double d) {
		int tmp=(int)(d*100);//将浮点型数*100，如num*100，再强制转化为整型数，得1256
		d=tmp/100.0;//100.0是关键，将整型数转化成浮点型
		return d;
	}
}