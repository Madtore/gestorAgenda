package gestorAgenda.agenda;

import java.io.File;

public class App {
	public static void main(String[] args) {
		
		String ruta = "D:"+ File.separator + "EjemplosJava"+ File.separator+"agenda.txt";
		Agenda agenda = new Agenda(ruta);
		
		agenda.addContact(new Contacto("Juan", "123456789", "juan@gmail.com"));
		agenda.addContact(new Contacto("Maria", "987654321", "maria@hotmail.com"));
		agenda.addContact(new Contacto("Pedro", "456789123", "pedro@yahoo.com"));	
		
		System.out.println("--------------------------------");
		agenda.saveContactToFile();
		
		agenda.listContacts();
		System.out.println("--------------------------------");
		agenda.addContact(new Contacto("Pedrito", "456789123", "pedro@gmail.com"));	
		
		agenda.saveContactToFile();
		
		agenda.listContacts();
		System.out.println("--------------------------------");
		agenda.filterGmail();
	}

}
