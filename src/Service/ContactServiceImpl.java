package Service;

import Model.Contact;
import Model.Phone;
import db.DataBase;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ContactServiceImpl implements ContactService {
    private DataBase dataBase;

    public ContactServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    @Override
    public String addContactToPhone(int phoneId, Contact contact) {
        Optional<Phone> optionalPhone = dataBase.getPhones().stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst();

        if (optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            phone.getContacts().add(contact);
            return "Контакт добавлен!!";
        } else {
            return "Телефон не найден";
        }
    }


    @Override
    public Contact findContactByName(int phoneId, String contactName) {
        Optional<Contact> optionalContact = dataBase.getPhones().stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst()
                .flatMap(phone -> phone.getContacts().stream()
                .filter(contact -> contact.getName().equals(contactName))
                .findFirst());

        return optionalContact.orElse(null);
    }


    @Override
    public Contact findContactByPhoneNumber(int phoneNumber) {
        Optional<Contact> optionalContact = dataBase.getPhones().stream()
                .flatMap(phone -> phone.getContacts().stream())
                .filter(contact -> contact.getPhoneNumber() == phoneNumber)
                .findFirst();

        return optionalContact.orElse(null);
    }


    @Override
    public List<Contact> sortContactsByName(int phoneId) {
        Optional<Phone> optionalPhone = dataBase.getPhones().stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst();

        return optionalPhone.map(phone -> {
            phone.getContacts().sort(Comparator.comparing(Contact::getName));
            return phone.getContacts();
        }).orElse(Collections.emptyList());
    }


    @Override
    public void deleteContactByNameFromPhone(int phoneId, String contactName) {
        dataBase.getPhones().stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst()
                .ifPresent(phone -> {
                    List<Contact> contacts = phone.getContacts();
                    contacts.removeIf(contact -> contact.getName().equals(contactName));
                });
    }

}
