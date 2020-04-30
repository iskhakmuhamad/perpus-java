import controller.LoginController;
import model.UserModel;
import view.VLogin;

public class Main {
    public static void main(String[] args) {
        VLogin vLogin = new VLogin();
        UserModel model = new UserModel();
        LoginController controller = new LoginController(model, vLogin);

    }
}
