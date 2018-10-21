package com.lql.cheng.utils;

import static com.lql.cheng.utils.Constant.ground;
import static com.lql.cheng.utils.Constant.startqipan;

public class Utils {
	// 检查是否有五子连起来
	public static boolean isWin(int x, int y) {
		if (baiwin() || hewin())
			return true;
		return false;

	}
	public static boolean baiwulu(int x,int y){
		if ((ground[0][0]==1&&ground[0][1]==1&&ground[0][2]==1&&ground[1][1]==2&&ground[1][2]==2)||//123
				(ground[0][0]==1&&ground[0][1]==1&&ground[1][0]==1&&ground[0][2]==2&&ground[1][1]==2)||//124
				(ground[0][0]==1&&ground[0][1]==1&&ground[1][2]==1&&ground[0][2]==2&&ground[1][1]==2&&ground[2][2]==2)||//126
				(ground[0][0]==1&&ground[0][1]==1&&ground[2][0]==1&&ground[0][2]==2&&ground[1][1]==2&&ground[2][1]==2)||//127
				(ground[0][0]==1&&ground[1][0]==1&&ground[0][2]==1&&ground[0][1]==2&&ground[1][1]==2&&ground[1][2]==2)||//134
				(ground[0][2]==1&&ground[1][2]==1&&ground[0][0]==1&&ground[0][1]==2&&ground[1][1]==2&&ground[2][2]==2)||//136
				(ground[0][0]==1&&ground[2][0]==1&&ground[2][1]==1&&ground[0][1]==2&&ground[1][1]==2&&ground[2][2]==2)||//178
				(ground[0][1]==1&&ground[0][2]==1&&ground[1][0]==1&&ground[0][0]==2&&ground[1][1]==2&&ground[1][2]==2)||//234
				(ground[0][2]==1&&ground[1][2]==1&&ground[0][1]==1&&ground[0][0]==2&&ground[1][1]==2&&ground[2][2]==2)||//236
				(ground[0][2]==1&&ground[1][2]==1&&ground[1][0]==1&&ground[0][1]==2&&ground[1][1]==2&&ground[2][2]==2)||//346
				(ground[0][2]==1&&ground[1][2]==1&&ground[2][2]==1&&ground[0][1]==2&&ground[1][1]==2&&ground[2][1]==2)||//369
				(ground[1][0]!=1&&ground[1][1]!=1&&ground[0][1]==2&&ground[1][2]==2&&ground[2][1]==2)||//379
				(ground[1][0]==1&&ground[1][1]==1&&ground[0][1]==2&&ground[1][2]==2&&ground[2][1]==2)||//379
				(ground[1][2]==1&&ground[2][2]==1&&ground[1][0]==1&&ground[1][1]==2&&ground[0][2]==2&&ground[2][1]==2)||//469
				(ground[1][0]==1&&ground[2][1]==1&&ground[2][0]==1&&ground[2][2]==2&&ground[1][1]==2)||//478
				(ground[1][0]==1&&ground[2][0]==1&&ground[2][2]==1&&ground[1][1]==2&&ground[1][2]==2&&ground[2][1]==2)||//479
				(ground[1][0]==1&&ground[2][1]==1&&ground[2][2]==1&&ground[1][1]==2&&ground[1][2]==2&&ground[0][2]==2)||//489
				(ground[2][1]==1&&ground[1][2]==1&&ground[2][1]==1&&ground[0][2]==2&&ground[1][1]==2&&ground[2][2]==2)||//678
				(ground[2][2]==1&&ground[1][2]==1&&ground[2][0]==1&&ground[0][2]==2&&ground[1][1]==2&&ground[2][1]==2)||//679
				(ground[2][1]==1&&ground[1][2]==1&&ground[2][2]==1&&ground[0][2]==2&&ground[1][1]==2&&ground[2][0]==2)||//689
				(ground[2][0]==1&&ground[2][1]==1&&ground[2][2]==1&&ground[1][1]==2&&ground[1][2]==2))//789
		{

			return true;
		}
		return false;
	}

	public static boolean hewulu(int x,int y){
		if     ((ground[0][0]==2&&ground[0][1]==2&&ground[0][2]==2&&ground[1][1]==1&&ground[1][2]==1)||//123
				(ground[0][0]==2&&ground[0][1]==2&&ground[1][0]==2&&ground[0][2]==1&&ground[1][1]==1)||//124
				(ground[0][0]==2&&ground[0][1]==2&&ground[1][2]==2&&ground[0][2]==1&&ground[1][1]==1&&ground[2][2]==1)||//126
				(ground[0][0]==2&&ground[0][1]==2&&ground[2][0]==2&&ground[0][2]==1&&ground[1][1]==1&&ground[2][1]==1)||//127
				(ground[0][0]==2&&ground[1][0]==2&&ground[0][2]==2&&ground[0][1]==1&&ground[1][1]==1&&ground[1][2]==1)||//134
				(ground[0][2]==2&&ground[1][2]==2&&ground[0][0]==2&&ground[0][1]==1&&ground[1][1]==1&&ground[2][2]==1)||//136
				(ground[0][0]==2&&ground[2][0]==2&&ground[2][1]==2&&ground[0][1]==1&&ground[1][1]==1&&ground[2][2]==1)||//178
				(ground[0][1]==2&&ground[0][2]==2&&ground[1][0]==2&&ground[0][0]==1&&ground[1][1]==1&&ground[1][2]==1)||//234
				(ground[0][2]==2&&ground[1][2]==2&&ground[0][1]==2&&ground[0][0]==1&&ground[1][1]==1&&ground[2][2]==1)||//236
				(ground[0][2]==2&&ground[1][2]==2&&ground[1][0]==2&&ground[0][1]==1&&ground[1][1]==1&&ground[2][2]==1)||//346
				(ground[0][2]==2&&ground[1][2]==2&&ground[2][2]==2&&ground[0][1]==1&&ground[1][1]==1&&ground[2][1]==1)||//369
				(ground[1][0]!=2&&ground[1][1]!=2&&ground[0][1]==1&&ground[1][2]==1&&ground[2][1]==1)||//379
				(ground[1][0]==2&&ground[1][1]==2&&ground[0][1]==1&&ground[1][2]==1&&ground[2][1]==1)||//379
				(ground[1][2]==2&&ground[2][2]==2&&ground[1][0]==2&&ground[1][1]==1&&ground[0][2]==1&&ground[2][1]==1)||//469
				(ground[1][0]==2&&ground[2][1]==2&&ground[2][0]==2&&ground[2][2]==1&&ground[1][1]==1)||//478
				(ground[1][0]==2&&ground[2][0]==2&&ground[2][2]==2&&ground[1][1]==1&&ground[1][2]==1&&ground[2][1]==1)||//479
				(ground[1][0]==2&&ground[2][1]==2&&ground[2][2]==2&&ground[1][1]==1&&ground[1][2]==1&&ground[0][2]==1)||//489
				(ground[2][1]==2&&ground[1][2]==2&&ground[2][1]==2&&ground[0][2]==1&&ground[1][1]==1&&ground[2][2]==1)||//678
				(ground[2][2]==2&&ground[1][2]==2&&ground[2][0]==2&&ground[0][2]==1&&ground[1][1]==1&&ground[2][1]==1)||//679
				(ground[2][1]==2&&ground[1][2]==2&&ground[2][2]==2&&ground[0][2]==1&&ground[1][1]==1&&ground[2][0]==1)||//689
				(ground[2][0]==2&&ground[2][1]==2&&ground[2][2]==2&&ground[1][1]==1&&ground[1][2]==1)){//789


			return true;

		}


		return false;
	}

	public static boolean  baiwin() {
		if (ground[0][0]==2&&ground[1][0]==2&&ground[2][0]==2&&ground[0][1]==1&&ground[1][1]==1&&ground[2][1]==1)
		 {return true;}
		return false;
	}
	public static boolean  hewin() {
		if (ground[0][0]==1&&ground[1][0]==1&&ground[2][0]==1&&ground[0][1]==2&&ground[1][1]==2&&ground[2][1]==2)
	 {return true;}
		return false;
	}


	public static boolean isLegal(int x, int y) {
		return x >= 0 && x < 3 && y >= 0 && y <3;
	}
	public static void initGroup(){
		int length =  ground.length;
		for (int i = 0; i < length; i++) {
			for(int j = 0;j < length;j++){
				ground[i][j] = startqipan[i][j];}
		}
		
	}
}
