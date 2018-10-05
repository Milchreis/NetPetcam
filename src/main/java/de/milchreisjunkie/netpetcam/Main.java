package de.milchreisjunkie.netpetcam;

public class Main {

	public static void main(String args[]) throws Exception {
		// Create ConfigManager by commandline-interface and start the programm
		new NetPetcam(CLIManager.parse(args)).start();
	}
}
