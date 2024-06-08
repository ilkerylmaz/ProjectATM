/* ATM örneği

-müşteri ibanı ile para gönderilecek
-müşterinin adı, soyadı ve ibanı hashmap sayesinde basitçe kaydedilecek
**işlemler**
- hesaptan para çekmek --> 1
- hesaba para yatırmak --> 2
- hesaptan hesaba para transferi --> 3
- yeni bir hesap oluşturmak için --> 4
- şifre değiştrime işlemi --> 5
- bakiye sorgulama --> 6
- işlemi sonlandırmak için --> 7
 */

import java.util.Scanner;

public class ATM extends Main {
    Scanner scanner = new Scanner(System.in);

    public void choiceScreen() {
        System.out.println("\t Lütfen Yapmak İstediğiniz İşlemi Seçiniz\n1 -> Para çekmek\t 4 -> Hesap oluşturmak\t 7 -> Hesap Değistirmek" +
                "\n2 -> Para Yatırmak\t 5-> Şifre Değiştirmek\t 8 -> Çıkış Yapmak\n3 -> Para Transferi\t 6 -> Bakiye Sorgulama");
    }

    public void choiceScreen1() {
        System.out.println("\t Lütfen Yapmak İstediğiniz İşlemi Seçiniz\n1 -> Para çekmek\t 4 -> Hesap oluşturmak\t 7 -> Çıkış Yapmak" +
                "\n2 -> Para Yatırmak\t 5-> Şifre Değiştirmek\n3 -> Para Transferi\t 6 -> Bakiye Sorgulama");
    }

    public void withdrawBalance(String customerId, String balance) {
        Customer customer = map.get(customerId);
        int balanceInt = Integer.parseInt(balance);//String to Integer
        if (balanceInt > customer.balance) {
            System.out.println("Yeterli miktarda paranız bulunmamaktadır.");
        } else {
            System.out.println("Hesabınızdaki Para : " + customer.balance);
            customer.balance -= balanceInt;
            System.out.println("\nÇektiğiniz Miktar: " + balanceInt);
            System.out.println("\nGüncel " + customer.balance + " TL Paranız Bulunmaktadır.");
        }
    }

    public void depositBalance(String customerId, String balance) {
        Customer customer = map.get(customerId);
        int balanceInt = Integer.parseInt(balance);//String to Integer
        System.out.println("Hesabınızdaki Para : " + customer.balance);
        customer.balance += balanceInt;
        System.out.println("\nEklediğiniz Miktar: " + balance);
        System.out.println("\nGüncel " + customer.balance + " TL Paranız Bulunmaktadır.");
    }

    public void transferBalance(String customerId, String customerId2, String balance) {
        Customer customer = map.get(customerId); //paranın sahibi
        Customer customer2 = map.get(customerId2); //paranın aktarılacağı hesap
        int balance1 = Integer.parseInt(balance);// String to Integer
        if (balance1 > customer.balance) {
            System.out.println("Yetersiz Bakiye!");
        } else if (!map.containsKey(customerId2)) {
            System.out.println(customerId2 + " Böyle biri sistemde yoktur!");
        } else {
            customer.balance -= balance1;
            customer2.balance += balance1;
            System.out.println(customerId2 + " Hesabına Başarıyla Para Transferi Gerçekleşmiştir.");
        }
    }

    public void createNewAccount(String id, String surname, String password, String iban) {
        while (map.containsKey(id)) { // id Hashmap'in içinde var mı?
            System.out.println("Kullandığınız id numarası daha önce kullanılmış lütfen başka bir numara deneyin! \n id --->");
            id = scanner.next();
        }
        map.put(id, new Customer(surname, password, 0, iban));
        System.out.println(id + " Numaralı hesabınız başarıyla oluşturulmuştur.");
    }

    public void changePassword(String customerId, String newPassword) {
        Customer customer = map.get(customerId);
        while (customer.password.equals(newPassword)) { //customer password ?= newPassword
            System.out.println("Eski şifreniz yeni şifrenizle aynı olamaz.\n id --->");
            newPassword = scanner.next();
        }
        customer.password = newPassword;
        System.out.println("Şifreniz başarıyla değiştirilmiştir.");
    }

    public void changeAccount(String customerId, String password) {
        Customer customer = map.get(customerId);
        boolean control = true;
        while (control) {
            choiceScreen1();
            int choice;
            System.out.println("---->");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Çekmek istediğiniz tutarı giriniz: ");
                    String money = scanner.next();
                    withdrawBalance(customerId, money);
                    break;
                case 2:
                    System.out.println("Yatırmak istediğiniz tutarı giriniz: ");
                    String money2 = scanner.next();
                    depositBalance(customerId, money2);
                    break;
                case 3:
                    System.out.println("Parayı göndermek istediğiniz hesabın id numarasını giriniz: ");
                    String id = scanner.next();
                    System.out.println("Göndermek istediğiniz miktarı giriniz: ");
                    String money3 = scanner.next();
                    transferBalance(customerId, id, money3);
                    break;
                case 4:
                    System.out.println("Lütfen sırasıyla istenilen bilgileri giriniz: ");
                    System.out.println("Kullanıcı numarası: ");
                    String id2 = scanner.next();
                    System.out.println("Kullanıcı isminiz: ");
                    String surname = scanner.next();
                    System.out.println("Kullanıcı Şifreniz");
                    String password1 = scanner.next();
                    System.out.println("Hesap iban numaranız: ");
                    String iban = scanner.next();
                    createNewAccount(id2, surname, password1, iban);
                    break;
                case 5:
                    System.out.println("Lütfen yeni Şifreniz giriniz: ");
                    String password2 = scanner.next();
                    changePassword(customerId, password2);
                    break;
                case 6:
                    System.out.println("Hesaptan kalan Bakiyeniz: " + customer.balance + " TL");
                    break;
                case 7:
                    System.out.println("İyi Günler \nHesaptan Çıkış Yapılıyor ...");
                    System.exit(1);

            }
        }
    }
}
