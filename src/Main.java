import controller.HomeController;
import model.BukuModel;
import view.VHome;

public class Main {
    public static void main(String[] args) {
        VHome vHome = new VHome();
        BukuModel bukuModel = new BukuModel();
        HomeController homeController = new HomeController(vHome, bukuModel);
    }
}