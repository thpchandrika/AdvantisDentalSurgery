package cs489.apsd.dentalsurgeryapp;

import cs489.apsd.dentalsurgeryapp.domain.*;
import cs489.apsd.dentalsurgeryapp.repository.*;
import cs489.apsd.dentalsurgeryapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class DentalsurgeryappApplication implements CommandLineRunner {

	@Autowired
	private DentistReporitory dentistReporitory;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private SurgeryRepository surgeryRepository;

	@Autowired
	private AddressService addressService;

	public static void main(String[] args) {
		SpringApplication.run(DentalsurgeryappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome to Advantis Dental Surgery");
		System.out.println("================================================================================");
		showDataTables();
	}

	private void showDataTables(){
       var dentist1 = new Dentist(null,"D1", "Tony",
			   "Smith", "76543", "tony@tony.com",
			   "orthodontics", null);
	   var dentist2 = new Dentist(null,"D2", "Helen",
				"Pearson", "87654", "helen@helen.com",
				"orthodontics", null);
	   var dentist3 = new Dentist(null,"D3", "Robin",
				"Plevin", "76543", "robin@robin.com",
				"periodontics", null);


	   var address = new Address(null,"1000 N 4th St", "54334", "Fairfield", "Iowa");
	   var address1 = new Address(null,"100 N 4th St", "54334", "Fairfield", "Iowa");
	   var address2 = new Address(null,"10 N 4th St", "54334", "Fairfield", "Iowa");
	   var address3 = new Address(null,"101 N 4th St", "54334", "Fairfield", "Iowa");

	   addressService.addAddressList(List.of(address1, address2,address3,address));

	   var patient1 = new Patient(null,"P100","Gillian", "White", "654345",
			   "gillian@gillian.com", LocalDate.of(1994, 9,10),
			   address, null);

	   var patient2 = new Patient(null,"P105","Jill", "Bell", "65434",
				"jill@jill.com", LocalDate.of(1994, 9,10),
			   address1, null);

	   var patient3 = new Patient(null,"P108","Ian", "MacKay", "654345",
				"ian@ian.com", LocalDate.of(1994, 9,10),
			   address2, null);

		var patient4 = new Patient(null,"P110","John", "Walker", "987878",
				"john@john.com", LocalDate.of(1994, 9,10),
				address3, null);



		var surgery1 = new Surgery(null,"S15", "Wisom Teeth Surgery","345634",
				address, null);
		var surgery2 = new Surgery(null,"S10", "Root Canal Surgery","345634",
				address1, null);
		var surgery3 = new Surgery(null,"S13", "Oral Surgery","345634",
				address2, null);

		var appointment1 = new Appointment(null, LocalDate.of(2013,10,12),
				LocalTime.of(10, 0),"confirmed", surgery1, dentist1, patient1);
		var appointment2 = new Appointment(null, LocalDate.of(2013,10,12),
				LocalTime.of(12, 0),"confirmed", surgery1, dentist1,patient2);
		var appointment3 = new Appointment(null, LocalDate.of(2013,10,14),
				LocalTime.of(14, 0),"confirmed", surgery2,dentist2,patient3);
		var appointment4 = new Appointment(null, LocalDate.of(2013,10,14),
				LocalTime.of(16, 30),"confirmed", surgery1, dentist3,patient2);
		var appointment5 = new Appointment(null, LocalDate.of(2013,10,15),
				LocalTime.of(18, 0),"confirmed", surgery3,dentist3,patient4);
		var appointment6 = new Appointment(null, LocalDate.of(2013,10,12),
				LocalTime.of(10, 0),"confirmed", surgery2, dentist2, patient3);

      dentist1.setAppointments(List.of(appointment1, appointment2));
      dentist2.setAppointments(List.of(appointment6, appointment3));
	  dentist3.setAppointments(List.of(appointment4, appointment5));

	   patient1.setAppointments(List.of(appointment1));
	   patient2.setAppointments(List.of(appointment2, appointment4));
	   patient3.setAppointments(List.of(appointment6, appointment3));
	   patient4.setAppointments(List.of(appointment5));

		surgery1.setAppointments(List.of(appointment1,appointment2,appointment4));
		surgery2.setAppointments(List.of(appointment3,appointment6));
		surgery3.setAppointments(List.of(appointment5));

		dentistReporitory.saveAll(List.of(dentist1, dentist2,dentist3));
		patientRepository.saveAll(List.of(patient1, patient2,patient3, patient4));
		surgeryRepository.saveAll(List.of(surgery1, surgery2,surgery3));

	    appointmentRepository.saveAll(List.of(appointment1, appointment2,appointment3, appointment4, appointment5, appointment6));


		//List<Appointment> appointments = List.of(appointment1, appointment2, appointment3, appointment4, appointment5, appointment6);
		List<Appointment> appointments = appointmentRepository.findAll();

		System.out.printf("\ndentistName\t|\tpatNumber\t|\tpatName\t|\tappointment Date time\t|\tsurgeryNo\n");
		System.out.println("================================================================================");
		for(Appointment appointment: appointments){
			System.out.printf("\n%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s",
					appointment.getDentist().getFirstName() + " " + appointment.getDentist().getLastName(),
					appointment.getPatient().getPatientNumber(),
					appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName(),
					appointment.getAppointmentDate() + " " + appointment.getAppointmentTime(),
					appointment.getSurgery().getNumber());
		}

	}
}
