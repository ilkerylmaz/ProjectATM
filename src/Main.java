import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static HashMap<String, Customer> map = new HashMap<>();

    public static void main(String[] args) {
        ATM atm = new ATM();
        map.put("0", new Customer("ilker", "12345", 5000, "TR-0001"));
        map.put("1", new Customer("Alper", "54321", 10000, "TR-0002"));
        map.put("2", new Customer("Mehmet", "01234", 20000, "TR-0003"));


        Scanner sc = new Scanner(System.in);
        System.out.println("\t--Hoşgeldiniz--\n-> Hesabınıza Giriş Yapınız.\n");
        System.out.println(" Kullanıcı Numarası: ");
        String customerId = sc.next();
        System.out.println("\n Şifrenizi Giriniz: ");
        String password = sc.next();
        Customer customer = map.get(customerId);
        if (map.containsKey(customerId) && customer.password.equals(password)) {
            System.out.println("\tBaşarıyla Giriş Yaptınız. ");
            boolean control = true;
            while (control) {
                atm.choiceScreen();
                int choice;
                System.out.println("---->");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Çekmek istediğiniz tutarı giriniz: ");
                        String money = sc.next();
                        atm.withdrawBalance(customerId, money);
                        break;
                    case 2:
                        System.out.println("Yatırmak istediğiniz tutarı giriniz: ");
                        String money2 = sc.next();
                        atm.depositBalance(customerId, money2);
                        break;
                    case 3:
                        System.out.println("Parayı göndermek istediğiniz hesabın id numarasını giriniz: ");
                        String id = sc.next();
                        System.out.println("Göndermek istediğiniz miktarı giriniz: ");
                        String money3 = sc.next();
                        atm.transferBalance(customerId, id, money3);
                        break;
                    case 4:
                        System.out.println("Lütfen sırasıyla istenilen bilgileri giriniz: ");
                        System.out.println("Kullanıcı numarası: ");
                        String id2 = sc.next();
                        System.out.println("Kullanıcı isminiz: ");
                        String surname = sc.next();
                        System.out.println("Kullanıcı Şifreniz");
                        String password1 = sc.next();
                        System.out.println("Hesap iban numaranız: ");
                        String iban = sc.next();
                        atm.createNewAccount(id2, surname, password1, iban);
                        break;
                    case 5:
                        System.out.println("Lütfen yeni Şifreniz giriniz: ");
                        String password2 = sc.next();
                        atm.changePassword(customerId, password2);
                        break;
                    case 6:
                        System.out.println("Hesaptan kalan Bakiyeniz: " + customer.balance + " TL");
                        break;
                    case 7:
                        System.out.println("\t--Hoşgeldiniz--\n-> Hesabınıza Giriş Yapınız.\n");
                        System.out.println(" Kullanıcı Numarası: ");
                        String customerId1 = sc.next();
                        System.out.println("\n Şifrenizi Giriniz: ");
                        String password3 = sc.next();
                        Customer customer1 = map.get(customerId1);
                        if (map.containsKey(customerId) && customer1.password.equals(password3)) {
                            System.out.println("\tBaşarıyla Giriş Yaptınız. ");
                            atm.changeAccount(customerId1, password3);
                        } else {
                            System.out.println("Kullanıcı numaranız veya şifreniz hatalı! ");
                        }
                        break;
                    case 8:
                        System.out.println("İyi Günler \nHesaptan Çıkış Yapılıyor ...");
                        control = false;
                }
            }

        } else {
            System.out.println("kullanıcı numaranız veya şifreniz hatalı! ");
        }

    }
}