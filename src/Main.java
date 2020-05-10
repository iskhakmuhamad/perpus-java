import controller.AdminController;
import model.BukuModel;
import view.*;

public class Main {
    public static void main(String[] args) {
//        VHome vHome = new VHome();
//        BukuModel bukuModel = new BukuModel();
//        HomeController homeController = new HomeController(vHome, bukuModel);
        VAdmin vAdmin = new VAdmin();
        BukuModel bukuModel = new BukuModel();
        AdminController homeController = new AdminController(vAdmin, bukuModel);
    }
}
