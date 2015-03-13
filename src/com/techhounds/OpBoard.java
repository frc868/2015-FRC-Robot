package com.techhounds;

import edu.wpi.first.wpilibj.buttons.Button;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.techhounds.OpBoard.TwoWaySerialComm.*;

public class OpBoard {
	
	private static TwoWaySerialComm com = (new OpBoard()).new TwoWaySerialComm();
	private static SerialWriter write;
	private static byte[] data = new byte[1024];
	private static String comNum = "COM1";

	public static byte TRUE = (byte) 1;
	public static byte FALSE = (byte) 0;
	
	public static String[] buttonNames = new String[]{"Button One"};
	public static String[] ledNames = new String[]{"LED One"};
	
	public static BoardButton[] buttons = new BoardButton[buttonNames.length];
	public static boolean[] ledStates = new boolean [ledNames.length];
	public static double slidePos;
	public static double slideTarget;
	
	public static void init(){
		try{
            com.connect(comNum);
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	public static void sendData(boolean[] ledStates, double slideTarget){
		byte[] data = new byte[ledStates.length];
		
		for(int i = 0; i < data.length; i++){
			data[i] = ledStates[i] ? TRUE : FALSE;
		}
		
		write(data);
		write(new byte[]{(byte) slideTarget});
	}
	
	private static void write(byte[] data){
		write.write(data);
	}
	
	private static void useData(){
		int last = 0;
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i].set(data[i] == TRUE);
			last = i;
		}
		
		slidePos = (double) data[last + 1];
	}
	
	protected class BoardButton extends Button{

		private boolean activated = false;
		
		public void set(boolean pressed){
			activated = pressed;
		}
		
		public boolean get() {
			return activated;
		}
		
	}
	
	public class TwoWaySerialComm{
		
	    public TwoWaySerialComm(){

	    }
	    
	    public void connect(String portName) throws Exception{
	    	
	        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
	        
	        if (portIdentifier.isCurrentlyOwned()){
	            System.out.println("Error: Port is currently in use");
	        }else{
	        	
	            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
	            
	            if (commPort instanceof SerialPort){
	            	
	                SerialPort serialPort = (SerialPort) commPort;
	                serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
	                
	                InputStream in = serialPort.getInputStream();
	                OutputStream out = serialPort.getOutputStream();
	                               
	                write = new SerialWriter(out);
	                
	                serialPort.addEventListener(new SerialReader(in));
	                serialPort.notifyOnDataAvailable(true);

	            }else{
	                System.out.println("Error: Only serial ports are handled by this example.");
	            }
	        }     
	    }
	    
	    public class SerialReader implements SerialPortEventListener{
	    	
	        private InputStream in;
	        private byte[] buffer = new byte[1024];
	        
	        public SerialReader(InputStream in){
	            this.in = in;
	        }
	        
	        public void serialEvent(SerialPortEvent arg0){
	        	
	            int data;
                int len = 0;
	          
	            try{
	            	
	                while ((data = in.read()) > -1){
	                	
	                    if (data == '\n'){
	                        break;
	                    }
	                    buffer[len++] = (byte) data;
	                }
//	                System.out.print(new String(buffer,0,len));
	                OpBoard.data = buffer;
	                OpBoard.useData();
	                
	            }catch ( IOException e ){
	                e.printStackTrace();
	                System.exit(-1);
	            }             
	        }
	    }

	    public class SerialWriter{
	    	
	        OutputStream out;
	        
	        public SerialWriter(OutputStream out){
	            this.out = out;
	        }
	        
	        public void write(byte[] data){
	        	
	            try{
	            	
	                while ((System.in.read()) > -1 ){
	                    this.out.write(data);
	                }
	                
	            }catch ( IOException e ){
	                e.printStackTrace();
	                System.exit(-1);
	            }            
	        }
	    }
	}
}