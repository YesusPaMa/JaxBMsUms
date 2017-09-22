package com.simple.standalone.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.joda.time.LocalDate;

import com.simple.standalone.xml.modelo.Alumno;
import com.simple.standalone.xml.modelo.Universidad;


public class JaxbRun {

	private static final String XML_FILE = "centro_educativo.xml";

	public static void main(String[] args) throws JAXBException,
			FileNotFoundException {

		List<Alumno> students = new ArrayList<Alumno>();

		Alumno s1 = new Alumno();
		s1.setNombre("Jesus");
		s1.setApellido("Parra");
		s1.setTitulo("ING Sistemas");
		s1.setFechaNac(new LocalDate(1992, 11, 27));
		s1.setId(1);
		students.add(s1);

		Alumno s2 = new Alumno();
		s2.setNombre("Jessica");
		s2.setApellido("Blancas");
		s2.setTitulo("DOC Medicina");
		s2.setFechaNac(new LocalDate(1995, 10, 3));
		s2.setId(2);
		students.add(s2);

		Alumno s3 = new Alumno();
		s3.setNombre("Marco");
		s3.setApellido("Polo");
		s3.setTitulo("Guerrero Samurai");
		s3.setFechaNac(new LocalDate(1254, 9, 15));
		s3.setId(3);
		students.add(s3);

		Universidad universidad = new Universidad();
		universidad.setNombre("UNAM");
		universidad.setDireccion("México");
		universidad.setAlumnos(students);

		// create JAXB context
		JAXBContext context = JAXBContext.newInstance(Universidad.class);

		System.out.println("<!----------Generación de la salida XML-------------->");
		
		// Marshalling [Generar XML desde JAVA]
		// Crear Marshaller usando JAXB context
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		m.marshal(universidad, System.out);
		m.marshal(universidad, new File(XML_FILE));


		
		System.out.println("<!---------------Generación de objetos Java desde entrada XML-------------->");
		// UnMarshalling [Generar objeto JAVA desde XML]
		// Instanciar Unmarshaller mediante el context
		Unmarshaller um = context.createUnmarshaller();

		Universidad universidadum = (Universidad) um.unmarshal(new FileReader(XML_FILE));
		List<Alumno> alumnosLista = universidadum.getAlumnos();
		for (Alumno s : alumnosLista) {
			System.out.println("Alumnos : " + s);
		}
	}

}
