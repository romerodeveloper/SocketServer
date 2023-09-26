import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SocketDef {
	OperationsDB db = new OperationsDB();

	public void Initialize() {
		ServerSocket servidor = null;
		Socket sc = null;
		final int PUERTO = 9000;

		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Servidor en ejecución");

			while (true) {
				sc = servidor.accept();

				System.out.println("Petición cliente iniciada");
								
				DataInputStream objectIS = new DataInputStream(sc.getInputStream());
				String telefono =  objectIS.readUTF();

				Ciudad_Persona recep = validateOperation(telefono);
				if (recep.getDir_tel() == null) {
					recep.setMensaje_error("El telefono no ha sido encontrado");
				}
				
				ObjectOutputStream out = new ObjectOutputStream(sc.getOutputStream());
				out.writeObject(recep);
								
				sc.close();
			}
		} catch (IOException ex) {
			Logger.getLogger(SocketDef.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	public Ciudad_Persona validateOperation(String telefono) {
		
		return db.Buscar(telefono);
		
	}

}
