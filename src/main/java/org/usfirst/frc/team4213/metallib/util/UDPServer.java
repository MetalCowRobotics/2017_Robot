package org.usfirst.frc.team4213.metallib.util;

import java.io.IOException;
import java.net.*;

public class UDPServer implements Runnable {

	private final int SOCKET_NUMBER_DEFAULT = 5808;
	private int socketNumber;
	private double offsetX;
	private double offsetY;
	private DatagramSocket serverSocket;
	
	
	public double getOffsetX() {
		return offsetX;
	}

	private void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}

	private void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}

	public UDPServer() {
		setSocketNumber(SOCKET_NUMBER_DEFAULT);
		try {
			serverSocket = new DatagramSocket(getSocketNumber());
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public UDPServer(int socketNumber) {
		setSocketNumber(socketNumber);
	}

	private int getSocketNumber() {
		return socketNumber;
	}

	private void setSocketNumber(int socketNumber) {
		this.socketNumber = socketNumber;
		try {
			serverSocket = new DatagramSocket(getSocketNumber());
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		
		try {

			byte[] receiveData = new byte[1024];

			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			serverSocket.receive(receivePacket);
			String offsets = new String(receivePacket.getData());
			String[] xAndY = offsets.split(",");
			setOffsetX(Double.parseDouble(xAndY[0]));
			setOffsetY(Double.parseDouble(xAndY[1]));

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}