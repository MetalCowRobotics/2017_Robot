package org.usfirst.frc.team4213.metallib.util;

import java.io.IOException;
import java.net.*;

public class UDPServer implements Runnable {

	private final int SOCKET_NUMBER_DEFAULT = 5808;
	private int socketNumber;
	private double offsetX;
	private double offsetY;

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
	}

	public UDPServer(int socketNumber) {
		setSocketNumber(socketNumber);
	}

	private int getSocketNumber() {
		return socketNumber;
	}

	private void setSocketNumber(int socketNumber) {
		this.socketNumber = socketNumber;
	}

	public void run() {
		DatagramSocket serverSocket;
		try {
			serverSocket = new DatagramSocket(getSocketNumber());

			byte[] receiveData = new byte[1024];

			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			serverSocket.receive(receivePacket);
			String offsets = new String(receivePacket.getData());
			String[] xAndY = offsets.split(",");
			setOffsetX(Double.parseDouble(xAndY[0]));
			setOffsetY(Double.parseDouble(xAndY[1]));

			// System.out.println("RECEIVED: " + sentence);
			// InetAddress IPAddress = receivePacket.getAddress();
			// int port = receivePacket.getPort();

			// we are not sending anything back
			// byte[] sendData = new byte[1024];
			// DatagramPacket sendPacket = new DatagramPacket(sendData,
			// sendData.length, IPAddress, port);
			// serverSocket.send(sendPacket);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}