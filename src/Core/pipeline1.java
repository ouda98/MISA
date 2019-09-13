package Core;
import java.util.LinkedList;
import java.util.Queue;

public class pipeline1 {
	Queue<String> readReg1 = new LinkedList<>();
	Queue<String> readReg2 = new LinkedList<>();
	Queue<String> opCode = new LinkedList<>();
	Queue<String> indBit = new LinkedList<>();
	Queue<String> signExtend = new LinkedList<>();	
	public pipeline1() {
	}
	
	public String toString() {
		return "readReg1"+ readReg1+"\n"+
			   "readReg2"+ readReg2+"\n"+
			   "opCode"+opCode+"\n"+
			   "indBit"+indBit+"\n"+
			   "signExtend"+signExtend+"\n";
	}
}
