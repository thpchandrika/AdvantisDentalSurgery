package cs489.apsd.dentalsurgeryapp.dto.dentist;

import cs489.apsd.dentalsurgeryapp.domain.Dentist;

import java.util.List;

public class DentistAdapter {

    public static Dentist getDentist(DentistRequest request){
        Dentist dentist = new Dentist(request.dentistID(),
                request.firstName(),
                request.lastName(),
                request.phoneNumber(),
                request.email(),
                request.specialization());
        return dentist;
    }

    public static DentistResponse getDentistResponse(Dentist dentist){
        DentistResponse dentistResponse = new DentistResponse(dentist.getId(),
                dentist.getDentistID(),
                dentist.getFirstName(),
                dentist.getLastName(),
                dentist.getPhoneNumber(),
                dentist.getEmail(),
                dentist.getSpecialization());
        return dentistResponse;
    }

    public static List<DentistResponse> getDentistResponseList(List<Dentist> dentistList){
       var dentistResponseList = dentistList.stream().map(d -> new DentistResponse(
                d.getId(),
                d.getDentistID(),
                d.getFirstName(),
                d.getLastName(),
                d.getPhoneNumber(),
                d.getEmail(),
                d.getSpecialization()
        )).toList();
        return dentistResponseList;
    }
}
