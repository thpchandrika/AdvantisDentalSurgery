package cs489.apsd.dentalsurgeryapp.util;

import cs489.apsd.dentalsurgeryapp.dto.appointment.AppointmentConfirmationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendAppointmentConfirmationEmail(AppointmentConfirmationDto appointmentDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appointmentDto.to());
        message.setFrom("mangar.chan3@gmail.com");
        message.setSubject("Appointment Confirmation");
        message.setText(String.format(
                "Dear %s\n\n Your appointment has been confirmed on %s at %s with dentist %s at location %s. \n\n Best Regards,\n\nADS Dental Clinic",
                appointmentDto.patient(),
                appointmentDto.appointmentDate(),
                appointmentDto.appointmentTime(),
                appointmentDto.dentist(),
                appointmentDto.surgeryLocation()
        ));
        javaMailSender.send(message);
    }

    public void sendTestEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("test@mailinator.com");
        message.setFrom("chandrika@mailinator.com");
        message.setSubject("Test");
        message.setText("Hello this is test messsage");
        javaMailSender.send(message);
    }
}
