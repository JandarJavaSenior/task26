package Service;

import Model.Phone;
import db.DataBase;

import java.util.List;
import java.util.Optional;

public class PhoneServiceImpl implements PhoneService {
    private DataBase dataBase;

    public PhoneServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String addPhone(Phone phone) {
        dataBase.getPhones().add(phone);
        return "DOBAVLEN!!!!";
    }

    @Override
    public Phone getPhoneById(int phoneId) {
        return dataBase.getPhones().stream().filter(phone -> phone.getId() == phoneId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Phone updatePhoneNameById(int phoneId, String newName) {
        Optional<Phone> optionalPhone = dataBase.getPhones().stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst();

        if (optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            phone.setName(newName);
            return phone;
        } else {
            return null;
        }
    }


    @Override
    public List<Phone> getAllPhones() {
        return dataBase.getPhones();
    }

    @Override
    public List<Phone> getAllPhonesByBrand(String brand) {
        List<Phone> list = dataBase.getPhones().stream().filter(phone -> phone.getBrand().equalsIgnoreCase(brand)).toList();
        return list;
    }

    @Override
    public void deletePhoneById(int phoneId) {
        dataBase.getPhones().removeIf(phone -> phone.getId() == phoneId);
        System.out.println("udalen!!!!!!!!");
    }

}
