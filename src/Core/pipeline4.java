package Core;
import java.util.LinkedList;
import java.util.Queue;

public class pipeline4 {
	Queue<String> readData = new LinkedList<>();
	Queue<String> writeReg = new LinkedList<>();
	Queue<String> ALUResult = new LinkedList<>();
	Queue<String> MemToReg = new LinkedList<>();
	Queue<String> RegWrite = new LinkedList<>();
	public pipeline4() {
		
	}
	public String toString() {
		return "readData"+ readData+"\n"+
			   "writeReg"+ writeReg+"\n"+
			   "ALUResult"+ALUResult+"\n"+
			   "MemToReg"+MemToReg+"\n"+
			   "RegWrite"+RegWrite+"\n";
	}
}
