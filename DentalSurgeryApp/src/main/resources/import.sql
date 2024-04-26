INSERT INTO `advantissurgerydb`.`addresses` (`id`, `city`, `state`, `street`, `zip`) VALUES ('1', 'Fairfield', 'IA', '1000 N 4th St', '52557');
INSERT INTO `advantissurgerydb`.`addresses` (`id`, `city`, `state`, `street`, `zip`) VALUES ('2', 'Euless', 'TX', '605 Del Paso St', '76040');
INSERT INTO `advantissurgerydb`.`addresses` (`id`, `city`, `state`, `street`, `zip`) VALUES ('3', 'Fairfield', 'IA', '1000 N 4th St', '52556');
INSERT INTO `advantissurgerydb`.`addresses` (`id`, `city`, `state`, `street`, `zip`) VALUES ('4', 'Euless', 'TX', '600 Del Paso St', '76041');

INSERT INTO `advantissurgerydb`.`dentists` (`id`, `dentistid`, `email`, `first_name`, `last_name`, `phone_number`, `specialization`) VALUES ('1', 'D1', 'tony@tony.com', 'Tony', 'Smith', '76543', 'orthodontics');
INSERT INTO `advantissurgerydb`.`dentists` (`id`, `dentistid`, `email`, `first_name`, `last_name`, `phone_number`, `specialization`) VALUES ('2', 'D2', 'helen@helen.com', 'Helen', 'Pearson', '87654', 'orthodontics');
INSERT INTO `advantissurgerydb`.`dentists` (`id`, `dentistid`, `email`, `first_name`, `last_name`, `phone_number`, `specialization`) VALUES ('3', 'D3', 'robin@robin.com', 'Robin', 'Plevin', '76543', 'periodontics');

INSERT INTO `advantissurgerydb`.`patients` (`dob`, `id`, `mailing_address_id`, `email`, `first_name`, `last_name`, `patient_number`, `phone_number`) VALUES ('1994-09-10', '1', '1', 'gillian@mailinator.com', 'Gillian', 'White', 'P100', '65432435');
INSERT INTO `advantissurgerydb`.`patients` (`dob`, `id`, `mailing_address_id`, `email`, `first_name`, `last_name`, `patient_number`, `phone_number`) VALUES ('1995-10-10', '2', '2', 'jill@mailinator.com', 'Jill', 'Bell', 'P105', '65434564');
INSERT INTO `advantissurgerydb`.`patients` (`dob`, `id`, `mailing_address_id`, `email`, `first_name`, `last_name`, `patient_number`, `phone_number`) VALUES ('1993-08-14', '3', '3', 'ian@mailinator.com', 'Ian', 'MacKay', 'P108', '5432456');
INSERT INTO `advantissurgerydb`.`patients` (`dob`, `id`, `mailing_address_id`, `email`, `first_name`, `last_name`, `patient_number`, `phone_number`) VALUES ('1990-10-12', '4', '4', 'john@mailinator.com', 'John', 'Walker', 'P110', '45643245');

INSERT INTO `advantissurgerydb`.`surgeries` (`id`, `surgery_location_id`, `name`, `number`, `phone`) VALUES ('1', '1', 'Wisom Teeth Surgery', 'S15', '6543245');
INSERT INTO `advantissurgerydb`.`surgeries` (`id`, `surgery_location_id`, `name`, `number`, `phone`) VALUES ('2', '2', 'Root Canal Surgery', 'S10', '987654323');
INSERT INTO `advantissurgerydb`.`surgeries` (`id`, `surgery_location_id`, `name`, `number`, `phone`) VALUES ('3', '3', 'Oral Surgery', 'S13', '7654345');

INSERT INTO `advantissurgerydb`.`appointments` (`appointment_date`, `appointment_time`, `dentist_id`, `id`, `patient_id`, `surgery_id`, `status`) VALUES ('2024-08-09', '10:30', '1', '1', '1', '1', 'APPROVED');
INSERT INTO `advantissurgerydb`.`appointments` (`appointment_date`, `appointment_time`, `dentist_id`, `id`, `patient_id`, `surgery_id`, `status`) VALUES ('2024-08-10', '10:30', '1', '2', '2', '1', 'APPROVED');
INSERT INTO `advantissurgerydb`.`appointments` (`appointment_date`, `appointment_time`, `dentist_id`, `id`, `patient_id`, `surgery_id`, `status`) VALUES ('2024-08-11', '10:30', '2', '3', '3', '2', 'APPROVED');
INSERT INTO `advantissurgerydb`.`appointments` (`appointment_date`, `appointment_time`, `dentist_id`, `id`, `patient_id`, `surgery_id`, `status`) VALUES ('2024-08-12', '10:30', '3', '4', '2', '1', 'APPROVED');
INSERT INTO `advantissurgerydb`.`appointments` (`appointment_date`, `appointment_time`, `dentist_id`, `id`, `patient_id`, `surgery_id`, `status`) VALUES ('2024-08-13', '10:00', '3', '5', '4', '3', 'APPROVED');
INSERT INTO `advantissurgerydb`.`appointments` (`appointment_date`, `appointment_time`, `dentist_id`, `id`, `patient_id`, `surgery_id`, `status`) VALUES ('2024-08-14', '11:00', '2', '6', '3', '2', 'APPROVED');




