package problema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class MainApp {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/laboratorul8";
        String sql = "select * from persoane";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next())
            System.out.println("id=" + rs.getInt("Id") + ", nume= "
                    + rs.getString("nume") + ",varsta=" + rs.getInt("varsta"));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENIU =====");
            System.out.println("1. Adăugare persoană");
            System.out.println("2. Adăugare excursie");
            System.out.println("3. Afișare persoane + excursii");
            System.out.println("4. Afișare excursii după numele persoanei");
            System.out.println("5. Afișare persoane după destinație");
            System.out.println("6. Afișare persoane după an");
            System.out.println("7. Ștergere excursie");
            System.out.println("8. Ștergere persoană + excursii");
            System.out.println("0. Ieșire");
            System.out.print("Alege opțiunea: ");

            int opt = Integer.parseInt(sc.nextLine());

            switch (opt) {
                case 1 -> adaugarePersoana(connection);
                case 2-> adaugareExcursie(connection);
                case 3->afisarePersoaneExcursii(connection);
                case 4->afisareExcursiiDupaPersoana(connection);
                case 5->afisarePersoanaDestinatie(connection);
                case 6->afisarePersoaneExcursiiAn(connection);
                case 7->stergereExcursie(connection);
                case 8->stergerePersoana(connection);
                default -> System.out.println("Opțiune invalidă!");
            }
        }

    }
    public static void adaugarePersoana(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nume: ");
        String nume = sc.nextLine();
        System.out.print("Vârstă: ");
        int varsta = sc.nextInt();

        String sql = "INSERT INTO persoane(nume, varsta) VALUES ('" + nume + "'," + varsta + ")";
        conn.createStatement().executeUpdate(sql);

        System.out.println(" Persoană adăugată.");
    }
    public static void adaugareExcursie(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID persoană: ");
        int id = Integer.parseInt(sc.nextLine());

        ResultSet rs = conn.createStatement()
                .executeQuery("SELECT * FROM persoane WHERE id=" + id);

        if (!rs.next()) {
            System.out.println(" Persoana nu există!");
            return;
        }

        System.out.print("Destinația: ");
        String dest = sc.nextLine();

        System.out.print("Anul: ");
        int anul = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO excursii(id_persoana, destinatia, anul) VALUES (" +
                id + ", '" + dest + "', " + anul + ")";

        conn.createStatement().executeUpdate(sql);

        System.out.println("Excursie adăugată.");
    }

    public static void afisarePersoaneExcursii(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM persoane");

        while (rs.next()) {
            int id = rs.getInt("id");
            System.out.println("\nPersoană: " + rs.getString("nume") +
                    " (vârsta " + rs.getInt("varsta") + ")");

            ResultSet ex = conn.createStatement().executeQuery(
                    "SELECT * FROM excursii WHERE id_persoana=" + id);

            while (ex.next()) {
                System.out.println("   -> " + ex.getString("destinatia") +
                        " (" + ex.getInt("anul") + ")");
            }
        }
    }

    public static void afisareExcursiiDupaPersoana(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Numele persoanei: ");
        String nume = sc.nextLine();

        ResultSet rs = conn.createStatement().executeQuery(
                "SELECT id FROM persoane WHERE nume='" + nume + "'");

        if (!rs.next()) {
            System.out.println("Persoana nu există!");
            return;
        }

        int id = rs.getInt("id");

        ResultSet ex = conn.createStatement().executeQuery(
                "SELECT destinatia, anul FROM excursii WHERE id_persoana=" + id);

        System.out.println("Excursiile lui " + nume + ":");

        while (ex.next()) {
            System.out.println(" -> " + ex.getString("destinatia") +
                    " (" + ex.getInt("anul") + ")");
        }
    }

    public static void afisarePersoanaDestinatie(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Destinația: ");
        String dest = sc.nextLine();

        ResultSet rs = conn.createStatement().executeQuery(
                "SELECT DISTINCT p.nume FROM persoane p JOIN excursii e ON p.id=e.id_persoana " +
                        "WHERE e.destinatia='" + dest + "'");

        System.out.println("Persoane care au vizitat " + dest + ":");

        while (rs.next()) {
            System.out.println(" -> " + rs.getString("nume"));
        }
    }

    public static void afisarePersoaneExcursiiAn(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Anul: ");
        int anul = sc.nextInt();

        ResultSet rs = conn.createStatement().executeQuery(
                "SELECT DISTINCT p.nume FROM persoane p JOIN excursii e ON p.id=e.id_persoana " +
                        "WHERE e.anul=" + anul);

        System.out.println("Persoane care au fost în excursii în anul " + anul + ":");

        while (rs.next()) {
            System.out.println(" -> " + rs.getString("nume"));
        }
    }

    public static void stergereExcursie(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID excursie: ");
        int id = sc.nextInt();

        conn.createStatement().executeUpdate(
                "DELETE FROM excursii WHERE id_excursie=" + id);

        System.out.println("Excursie ștearsă.");
    }

    public static void stergerePersoana(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID persoană: ");
        int id = sc.nextInt();

        conn.createStatement().executeUpdate(
                "DELETE FROM persoane WHERE id=" + id);

        System.out.println("Persoană și excursiile asociate au fost șterse.");
    }
}