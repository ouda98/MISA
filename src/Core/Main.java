package Core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
	 String PC;
	 int instrLimit=7;
	 String[] mem = new String[(int) Math.pow(2, 16)];
	 String[] registers = new String[64];
	 boolean[] regWrite = new boolean[64];
	 pipeline1 F_D = new pipeline1();
	 pipeline2 D_E = new pipeline2();
	 pipeline3 E_M = new pipeline3();
	 pipeline4 M_W = new pipeline4();
	 ArrayList<instruction> instructions=new ArrayList<>();
	 ArrayList<instruction> tmp=new ArrayList<>();
	public Main() {
		// TODO Auto-generated constructor stub
		initRegesters();
	}
	public  void initRegesters() {
		PC="0000000000000000";
		Arrays.fill(regWrite, true);
		for (int i = 0; i < 64; i++) {
			if(i<16) {
				regWrite[i]=false;
				String tmp = Integer.toBinaryString(1<<i);
				System.err.println(tmp+" "+i);
				int offset = 16-tmp.length();
				while(offset-->0) {
					tmp="0"+tmp;
				}
				System.err.println(tmp+" "+i);
				registers[i] = tmp;			
			}
			else {
				registers[i]="0000000000000000";
			}
		}
		regWrite[17]=false;
		regWrite[58]=false;
		regWrite[59]=false;
		for(int i=0;i<mem.length;i++) {
			mem[i]="0000000000000000";
		}
	}

	public  String Adder(String inp1, String inp2) {
		int tmp1 = Integer.valueOf(inp1, 2);
		int tmp2 = Integer.valueOf(inp2, 2);
		String s=Integer.toBinaryString(tmp1 + tmp2);
		while(s.length()<16) {
			s="0"+s;
		}
		return s;
	}

	public  String And(String inp1, String inp2) {
		if (inp1.equals("0") || inp2.equals("0"))
			return "0";
		return "1";
	}

	public  String Mux(String inp1, String inp2, String sel) {
		if (sel.equals("0"))
			return inp1;
		return inp2;
	}

	public  String[] InstrMem(String pc) {
		String[] result = new String[5];
		int pos = Integer.valueOf(pc, 2);
		String s = mem[pos];

// 0   1  2  3  4  5  6 7 8 9 10 11 12 13 14 15 my string
//-ind-op code -    rs       -       rt        -
// 15  14 13 12 11 10 9 8 7 6 5  4  3  2  1  0  instruction index

		result[0] = s.charAt(0) + ""; // indirect bit
		result[1] = s.substring(1, 4);// opcode
		result[2] = s.substring(4, 10);// rs
		result[3] = s.substring(10);// rt
		result[4] = s.substring(4);
		return result;
	}
	
	public  String getRegData(String sel) {
		int ind = Integer.valueOf(sel, 2);
		return registers[ind];
	}

	public  void setWriteReg(String sel) {
		int ind = Integer.valueOf(sel, 2);
		regWrite[ind] = true;
	}

	public  void resetWriteReg() {
		Arrays.fill(regWrite, false);
	}

	public  void writeInReg() {

	}

	public  String[] RegisterFileRead(String readReg1, String readReg2) {
		String[] result = new String[2];
		result[0] = getRegData(readReg1);
		result[1] = getRegData(readReg2);
		return result;
	}
	
	public  void RegisterFileWrite(String writeReg, String writeData,String RegWriteControl) {
		if (RegWriteControl.equals("1")) {
			System.out.println("IND "+writeReg);
			int ind = Integer.valueOf(writeReg, 2);
//			if(regWrite[ind])  to give access on private registers
			registers[ind] = writeData;
		}
	}

	public  String MemoryData(String Address, String writeData, String MemWrite, String MemRead) {
		String result = "0000000000000000";
		int ind = Integer.valueOf(Address, 2);
		if (MemWrite.equals("1")) {
			mem[ind] = writeData;
		} else if (MemRead.equals("1")) {
			result = mem[ind];
		}
		return result;
	}

	private  String signExtend(String s) {
		return "0000" + s;
	}

	private  void control(String opCode) {
		// TODO Auto-generated method stub

	}

	public  String[] AluCon(String opCode,String rs,String rt) {
		String AluResult="";
		String lessThan=lessThan(rs,rt);
		if (opCode.equals("000"))
			AluResult=add(rs,rt);
		if (opCode.equals("001"))
			AluResult=or(rs,rt);
		if (opCode.equals("010"))
			AluResult=not(rs);
		if (opCode.equals("011"))
			AluResult=slr(rs,rt);
		if (opCode.equals("100"))
			AluResult=lessThan(rs,rt);
		
		String[] s=new String[2];
		s[0]=lessThan;
		while(AluResult.length()<16) {
			AluResult="0"+AluResult;
		}
		s[1]=AluResult;
		return s;
	}

	private  String lessThan(String rs,String rt) {
		String lessThan = "0";
		int b1 = Integer.parseInt(rs, 2);
		int b2 = Integer.parseInt(rt, 2);
		if (b1 < b2)
			lessThan = "1";
		return lessThan;
	}

	private  String slr(String rs,String rt) {
		return "0" + rs.substring(0, rs.length() - 1);
	}

	//ouda's original not
	private  String not(String rs,String rt) {
		for (int i = 0; i < rs.length(); i++)
			if (rs.charAt(i) == '1')
				rt += 0;
			else
				rt += 1;
		return rt;
	}
	
	//modified not
	private  String not(String rs) {
		String rt="";
		for (int i = 0; i < rs.length(); i++)
			if (rs.charAt(i) == '1')
				rt += 0;
			else
				rt += 1;
		return rt;
	}
	


	private  String or(String rs,String rt) {
		String result = "";
		for (int i = 0; i < rs.length(); i++) {
			if (rs.charAt(i) == '1' || rt.charAt(i) == '1')
				result += 1;
			else
				result += 0;
		}
		return result;
	}

	public  String add(String rs,String rt) {
		int b1 = Integer.parseInt(rs, 2);
		int b2 = Integer.parseInt(rt, 2);
		int sum = b1 + b2;
		rt = Integer.toBinaryString(sum);
		if (rt.length() > 16) {
			rt = rt.substring(1, rt.length());
		}
		return rt;
	}
	private  String shiftLeft(String in) {
		return  in.substring(1) +"0";
	}
	private  String[] controller(String bunIndirect, String opCode) {
//		BUNIndirect 0
//		PCSrc 1
//		RegWrite 2
//		ALUOp 3
//		MemToReg 4
//		SKP 5
//		MemRead 6
//		MemWrite 7
		String[] control = new String[8];
		for (int i = 0; i < control.length; i++) {
			control[i] = "0";
		}
		if (bunIndirect.equals("1")) {
			if (opCode.equals("101")) { // BUN
				control[0] = "1"; // to take into consdration PCSrc is aready 0
			}
		}
		// ADD OR NOT SLR
		if (bunIndirect.equals("0")) {
			if (opCode.equals("000") || opCode.equals("001") || opCode.equals("010") || opCode.equals("011")) {
				control[3] = "1";
				control[2] = "1";
				control[1] = "1"; // to take into consdration memtoreg is aready 0
			}
			if (opCode.equals("100")) { // KLT
				control[1] = "1";
				control[5] = "1";
				control[3] = "1";// if re less than rt

			}
			if (opCode.equals("101")) { // BUN
				// to take into considration PCSrc and bunIndirect are aready 0
			}
			if (opCode.equals("110")) { // LW
				control[1] = "1";
				control[2] = "1";
				control[4] = "1";
				control[6] = "1";
			}
			if (opCode.equals("111")) {// SW
				control[1] = "1";
				control[7] = "1";
			}
		}
//		System.out.println("control: "+Arrays.toString(control));
		return control;

	}

	// fetch
	public  void stage1(int time) {
		String[] arr=InstrMem(PC);
		F_D.indBit.add(arr[0]);
		F_D.opCode.add(arr[1]);
		F_D.readReg1.add(arr[2]);
		F_D.readReg2.add(arr[3]);
		F_D.signExtend.add(arr[2]+arr[3]);
		
		tmp.add(new instruction(arr[0],arr[1],time,arr[2],arr[3]));
	}

	// decode
	public  void stage2(instruction instr) {
		String rs=F_D.readReg1.poll();
		String rt=F_D.readReg2.poll();
		String opCode=F_D.opCode.poll();
		String signExtend=signExtend(F_D.signExtend.poll());
		String[] control = controller(F_D.indBit.poll(), opCode);
		String[] regs=RegisterFileRead(rs, rt);
//		BUNIndirect 0
//		PCSrc 1
//		RegWrite 2
//		ALUOp 3
//		MemToReg 4
//		SKP 5
//		MemRead 6
//		MemWrite 7
		D_E.BUNIndirect.add(control[0]);
		D_E.PCSrc.add(control[1]);
		D_E.RegWrite.add(control[2]);
		D_E.ALUOp.add(control[3]);
		D_E.MemToReg.add(control[4]);
		D_E.SKP.add(control[5]);
		D_E.MemRead.add(control[6]);
		D_E.MemWrite.add(control[7]);
		
		D_E.readData1.add(regs[0]);
		D_E.readData2.add(regs[1]);
		D_E.signExtend.add(signExtend);
		D_E.opCode.add(opCode);
		D_E.writeReg.add(rt);

		instr.finTime+=1;
		instr.New=false;
	}

	// execute
	public  void stage3(instruction instr) {
		String PCSrc=D_E.PCSrc.poll();
		String BUNIndirect=D_E.BUNIndirect.poll();
		String extended=D_E.signExtend.poll();
		String readData1=D_E.readData1.poll();
		String readData2=D_E.readData2.poll();
		String opCode=D_E.opCode.poll();
		String ALUOp=D_E.ALUOp.poll();
		String SKP=D_E.SKP.poll();
		
		String[] AluR=new String[2];

		AluR=AluCon(opCode,readData1,readData2).clone();
		

		String Adder1inp=Mux("1", "10",And(AluR[0], SKP));

		String add1=Adder(PC, Adder1inp);

		String Mux=Mux(extended,readData1,BUNIndirect);

		String shiftMux=shiftLeft(Mux);

		
		PC=Mux(Mux, add1,PCSrc);
		E_M.ALUResult.add(AluR[1]);			
		E_M.address.add(readData1);
		E_M.readData2.add(readData2);
		E_M.writeReg.add(D_E.writeReg.poll());
		E_M.MemRead.add(D_E.MemRead.poll());
		E_M.MemWrite.add(D_E.MemWrite.poll());
		E_M.MemToReg.add(D_E.MemToReg.poll());
		E_M.RegWrite.add(D_E.RegWrite.poll());
		
		instr.finTime+=1;
	}

	// memory access
	public  void stage4(instruction instr) {
		String ReadData2=E_M.readData2.poll();
		String address=E_M.address.poll();
		String MemWrite=E_M.MemWrite.poll();
		String MemRead=E_M.MemRead.poll();
		String readData=MemoryData(address, ReadData2, MemWrite, MemRead);
		M_W.readData.add(readData);
		M_W.ALUResult.add(E_M.ALUResult.poll());
		M_W.writeReg.add(E_M.writeReg.poll());
		M_W.MemToReg.add(E_M.MemToReg.poll());
		M_W.RegWrite.add(E_M.RegWrite.poll());
		
		instr.finTime+=1;
	}

	// write back
	public  void stage5(instruction instr) {
		String ALUresult = M_W.ALUResult.poll();
		String memoryResult = M_W.readData.poll();
		String MemToReg = M_W.MemToReg.poll();
		String writeData = Mux(ALUresult, memoryResult, MemToReg);
		RegisterFileWrite(M_W.writeReg.poll(), writeData, M_W.RegWrite.poll());
		instr.finTime+=1;
		instr.finished=true;
	}
	
	public  void Run() {
//		initRegesters();
		stage1(0);	
		instructions.add(tmp.remove(0));
		int instrCnt=0;
		
		int time=1;
		while(instructions.size()>0 && instrCnt<instrLimit){
			System.out.println("time "+time+" " +instructions + " / size:" +instructions.size() );
			System.out.println("PC : "+PC);
			time++;

			for(int i=0;i<instructions.size();i++) {
				int stageNum=instructions.get(i).stage;
				String instrName=instructions.get(i).name;
				 
				switch(stageNum) {
				case 2:stage2(instructions.get(i));break;
				case 3:System.out.println("before stage3-PC: "+PC);stage3(instructions.get(i));System.out.println("after stage3-PC: "+PC);stage1(instructions.get(i).finTime);break;
				case 4:stage4(instructions.get(i));break;
				case 5:stage5(instructions.get(i));break;
				}
				instructions.get(i).stage=stageNum+1;
			}
			
			while(tmp.size()>0) {
				instructions.add(tmp.remove(0));
			}
			
			for(int i=0;i<instructions.size();) {
				if(instructions.get(i).stage>5) {
					String name=instructions.get(i).name;
					instrCnt++;
					System.out.println("-------------------");
					System.out.println(instructions.get(i));
					System.out.println("register t0 "+registers[40]);
					System.out.println("register t1 "+registers[41]);
					System.out.println("register t2 "+registers[42]);
					System.out.println("register t3 "+registers[43]);
					System.out.println("register t4 "+registers[44]);
					System.out.println("register t5 "+registers[45]);
					System.out.println("register t6 "+registers[46]);
					System.out.println("register t7 "+registers[47]);
					System.out.println("mem[1] "+mem[1]);
					System.out.println("PC "+PC);
					System.out.println("-------------------");
					instructions.remove(i);
				}
				else
					i++;
			}
			System.out.println("time "+time+" " +instructions + " / size:" +instructions.size() );
			System.out.println("=======================");

		}
	}
	public  String convert(String s)
	{
		String in[]=s.split(" ");
		String instr=convertInstruction(in[0]);
		String ret=instr;
		if(ret.equals("error"))
			return ret;
		for(int i=1;i<in.length;i++)
		{
			int registerNum=convertRegisterToBinary(in[i]);
			if(registerNum == -1)
				return "error";
			String register = Integer.toBinaryString(registerNum);
			int offset=6-register.length();
			while(offset-->0)
				register="0"+register;
			ret+=register;
		}
		if(in[0].equals("bunr"))
			ret+="000000";
		return ret;
	}
	public  String convertInstruction(String s) {
		switch(s)
		{
		case "add":return "0000";
		case "or":return "0001";
		case "not":return "0010";
		case "slr":return "0011";
		case "klt":return "0100";
		case "bun":return "0101";
		case "bunr":return "1101";
		case "lw":return "0110";
		case "sw":return "0111";
		default:return "error";
		}
	}
	public  int convertRegisterToBinary(String select) {
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
	public  String intToString(int sel) {

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

	public  String print(){
		String ret="";
		for(int i=0;i<64;i++){
			ret+=intToString(i)+" = "+registers[i]+"<br/>";
		}
		return "<html>"+ret+"<html>";
	}
	public  String print1(){
		String ret="";
		for(int i=0;i<64;i++){
			ret+=intToString(i)+" = "+registers[i]+"\n";
		}
		return ret;
	}
	public  String compile(String s) throws IOException{
		initRegesters();
		String lines[] = s.split("\n");
		int i=0;
		for(i=0;i<lines.length;i++)
		{
			if(i!=lines.length-1)
				lines[i]=lines[i].substring(0, lines[i].length()-1);
			System.out.println(lines[i]);
			String binary=convert(lines[i]);
			if(binary.equals("error"))
				return "line number "+i+" has an error";
			else 
				mem[i]=binary;
		}
		instrLimit=lines.length;
		Run();
		return "Done";
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//initializing the registers and the memory
		Main main=new Main();
		System.out.println("Enter compile to compile or enter an instruction like");
		System.out.println("add $t0 $c1");
		int i=0;
		String in ="";
		while(true)
		{
			String s=br.readLine();
			if(s.equals("compile"))
				break;
			in+=s+"\n";
		}
		main.instrLimit=i;
		System.out.println(main.compile(in));
		System.out.println(main.print1());
	}

}