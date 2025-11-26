import java.sql.*;
import java.util.Scanner;

class MainApp {

    public static void afisare_tabela(Statement statement) {
        String sql = "select * from persoane";
        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next())
                System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ",varsta=" + rs.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void adaugare(Connection connection, int id, String nume, int varsta) {String sql="insert into persoane values (?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nume);
            ps.setInt(3, varsta);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de adaugare="+nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void stergere(Connection connection,int id){
        String sql="delete from persoane where id=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de stergere="+nr_randuri);
        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void actualizare(Connection connection,int id,int varsta){
        String sql="update persoane set varsta=? where id=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, varsta);
            ps.setInt(2, id);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de modificare="+nr_randuri);} catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
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

    public  static void afisarePersoanaDestinatie(Connection connection) throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Destinatia:");
        String destination = sc.nextLine();

        ResultSet rs = connection.createStatement().executeQuery( "SELECT DISTINCT p.nume FROM persoane p JOIN excursii e ON p.id=e.id_persoana " +
                "WHERE e.destinatia='" + destination + "'");

        System.out.println("Persoane care au vizitat " + destination + ":");

        while (rs.next()) {
            System.out.println(" -> " + rs.getString("nume"));
        }
    }


    public  static void afisarePersoaneExcursiiAn(Connection connection) throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Anul:");
        String anul = sc.nextLine();

        ResultSet rs = connection.createStatement().executeQuery( "SELECT DISTINCT p.nume FROM persoane p JOIN excursii e ON p.id=e.id_persoana " +
                "WHERE e.anul='" + anul + "'");

        System.out.println("Persoane care au vizitat " + anul + ":");

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

    public static void afisarePersoane(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM persoane");

        while (rs.next()) {
            int id = rs.getInt("id");
            System.out.println("Persoană: " + rs.getString("nume") +
                    " (vârsta " + rs.getInt("varsta") + ")");

        }
    }
    public static void afisareExcursii(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM excursii");

        while (rs.next()) {
            int id = rs.getInt("id_excursie");
            System.out.println(" -> " + rs.getString("destinatia") +
                    " (" + rs.getInt("anul") + ")");

        }
    }




    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/drv8";
        String sql="select * from persoane";
        Connection connection= DriverManager.getConnection(url, "root", "root");
        Statement statement = connection.createStatement();


        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nMENIU");
            System.out.println("1. Adaugare persoana");
            System.out.println("2. Adaugare excursie");
            System.out.println("3. Afisare persoane + excursii");
            System.out.println("4. Afisare excursii dupa numele persoanei");
            System.out.println("5. Afisare persoane dupa destinatie");
            System.out.println("6. Afisare persoane dupa an");
            System.out.println("7. Stergere excursie");
            System.out.println("8. Ștergere persoana + excursii");
            System.out.println("9. Afisare persoane");
            System.out.println("10. Afisare excursii");
            System.out.println("0. Iesire");
            System.out.print("Alege opțiunea: ");

            int optiune = Integer.parseInt(sc.nextLine());
            switch (optiune) {
                case 1 -> adaugarePersoana(connection);
                case 2-> adaugareExcursie(connection);
                case 3->afisarePersoaneExcursii(connection);
                case 4->afisareExcursiiDupaPersoana(connection);
                case 5->afisarePersoanaDestinatie(connection);
                case 6->afisarePersoaneExcursiiAn(connection);
                case 7->stergereExcursie(connection);
                case 8->stergerePersoana(connection);
                case 9 ->afisarePersoane(connection);
                case 10 ->afisareExcursii(connection);
                default -> System.out.println("Opțiune invalidă!");
            }


        }



/*
        String url = "jdbc:mysql://localhost:3306/drv8";
        String sql="select * from persoane";
        Connection connection= DriverManager.getConnection(url, "root", "root");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next())
            System.out.println("id=" + rs.getInt("Id") + ", nume= "
                    + rs.getString("nume") + ",varsta=" + rs.getInt("varsta"));

        try {
            afisare_tabela(statement,"Continut initial");
            adaugare(connection,4,"Dana",23);
            afisare_tabela(statement,"Dupa adaugare");
            actualizare(connection,4,24);
            afisare_tabela(statement,"Dupa modificare");
            stergere(connection,4);
            afisare_tabela(statement,"Dupa stergere");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.close();
        statement.close();
        rs.close();
*/

    }
}
