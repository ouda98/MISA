package Core;
public class RegisterFile {
	
	// Powers of two Registers
	static String $c0 = "";
	static String $c1 = "";
	static String $c2 = "";
	static String $c3 = "";
	static String $c4 = "";
	static String $c5 = "";
	static String $c6 = "";
	static String $c7 = "";
	static String $c8 = "";
	static String $c9 = "";
	static String $c10 = "";
	static String $c11 = "";
	static String $c12 = "";
	static String $c13 = "";
	static String $c14 = "";
	static String $c15 = "";
	static String $c16 = "";

	// Assembler Register
	static String $at = "";

	// Return value Registers
	static String $v0 = "";
	static String $v1 = "";

	// Arguments Registers
	static String $a0 = "";
	static String $a1 = "";
	static String $a2 = "";
	static String $a3 = "";
	static String $a4 = "";
	static String $a5 = "";

	// Saved Registers
	static String $s0 = "";
	static String $s1 = "";
	static String $s2 = "";
	static String $s3 = "";
	static String $s4 = "";
	static String $s5 = "";
	static String $s6 = "";
	static String $s7 = "";
	static String $s8 = "";
	static String $s9 = "";
	static String $s10 = "";
	static String $s11 = "";
	static String $s12 = "";
	static String $s13 = "";

	// Temporary Registers
	static String $t0 = "";
	static String $t1 = "";
	static String $t2 = "";
	static String $t3 = "";
	static String $t4 = "";
	static String $t5 = "";
	static String $t6 = "";
	static String $t7 = "";
	static String $t8 = "";
	static String $t9 = "";
	static String $t10 = "";
	static String $t11 = "";
	static String $t12 = "";
	static String $t13 = "";
	static String $t14 = "";
	static String $t15 = "";
	static String $t16 = "";
	static String $t17 = "";

	// Kernel Registers
	static String $k0 = "";
	static String $k1 = "";

	// Global Pointer Register
	static String $gp = "";

	// Stack Pointer Register
	static String $sp = "";

	// Frame Pointer Register
	static String $fp = "";

	// Return address Register
	static String $ra = "";

	public static int convertRegisterToBinary(String select) {

		switch (select) {
		case "$c0":
			return 0;
		case "$c1":
			return 1;
		case "$c2":
			return 2;
		case "$c3":
			return 3;
		case "$c4":
			return 4;
		case "$c5":
			return 5;
		case "$c6":
			return 6;
		case "$c7":
			return 7;
		case "$c8":
			return 8;
		case "$c9":
			return 9;
		case "$c10":
			return 10;
		case "$c11":
			return 11;
		case "$c12":
			return 12;
		case "$c13":
			return 13;
		case "$c14":
			return 14;
		case "$c15":
			return 15;
		case "$c16":
			return 16;
		case "$at":
			return 17;
		case "$v0":
			return 18;
		case "$v1":
			return 19;
		case "$a0":
			return 20;
		case "$a1":
			return 21;
		case "$a2":
			return 22;
		case "$a3":
			return 23;
		case "$a4":
			return 24;
		case "$a5":
			return 25;
		case "$s0":
			return 26;
		case "$s1":
			return 27;
		case "$s2":
			return 28;
		case "$s3":
			return 29;
		case "$s4":
			return 30;
		case "$s5":
			return 31;
		case "$s6":
			return 32;
		case "$s7":
			return 33;
		case "$s8":
			return 34;
		case "$s9":
			return 35;
		case "$s10":
			return 36;
		case "$s11":
			return 37;
		case "$s12":
			return 38;
		case "$s13":
			return 39;
		case "$t0":
			return 40;
		case "$t1":
			return 41;
		case "$t2":
			return 42;
		case "$t3":
			return 43;
		case "$t4":
			return 44;
		case "$t5":
			return 45;
		case "$t6":
			return 46;
		case "$t7":
			return 47;
		case "$t8":
			return 48;
		case "$t9":
			return 49;
		case "$t10":
			return 50;
		case "$t11":
			return 51;
		case "$t12":
			return 52;
		case "$t13":
			return 53;
		case "$t14":
			return 54;
		case "$t15":
			return 55;
		case "$t16":
			return 56;
		case "$t17":
			return 57;
		case "$k0":
			return 58;
		case "$k1":
			return 59;
		case "$gp":
			return 60;
		case "$sp":
			return 61;
		case "$fp":
			return 62;
		case "$ra":
			return 63;
		default:return -1;
		}
	}
	public static String readRegData(int sel) {

		switch (sel) {
		case 0:
			return "$c0";
		case 1:
			return "$c1";
		case 2:
			return "$c2";
		case 3:
			return "$c3";
		case 4:
			return "$c4";
		case 5:
			return "$c5";
		case 6:
			return "$c6";
		case 7:
			return "$c7";
		case 8:
			return "$c8";
		case 9:
			return "$c9";
		case 10:
			return "$c10";
		case 11:
			return "$c11";
		case 12:
			return "$c12";
		case 13:
			return "$c13";
		case 14:
			return "$c14";
		case 15:
			return "$c15";
		case 16:
			return "$c16";
		case 17:
			return "$at";
		case 18:
			return "$v0";
		case 19:
			return "$v1";
		case 20:
			return "$a0";
		case 21:
			return "$a1";
		case 22:
			return "$a2";
		case 23:
			return "$a3";
		case 24:
			return "$a4";
		case 25:
			return "$a5";
		case 26:
			return "$s0";
		case 27:
			return "$s1";
		case 28:
			return "$s2";
		case 29:
			return "$s3";
		case 30:
			return "$s4";
		case 31:
			return "$s5";
		case 32:
			return "$s6";
		case 33:
			return "$s7";
		case 34:
			return "$s8";
		case 35:
			return "$s9";
		case 36:
			return "$s10";
		case 37:
			return "$s11";
		case 38:
			return "$s12";
		case 39:
			return "$s13";
		case 40:
			return "$t0";
		case 41:
			return "$t1";
		case 42:
			return "$t2";
		case 43:
			return "$t3";
		case 44:
			return "$t4";
		case 45:
			return "$t5";
		case 46:
			return "$t6";
		case 47:
			return "$t7";
		case 48:
			return "$t8";
		case 49:
			return "$t9";
		case 50:
			return "$t10";
		case 51:
			return "$t11";
		case 52:
			return "$t12";
		case 53:
			return "$t13";
		case 54:
			return "$t14";
		case 55:
			return "$t15";
		case 56:
			return "$t16";
		case 57:
			return "$t17";
		case 58:
			return "$k0";
		case 59:
			return "$k1";
		case 60:
			return "$gp";
		case 61:
			return "$sp";
		case 62:
			return "$fp";
		case 63:
			return "$ra";
		default:return "";
		}
	}

	
	public static void wirteRegData(String select,String data) {
		int sel = Integer.valueOf(select, 2);
		switch (sel) {
		case 0:
			 $c0=data;
		case 1:
			 $c1=data;
		case 2: 
			 $c2=data;
		case 3:
			 $c3=data;
		case 4:
			 $c4=data;
		case 5:
			 $c5=data;
		case 6:
			 $c6=data;
		case 7:
			 $c7=data;
		case 8:
			 $c8=data;
		case 9:
			 $c9=data;
		case 10:
			 $c10=data;
		case 11:
			 $c11=data;
		case 12:
			 $c12=data;
		case 13:
			 $c13=data;
		case 14:
			 $c14=data;
		case 15:
			 $c15=data;
		case 16:
			 $c16=data;
		case 17:
			 $at=data;
		case 18:
			 $v0=data;
		case 19:
			 $v1=data;
		case 20:
			 $a0=data;
		case 21:
			 $a1=data;
		case 22:
			 $a2=data;
		case 23:
			 $a3=data;
		case 24:
			 $a4=data;
		case 25:
			 $a5=data;
		case 26:
			 $s0=data;
		case 27:
			 $s1=data;
		case 28:
			 $s2=data;
		case 29:
			 $s3=data;
		case 30:
			 $s4=data;
		case 31:
			 $s5=data;
		case 32:
			 $s6=data;
		case 33:
			 $s7=data;
		case 34:
			 $s8=data;
		case 35:
			 $s9=data;
		case 36:
			 $s10=data;
		case 37:
			 $s11=data;
		case 38:
			 $s12=data;
		case 39:
			 $s13=data;
		case 40:
			 $t0=data;
		case 41:
			 $t1=data;
		case 42:
			 $t2=data;
		case 43:
			 $t3=data;
		case 44:
			 $t4=data;
		case 45:
			 $t5=data;
		case 46:
			 $t6=data;
		case 47:
			 $t7=data;
		case 48:
			 $t8=data;
		case 49:
			 $t9=data;
		case 50:
			 $t10=data;
		case 51:
			 $t11=data;
		case 52:
			 $t12=data;
		case 53:
			 $t13=data;
		case 54:
			 $t14=data;
		case 55:
			 $t15=data;
		case 56:
			 $t16=data;
		case 57:
			 $t17=data;
		case 58:
			 $k0=data;
		case 59:
			 $k1=data;
		case 60:
			 $gp=data;
		case 61:
			 $sp=data;
		case 62:
			 $fp=data;
		case 63:
			 $ra=data;
		default:return;
		}
	}

}


