import Model.Contact;
import Model.Phone;
import Service.ContactService;
import Service.ContactServiceImpl;
import Service.PhoneServiceImpl;
import db.DataBase;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Contact contact1 = new Contact("Jandar", 707986784);
        Contact contact2 = new Contact("Opko", 66666666);
        Contact contact3 = new Contact("Jurok", 11111111);
        ArrayList<Contact> contacts1 = new ArrayList<>(Arrays.asList(contact1, contact2, contact3));
        Phone phone1 = new Phone("Redmi Pro 12","Redmi",contacts1);

        Contact contact4 = new Contact("Aibek", 12345678);
        Contact contact5 = new Contact("Gulnaz", 98765432);
        ArrayList<Contact> contacts2 = new ArrayList<>(Arrays.asList(contact4, contact5));
        Phone phone2 = new Phone("Redmi Pro 12","Iphone",contacts2);

        Contact contact6 = new Contact("Elena", 55555555);
        Contact contact7 = new Contact("Gulnaz", 98765432);
        Contact contact8 = new Contact("Gulnaz", 98765432);
        ArrayList<Contact> contacts3 = new ArrayList<>(Arrays.asList(contact6,contact7));
        Phone phone3 = new Phone("Redmi Pro 12","Redmi",contacts3);

        ArrayList<Phone>phones = new ArrayList<>(Arrays.asList(phone1,phone2));

        DataBase dataBase = new DataBase(phones);
        ContactServiceImpl contactSer = new ContactServiceImpl(dataBase);
        PhoneServiceImpl phoneSer = new PhoneServiceImpl(dataBase);


        System.out.println(phoneSer.addPhone(phone3));
        System.out.println("-----------------------------------------------");
        System.out.println(phoneSer.getPhoneById(3));
        System.out.println("-----------------------------------------------");
        System.out.println(phoneSer.updatePhoneNameById(3, "Jandar"));
        System.out.println("-----------------------------------------------");
        System.out.println(phoneSer.getAllPhones());
        System.out.println("-----------------------------------------------");
        System.out.println(phoneSer.getAllPhonesByBrand("iphone"));
        System.out.println("-----------------------------------------------");
        phoneSer.deletePhoneById(3);
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println(contactSer.addContactToPhone(2, contact8));
        System.out.println("-----------------------------------------------");
        System.out.println(contactSer.findContactByName(2, "Gulnaz"));
        System.out.println("-----------------------------------------------");
        System.out.println(contactSer.findContactByPhoneNumber(707986784));
        System.out.println("-----------------------------------------------");
        System.out.println(contactSer.sortContactsByName(2));
        System.out.println("-----------------------------------------------");
        contactSer.deleteContactByNameFromPhone(2,"Gulnaz");
        System.out.println("-----------------------------------------------");


    }
}