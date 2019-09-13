package Core;
import java.util.LinkedList;
import java.util.Queue;

public class pipeline2 {
	Queue<String> readData1 = new LinkedList<>();
	Queue<String> readData2 = new LinkedList<>();
	Queue<String> PCSrc = new LinkedList<>();
	Queue<String> RegWrite = new LinkedList<>();
	Queue<String> ALUOp = new LinkedList<>();
	Queue<String> MemToReg = new LinkedList<>();
	Queue<String> SKP = new LinkedList<>();
	Queue<String> MemRead = new LinkedList<>();
	Queue<String> MemWrite = new LinkedList<>();
	Queue<String> BUNIndirect = new LinkedList<>();
	Queue<String> signExtend = new LinkedList<>();
	Queue<String> opCode = new LinkedList<>();
	Queue<String> writeReg = new LinkedList<>();

	
	public pipeline2() {
		
	}
	public String toString() {
		return "readData1"+ readData1+"\n"+
			   "readData2"+ readData2+"\n"+
			   "PCSrc"+PCSrc+"\n"+
			   "RegWrite"+RegWrite+"\n"+
			   "ALUOp"+ALUOp+"\n"+
			   "MemToReg"+MemToReg+"\n"+
			   "SKP"+SKP+"\n"+
			   "MemRead"+MemRead+"\n"+
			   "MemWrite"+MemWrite+"\n"+
			   "BUNIndirect"+BUNIndirect+"\n"+
			   "signExtend"+signExtend+"\n"+
			   "opCode"+opCode+"\n"+
			   "writeReg"+writeReg+"\n";
	}
}
